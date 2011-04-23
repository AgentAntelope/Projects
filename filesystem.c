/*
	FUSE: Filesystem in Userspace
	Copyright (C) 2001-2007  Miklos Szeredi <miklos@szeredi.hu>

	This program can be distributed under the terms of the GNU GPL.
	See the file COPYING.

	gcc -Wall `pkg-config fuse --cflags --libs` cs1550.c -o cs1550
*/

#define	FUSE_USE_VERSION 26

#include <fuse.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>
#include <stdlib.h>
//size of a disk block
#define	BLOCK_SIZE 512

//we'll use 8.3 filenames
#define	MAX_FILENAME 8
#define	MAX_EXTENSION 3

//How many files can there be in one directory?
#define	MAX_FILES_IN_DIR (BLOCK_SIZE - (MAX_FILENAME + 1) - sizeof(int)) / \
	((MAX_FILENAME + 1) + (MAX_EXTENSION + 1) + sizeof(size_t) + sizeof(long))

//How much data can one block hold?
#define	MAX_DATA_IN_BLOCK (BLOCK_SIZE - sizeof(size_t) - sizeof(long))

struct cs1550_directory_entry
{
	char dname[MAX_FILENAME	+ 1];	//the directory name (plus space for a nul)
	int nFiles;			//How many files are in this directory. 
					//Needs to be less than MAX_FILES_IN_DIR

	struct cs1550_file_directory
	{
		char fname[MAX_FILENAME + 1];	//filename (plus space for nul)
		char fext[MAX_EXTENSION + 1];	//extension (plus space for nul)
		size_t fsize;			//file size
		long nStartBlock;		//where the first block is on disk
	} files[MAX_FILES_IN_DIR];		//There is an array of these
};

typedef struct cs1550_directory_entry cs1550_directory_entry;

struct cs1550_disk_block
{
	//Two choices for interpreting size: 
	//	1) how many bytes are being used in this block
	//	2) how many bytes are left in the file
	//Either way, size tells us if we need to chase the pointer to the next
	//disk block. Use it however you want.
	size_t size;	

	//The next disk block, if needed. This is the next pointer in the linked 
	//allocation list
	long nNextBlock;

	//And all the rest of the space in the block can be used for actual data
	//storage.
	char data[MAX_DATA_IN_BLOCK];
};

typedef struct cs1550_disk_block cs1550_disk_block;

/**
* Need a pointer to a directory entry space
* which directory you want in ith position
*
* returns: 1 means that a directory was successfully found, 0 is failure (Usually EOF).
* Use in a while loop like:
* int i = 0;
* while(get_dir(cs1550_directory_entry &f, i) != 0){
*   printf("My name is %s", f.name); //prints out the directory name for each directory
*    i++;
* }
*/
static int get_dir(cs1550_directory_entry *fill, int i){
	int ret = 0;
	int seekCode;
	FILE *f = fopen(".directories", "r");
	if(f == NULL) return ret;
	seekCode = fseek(f, sizeof(cs1550_directory_entry)*i, SEEK_SET);	
	seekCode = fread(fill, sizeof(cs1550_directory_entry), 1, f);
	if(seekCode == 1){
		ret = 1;
	}
	fclose(f);
	return ret;
}
/*
 * CHECK BLOCK
 * Returns 1 if block is allocated, 0 if free
 * -1 on error (requested block is out of range) or File not found.
 */
int checkBitmap(int block){
	if(block < MAX_BITMAP_BYTES && block >= 0){
		int currByte = block/8;
		int bitLocation = block % 8 ;
		unsigned char thisByte;
		FILE *f = fopen(".disk", "r");
		if(f == NULL){
			return -1;

		}
		fseek(f, currByte, SEEK_SET);
		fread(&thisByte, 1, 1, f);
		fclose(f);
		if((thisByte & (1<<bitLocation)) == 0){
			return 0;
		}
		else{
			return 1;
		}
	}
	else{
		return -1;
	}


}
/*
 * FREE
 * Sets the firekitten-th bit to 0 (indicating block is now free)
 * Returns 0 if firekitten falls outside of the range of blocks managed by the bitmap
*/
int freeBlock(int blockNum){
	if(blockNum < MAX_BITMAP_BYTES && blockNum >= 4){
		int byteLoc = blockNum/8;
		int bitLoc = blockNum % 8;
		unsigned char thisByte;
		FILE *f = fopen(".disk", "r");
		if(f == NULL){
			return -1;

		}
		fseek(f, byteLoc, SEEK_SET);
		fread(&thisByte, 1, 1, f);
		thisByte &= ~(1<<bitLoc);
		printf("thisbyte is: %d\n", thisByte);
		fseek(f, byteLoc, SEEK_SET);
		fwrite(&thisByte, 1, 1, f);
		fclose(f);
		return 0;
	}
	else{
		return -1;
	}
}

/*
 * ALLOCATE
 * Sets the plainkitten-th bit to 1 (indicating block is now allocated)
 * Returns 0 if plainkitten falls outside of the range of blocks managed by the bitmap
*/
int allocateBlock(int blockNum){
	if(blockNum < MAX_BITMAP_BYTES && blockNum >= 4){
		int byteLoc = blockNum/8;
		int bitLoc = blockNum % 8 ;
		int opSuccess;
		unsigned char thisByte;
		FILE *f = fopen(".disk", "r+");
		if(f == NULL){
			return -1;

		}
		fseek(f, byteLoc, SEEK_SET);
		fread(&thisByte, 1, 1, f);
		thisByte |= 1<<bitLoc;
		printf("thisbyte is: %x\n", thisByte);
		fseek(f,byteLoc, SEEK_SET);
		opSuccess = fwrite(&thisByte, 1, 1, f);
		printf("%d success\n", opSuccess);
		fclose(f);
		return 0;
	}
	else{
		return -1;
	}
}
/**
* Will write the directory to either an "empty" slot or will
* append to the end of the .directories file.
* returns 1 if successful. Otherwise 0.
*/

static int write_dir(cs1550_directory_entry *written){
	int ret = 0;
	int i = 0;
	cs1550_directory_entry check;
	while(get_dir(&check, i) != 0){
	
		if(check.nFiles == -1){
			FILE *f = fopen(".directories", "r+");
			fseek(f, sizeof(cs1550_directory_entry)*i, SEEK_SET);
			fwrite(written, sizeof(cs1550_directory_entry), 1, f);
			fclose(f);
			ret = 1;
		}
		i++;
	}
	if(ret == 0){
		FILE *f = fopen(".directories", "a");
		fwrite(written, sizeof(cs1550_directory_entry),1,f);
		ret = 1;
		fclose(f);
	}
	return ret;
}
/*
Used only for rmdir.
*/
static int write_dir_place(cs1550_directory_entry *written, int place){
	int ret = 0;
	cs1550_directory_entry test_exist;
	if(get_dir(&test_exist, place) != 0){
                 FILE *f = fopen(".directories", "r+");
                 fseek(f, sizeof(cs1550_directory_entry)*place, SEEK_SET);
                 fwrite(written, sizeof(cs1550_directory_entry), 1, f);
                 fclose(f);
                 ret = 1;
	}
	return ret;
}
/**
* will return the ith place in .directories of where the file is.
* returns -1 if directory doesn't exist.
*/
static int dir_exists(char * name){
	int ret = -1;
	cs1550_directory_entry curr_dir;
	int i = 0;
	while(get_dir(&curr_dir, i) != 0){
		if(strcmp(name, curr_dir.dname) == 0 && curr_dir.nFiles != -1){
			ret = i;
			break;
		}
		i++;	
	}
	return ret;
}
/**
*Returns what the path is.
*If the path is a root, return 0.
*If the path is a directory, return 1
*If the path is a file, return 2 or 3(3 has an extension)
*/
static int path_name(char *directory,char *file,char *extension){
	int ret = 0;	
	if(directory != NULL){
		ret = 1;
	}
	if(file != NULL)
		ret = 2;	
	if(extension != NULL){
		ret = 3;
	}
	return ret;
}
/**
will get the file location of the file from the directory entry or will return -1 if
it does not exist.
params:
	directory_entry is self explanatory
	filename is the first part of the filename
	ext is the extension
	file_type is the type of file (either 2 for a file with no extension or 3 with an extension)

return value: a number that is the i'th position of the file with that name
		-1 if it can't find one.

*/
static int get_file_location(cs1550_directory_entry *fill, char * filename, char *ext, int file_type){
	int i;

	for(i = 0; i < fill->nFiles; i++){
		if(file_type == 2 && !strcmp(filename, fill->files[i].fname) && (strlen(fill->files[i].fext) == 0)){
			return i;
		}
		else if(file_type == 3 && !strcmp(filename, fill->files[i].fname) && !strcmp(fill->files[i].fext, ext)){
			return i;
		}
	}
	return -1;
}
/*
 * Called whenever the system wants to know the file attributes, including
 * simply whether the file exists or not. 
 *
 * man -s 2 stat will show the fields of a stat structure
 */
/*
 * Called whenever the system wants to know the file attributes, including
 * simply whether the file exists or not.
 *
 * man -s 2 stat will show the fields of a stat structure
 */
static int cs1550_getattr(const char *path, struct stat *stbuf)
{
	int res = 0;
	char *directory = strtok(path, "./");
	char *filename = strtok(NULL, "./");
	char *ext = strtok(NULL, ".");

	memset(stbuf, 0, sizeof(struct stat));

    int type = path_name(directory, filename, ext);
	//is path the root dir?
	if (type==0) {
		stbuf->st_mode = S_IFDIR | 0755;
		stbuf->st_nlink = 2;
	} else{ // path is a directory or file

	    // see if dir exists
	    int index = dir_exists(directory);
	    if(index==-1){
            res = -ENOENT; // directory does not exists
	    }
	    else{ // directory exists
            	struct cs1550_directory_entry curr;
            	get_dir(&curr,index); // get directory
            	if(type==1){ // path is a directory

                // set structure
                // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! What else do I need to set??
                // im looking at the man page on stat, but it's not evident to me what else i need to set if anything else
                stbuf->st_mode = S_IFDIR | 0755;
                stbuf->st_nlink = 2;
            	}
            else{ // path is a file
                // see if file exists
             	   int i;
		   res = -ENOENT;

             	   for(i=0; i< curr.nFiles; i++){
             	       if(strcmp(curr.files[i].fname,filename)==0){ // file exists
				if(type == 3 && strcmp(curr.files[i].fext,ext)!=0){
					continue;
				}	
                       			stbuf->st_mode = S_IFREG | 0666;
                        		stbuf->st_nlink = 1; //file links
                        		stbuf->st_size = curr.files[i].fsize;
                        		res = 0; // file found
                    	}
               	  }
               }
	    }
	}
	return res;
}

/* 
 * Called whenever the contents of a directory are desired. Could be from an 'ls'
 * or could even be when a user hits TAB to do autocompletion
 */
static int cs1550_readdir(const char *path, void *buf, fuse_fill_dir_t filler,
			 off_t offset, struct fuse_file_info *fi)
{
	//Since we're building with -Wall (all warnings reported) we need
	//to "use" every parameter, so let's just cast them to void to
	//satisfy the compiler
	int ret  = 0;
	cs1550_directory_entry curr_dir;
	(void) offset;
	(void) fi;

	if (strcmp(path, "/") == 0){
		int i = 0;
		filler(buf, ".", NULL,0);
		filler(buf, "..", NULL, 0);
		while(get_dir(&curr_dir, i) != 0){
			i++;
			if(curr_dir.nFiles == -1){
				continue;
		 	}
			filler(buf, curr_dir.dname, NULL, 0);
		
		}
		ret = 0;
	}
	else{
		char *directory = strtok(path, "./");
		char *filename = strtok(NULL, "./");
		char *ext = strtok(NULL, ".");
		int path_type = path_name(directory, filename, ext);
		if(path_type >= 1){
			int dirEntry = dir_exists(directory);
			if(dirEntry != -1 && path_type == 1){
				char buffer [50];
				int i = 0;	
			  	get_dir(&curr_dir, dirEntry);
				filler(buf, ".", NULL, 0);
			        filler(buf, "..", NULL, 0);
				
				for(; i< curr_dir.nFiles; i++){
					sprintf(buffer, "%s.%s", curr_dir.files[i].fname, curr_dir.files[i].fext);
					filler(buf, buffer, NULL, 0);
				}
				ret= 0;
			}
			else{
				ret= -ENOENT;
			}
		}

	}	
	//the filler function allows us to add entries to the listing
	//read the fuse.h file for a description (in the ../include dir)
	
	/*
	//add the user stuff (subdirs or files)
	//the +1 skips the leading '/' on the filenames
	filler(buf, newpath + 1, NULL, 0);
	*/
	return ret;
}

/*
 * Creates a directory. We can ignore mode since we're not dealing with
 * permissions, as long as getattr returns appropriate ones for us.
 */
static int cs1550_mkdir(const char *path, mode_t mode)
{
	int ret = 0;
	char *directory = strtok(path, "./");
	char *filename = strtok(NULL, "./");
	char *ext = strtok(NULL, ".");
	(void) mode;
	printf("%s\n", directory);
	if(path_name(directory, filename, ext) !=1){
		ret = -EPERM;
	}
	else{
        // name NOT too long
        if(dir_exists(directory)==-1){
            // directory does NOT exist
            if(strlen(directory)<=MAX_FILENAME){
		 // good to go, create directory
                cs1550_directory_entry new_directory;
                strcpy(new_directory.dname, directory);
		new_directory.nFiles = 0;
		printf("Hello world\n");
                write_dir(&new_directory);
            }
            else{
                 ret = -ENAMETOOLONG;   // filename submitted
            }
        }
        else{
            ret = -EEXIST; // directory already exists
        }
	}

    return ret;
}

/* 
 * Removes a directory.
 */
static int cs1550_rmdir(const char *path)
{
	char *directory = strtok(path, "./");
	char *filename = strtok(NULL, "./");
	char *ext = strtok(NULL, ".");
        int path_type;
	int ret = 0;
        path_type = path_name(directory,filename,ext);
	if(path_type == 1){
		int dir_place;
		cs1550_directory_entry curr;
		dir_place = dir_exists(directory);
		if(dir_place == -1){
			ret = -ENOENT;
		}
		else{
			get_dir(&curr, dir_place);
			if(curr.nFiles > 0){
				ret = -ENOTEMPTY;
			}
			else{
				curr.nFiles = -1;
				write_dir_place(&curr, dir_place);		
			}
		}
	}
	else{
		ret = -ENOTDIR;
	}

    return ret;
}

/* 
 * Does the actual creation of a file. Mode and dev can be ignored.
 *
 */
static int cs1550_mknod(const char *path, mode_t mode, dev_t dev)
{
	(void) mode;
	(void) dev;
	char *directory = strtok(path, "./");
	char *filename = strtok(NULL, "./");
	char *ext = strtok(NULL, ".");
        int path_type;
	int ret = 0;
        path_type = path_name(directory,filename,ext);
	if(path_type >= 2){//Right place to put the file
		int directory_place = dir_exists(directory);
		//No extension, just file name.

		if(strlen(filename) > MAX_FILENAME){
			ret = -ENAMETOOLONG;
		}
		else if(path_type == 3  && strlen(ext)> MAX_EXTENSION){//If it has an extension (path_type = 3) and that extension is greater than max..
			ret = -ENAMETOOLONG;
		}
		else{
			cs1550_directory_entry curr;
			get_dir(&curr, directory_place);
			if(get_file_location(curr, filename, ext, path_type) == -1){
				strcpy(curr.files[curr.nFiles].fname ,filename);
				if(path_type == 3){
					strcpy(curr.files[curr.nFiles].fext ,ext);
				}
				curr.nFiles = curr.nFiles+1;
				write_dir_place(&curr, directory_place);
				ret = 0;
				
			}
			else{
				ret = -EEXIST;
			}
			
		}
	}
	else{ 
		ret = -EPERM;
	}
	return ret;
}

/*
 * Deletes a file
 */
static int cs1550_unlink(const char *path)
{
    (void) path;

    return 0;
}

/* 
 * Read size bytes from file into buf starting from offset
 *
 */
static int cs1550_read(const char *path, char *buf, size_t size, off_t offset,
			  struct fuse_file_info *fi)
{
	(void) buf;
	(void) offset;
	(void) fi;
	(void) path;

	//check to make sure path exists
	//check that size is > 0
	//check that offset is <= to the file size
	//read in data
	//set size and return, or error

	size = 0;

	return size;
}

/* 
 * Write size bytes from buf into file starting from offset
 *
 */
static int cs1550_write(const char *path, const char *buf, size_t size, 
			  off_t offset, struct fuse_file_info *fi)
{
	(void) buf;
	(void) offset;
	(void) fi;
	(void) path;

	//check to make sure path exists
	//check that size is > 0
	//check that offset is <= to the file size
	//write data
	//set size (should be same as input) and return, or error

	return size;
}

/******************************************************************************
 *
 *  DO NOT MODIFY ANYTHING BELOW THIS LINE
 *
 *****************************************************************************/

/*
 * truncate is called when a new file is created (with a 0 size) or when an
 * existing file is made shorter. We're not handling deleting files or 
 * truncating existing ones, so all we need to do here is to initialize
 * the appropriate directory entry.
 *
 */
static int cs1550_truncate(const char *path, off_t size)
{
	(void) path;
	(void) size;

    return 0;
}


/* 
 * Called when we open a file
 *
 */
static int cs1550_open(const char *path, struct fuse_file_info *fi)
{
	(void) path;
	(void) fi;
    /*
        //if we can't find the desired file, return an error
        return -ENOENT;
    */

    //It's not really necessary for this project to anything in open

    /* We're not going to worry about permissions for this project, but 
	   if we were and we don't have them to the file we should return an error

        return -EACCES;
    */

    return 0; //success!
}

/*
 * Called when close is called on a file descriptor, but because it might
 * have been dup'ed, this isn't a guarantee we won't ever need the file 
 * again. For us, return success simply to avoid the unimplemented error
 * in the debug log.
 */
static int cs1550_flush (const char *path , struct fuse_file_info *fi)
{
	(void) path;
	(void) fi;

	return 0; //success!
}


//register our new functions as the implementations of the syscalls
static struct fuse_operations hello_oper = {
    .getattr	= cs1550_getattr,
    .readdir	= cs1550_readdir,
    .mkdir	= cs1550_mkdir,
	.rmdir = cs1550_rmdir,
    .read	= cs1550_read,
    .write	= cs1550_write,
	.mknod	= cs1550_mknod,
	.unlink = cs1550_unlink,
	.truncate = cs1550_truncate,
	.flush = cs1550_flush,
	.open	= cs1550_open,
};

//Don't change this.
int main(int argc, char *argv[])
{	
	return fuse_main(argc, argv, &hello_oper, NULL);
}

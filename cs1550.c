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
#define MAX_BITMAP_BYTES 1280
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
//////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////Functions for Files////////////////////////////////
//////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
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
		if(file_type == 2 && !strcmp(filename, fill->files[i].fname) && (strlen(fill->files[i].fext) == 0) && fill->files[i].size != -1){
			return i;
		}
		else if(file_type == 3 && !strcmp(filename, fill->files[i].fname) && !strcmp(fill->files[i].fext, ext)&& fill->files[i].size != -1){
			return i;
		}
	}
	return -1;
}

/*
 * CHECK BLOCK
 * Returns 1 if block is allocated, 0 if free
 * -1 on error (requested block is out of range) or File not found.
 */
static int checkBitmap(int block){
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
static int freeBlock(int blockNum){
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
static int allocateBlock(int blockNum){
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

		fseek(f,byteLoc, SEEK_SET);
		opSuccess = fwrite(&thisByte, 1, 1, f);

		fclose(f);
		return 0;
	}
	else{
		return -1;
	}
}
/**
Given a place, will write to that area on disk.
returns -1 if outside the bounds of the .disk file, or if it could not find .disk.

else return 0.

*/
static int create_disk_block(int place){
	if(place <= 3 || place > MAX_BITMAP_BYTES){
		return -1;
	}
	cs1550_disk_block newBlock;
	newBlock.size = 0;
	newBlock.nNextBlock = -1;
	FILE *f = fopen(".disk", "r+");
	if(f == NULL){
		return -1;
	}
	fseek(f, sizeof(cs1550_disk_block)*place, SEEK_SET);
	fwrite(&newBlock, sizeof(cs1550_disk_block), 1, f);
	fclose(f);
	return 0;
}
/*
gets the ith block from the disk and stores it in (point)
returns -1 if out of bounds or can't find file.
*/
static int get_block(cs1550_disk_block *point, int place){
	if(place <= 3 || place > MAX_BITMAP_BYTES){
		return -1;
	}
	FILE *f = fopen(".disk", "r");
	if(f == NULL){
		return -1;
	}
	fseek(f, sizeof(cs1550_disk_block)*place, SEEK_SET);
	fread(point, sizeof(cs1550_disk_block), 1, f);
	fclose(f);	
	return 0;	
}
/*
*  Gets the first free block available. For better efficiency, it would be best to store int somewhere
*  and then just make the bitmap circular in nature. 

Will return -1 if .disk is full, otherwise returns ith position (not touching the reserved bitmap space)
*/
static int get_free_block(){
	int i = 4;
	while(checkBitmap(i)!= 0){
		i++;
		if(i > MAX_BITMAP_BYTES){
			return -1;
		}
	}
	return i;
}

/*
Creates a disk block in the first empty block found in the bitmap
Returns -1 if failed.
*/
static int create_space(){
	int i = get_free_block();
	create_disk_block(i);
	allocateBlock(i);
	return i;
}


/*
returns current Disk block to start writing to.
returns position into the current disk block to start at
returns -1 if over the offset by a large margin (too large for append)
*/
static int get_offset(cs1550_disk_block *f,int start_pos, int offset){
	get_block(f, start_pos);
	while(f->size <= offset){
		if(f->nNextBlock == -1){
			return -1;
		}
		offset = offset - f->size;
		get_block(f, f->nNextBlock);
	}
	return offset;
}
/*
Will write a disk block back to disk. Has to be within specified parameters

return 0 if successful,
otherwise 1.
*/
static int write_block(cs1550_disk_block *to_write, int place){
	if(place <= 3 || place > MAX_BITMAP_BYTES){
		return -1;
	}
	FILE *f = fopen(".disk", "r+");
	if(f == NULL){
		return -1;
	}
	fseek(f, sizeof(cs1550_disk_block)*place, SEEK_SET);
	fwrite(to_write, sizeof(cs1550_disk_block), 1, f);
	fclose(f);	
	return 0;
}
/*
Will put as much data it can into the current block. If it succeeds into reducing size to 0,
return 1. If it needs more space, return 0.
*/
static int put_data_in_block(cs1550_disk_block *data_block, char *buf, int size, int position){
	int i = 0;
	while(size > 0){
		if(position < MAX_DATA_IN_BLOCK){
			data_block->data[position] = *buf;
			size--;
			buf++;
			if(position >= data_block->size){
				data_block->size = data_block->size + 1;
			}
			position++;
		}
		else{
			break;
		}
	}
	return size;
}

/*
Will attempt to get size from the current block and put it into the buffer. 
If it doesn't, it returns a smaller size than it once had.

position used to get the n'th data bit.
*/
static int get_data_from_block(cs1550_disk_block *data_block, char *buf, int size, int position){
	while(size > 0){
		if(position < MAX_DATA_IN_BLOCK && position < data_block->size){
			*buf= data_block->data[position];
			size--;
			buf++;
			position++;
		}
		else{
			break;
		}
	}
	return size;
}
/**
First, get offset block.
You subtract n-times to get to that block, and what is left is a position of where to start
abstracting data.
*/

static int write_data(int start_block, char *buff, int offset, int size){
	cs1550_disk_block start;
	int return_size = 0;
	int new_size, point;
	point = start_block;
	int position = get_offset(&start, start_block, offset);
	new_size = put_data_in_block(&start,buff,size,position);
	position = 0;
	printf("new_size is %d", new_size);
	while(new_size > 0){
		if(start.nNextBlock == -1){
			printf("The size at this point is %d\n", new_size);
			int new_block = create_space();
			start.nNextBlock = new_block;
			write_block(&start, point);
			point = new_block;
			buff += (size-new_size);
			return_size += size-new_size;
			size = new_size;
			get_block(&start, point);
			new_size = put_data_in_block(&start, buff, size, position);
		}
		else{  //If there is a next block...
			write_block(&start, point);
			buff += (size-new_size);
			point = start.nNextBlock;
			get_block(&start, point);//Gets the next block.
			return_size += size-new_size;
			size = new_size;			
			new_size = put_data_in_block(&start, buff, size, position);			
		}

	}
	printf("what is the size:: %d, endsave: %d\n", start.size, point);
	write_block(&start, point);
	return_size += size-new_size;
	return  return_size;
}
static int read_data(int start_block, char *buff, int offset, int size){

	cs1550_disk_block start;
	int return_size = 0;
	int new_size, point;
	int new_position = get_offset(&start, start_block, offset); //Gets the starting block from the offset
	printf("size here outside:%d start_block = %d\n", start.size, start_block);
	new_size = get_data_from_block(&start,buff,size,point);
	printf("new size is: %d", new_size);
	return_size += size-new_size;
	while(new_size > 0){
		printf("next block is: %d", start.nNextBlock);
		if(start.nNextBlock < 0){
			break;
		}
		get_block(&start, start.nNextBlock);
		buff += (size-new_size);
		printf("return size is: %d\n", return_size);
		size = new_size;
		new_size = get_data_from_block(&start, buff, size, point);
		return_size += size-new_size;
	}

	return return_size;
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
					printf("strlen is %d\n", strlen(curr_dir.files[i].fext));
					if(strlen(curr_dir.files[i].fext) > 0){
						
						sprintf(buffer, "%s.%s", curr_dir.files[i].fname, curr_dir.files[i].fext);
					}
					else{
						sprintf(buffer, "%s", curr_dir.files[i].fname);
					}
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
			if(get_file_location(&curr, filename, ext, path_type) == -1){
				strcpy(curr.files[curr.nFiles].fname ,filename);
				if(path_type == 3){
					strcpy(curr.files[curr.nFiles].fext ,ext);
				}
				else{
					strcpy(curr.files[curr.nFiles].fext, "\0");
				}
				curr.files[curr.nFiles].nStartBlock = create_space();
				curr.files[curr.nFiles].fsize = 0;
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
	(void) offset;
	(void) fi;
	char *directory = strtok(path, "./");
	char *filename = strtok(NULL, "./");
	char *ext = strtok(NULL, ".");
        int path_type;
        path_type = path_name(directory,filename,ext);

	//check to make sure path exists
	//check that size is > 0
	//check that offset is <= to the file size
	//read in data
	//set size and return, or error
	if(size <= 0){
		return 0;
	}
	if(path_type >= 2){//Right place for a file
		int directory_place = dir_exists(directory);
		int file_loc;
		int size_read;
		cs1550_directory_entry curr;

		if(directory_place == -1){
			return -EISDIR;
		}
		get_dir(&curr, directory_place);
		file_loc = get_file_location(&curr, filename, ext, path_type);
		printf("file location: %d\n", file_loc);
		if(curr.files[file_loc].fsize < offset){
			return -EFBIG;
		}
		if(file_loc != -1){
			printf("file size is: %d and %d is pointing\n", curr.files[file_loc].fsize, curr.files[file_loc].nStartBlock);
			size_read = read_data(curr.files[file_loc].nStartBlock, buf, offset, size);
			printf("sizeread is: %d buff is %s\n", size_read, buf);
		}
		else{
			return -ENOENT;
		}
		return size_read;
			
	}

	return -EISDIR;
}

/* 
 * Write size bytes from buf into file starting from offset
 *
 */
static int cs1550_write(const char *path, const char *buf, size_t size, 
			  off_t offset, struct fuse_file_info *fi)
{
	
	(void) fi;
	char *buff = buf;
	char *directory = strtok(path, "./");
	char *filename = strtok(NULL, "./");
	char *ext = strtok(NULL, ".");
        int path_type;
        path_type = path_name(directory,filename,ext);
	if(path_type >= 2){//Right place for a file
		int directory_place = dir_exists(directory);
		int file_loc;
		cs1550_directory_entry curr;

		if(directory_place == -1){
			return -EISDIR;
		}

		get_dir(&curr, directory_place);
		file_loc = get_file_location(&curr, filename, ext, path_type);
		printf("file location: %d\n", file_loc);
		printf("current file specs: file size: %d, location: %d  \n\n\n",curr.files[file_loc].fsize, curr.files[file_loc].nStartBlock);
		printf("offset at %d filesize is %d\n", offset, curr.files[file_loc].fsize);
		if(curr.files[file_loc].fsize < offset){
			return -EFBIG;
		}
		
		if(file_loc != -1){
			int size_written = write_data(curr.files[file_loc].nStartBlock, buff, offset, size);
			curr.files[file_loc].fsize = offset + size_written;
			printf("new size is: %d\n",curr.files[file_loc].fsize);
			write_dir_place(&curr, directory_place);
			printf("size written is: %d\n\n\n", size_written);
			if(size_written <= 0){
				return 0;
			}
			else{
				return size_written;
			}
		}
		else{
			return -ENOENT;
		}
			
	}	
	//check to make sure path exists
	//write data
	//set size (should be same as input) and return, or error

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

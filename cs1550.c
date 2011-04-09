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
	if(seekCode == sizeof(cs1550_directory_entry)){
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
*If the path is a file, return 2.
*/
static int path_name(char *directory, char *filename, char *extension){
	int ret = 0;
	if(strlen(directory) > 0){
		ret = 1;
	}
	if(strlen(filename) > 0){
		ret = 2;
	}
	return ret;
}
/*
 * Called whenever the system wants to know the file attributes, including
 * simply whether the file exists or not. 
 *
 * man -s 2 stat will show the fields of a stat structure
 */
static int cs1550_getattr(const char *path, struct stat *stbuf)
{
	int res = 0;
	cs1550_directory_entry f;
	get_dir(&f, 5);
	memset(stbuf, 0, sizeof(struct stat)); 
	//is path the root dir?
	if (strcmp(path, "/") == 0) {
		stbuf->st_mode = S_IFDIR | 0755;
		stbuf->st_nlink = 2;
	} else {

	//Check if name is subdirectory
	/* 
		//Might want to return a structure with these fields
		stbuf->st_mode = S_IFDIR | 0755;
		stbuf->st_nlink = 2;
		res = 0; //no error
	*/

	//Check if name is a regular file
	/*
		//regular file, probably want to be read and write
		stbuf->st_mode = S_IFREG | 0666; 
		stbuf->st_nlink = 1; //file links
		stbuf->st_size = 0; //file size - make sure you replace with real size!
		res = 0; // no error
	*/

		//Else return that path doesn't exist
		res = -ENOENT;
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

	if (strcmp(path, "/") != 0){
		int i = 0;
		filler(buf, ".", NULL,0);
		filler(buf, "..", NULL, 0);
		while(get_dir(&curr_dir, i) != 0){
			if(curr_dir.nFiles == -1){
				continue;
		 	}
			filler(buf, curr_dir.dname, NULL, 0);
		}
		ret = 0;
	}
	else{
		char *directory = (char *)malloc(strlen(path)+1);
		char *filename = (char *)malloc(strlen(path)+1);
		char *extension = (char *)malloc(strlen(path)+1);
		int path_type;
		sscanf(path, "/%[^/]/%[^.].%s", directory, filename, extension);
		path_type = path_name(directory,filename,extension);
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
		free(directory);
		free(filename);
		free(extension);
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
        (void) path;
        (void) mode;


	return 0;
}

/* 
 * Removes a directory.
 */
static int cs1550_rmdir(const char *path)
{
        char *directory = (char *)malloc(strlen(path)+1);
        char *filename = (char *)malloc(strlen(path)+1);
        char *extension = (char *)malloc(strlen(path)+1);
        int path_type;
	int ret = 0;
        sscanf(path, "/%[^/]/%[^.].%s", directory, filename, extension);
        path_type = path_name(directory,filename,extension);
	if(path_type == 1){
		int dir_place;
		cs1550_directory_entry curr;
		dir_place = dir_exists(directory);
		if(dir_place == -1){
			ret = -ENOENT;
		}
		else{
			get_dir(&curr, dir_place);
			if(curr.nFiles != 0){
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


        free(directory);
        free(filename);
        free(extension);



    return ret;
}

/* 
 * Does the actual creation of a file. Mode and dev can be ignored.
 *
 */
static int cs1550_mknod(const char *path, mode_t mode, dev_t dev)
{
	(void) path;
	(void) mode;
	(void) dev;
	return 0;
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

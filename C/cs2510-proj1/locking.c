#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#include "locking.h"
#define FREE 0
#define LOCKED 1

// Barrier macros
#define ALLOW_THREAD_THROUGH 0
#define WAIT_FOR_OTHER_THREADS 1

//rw lock macros
#define READER_LOCKED 1
#define WRITER_LOCKED 2
#define WRITER_REQUESTED_LOCK 3

/* Exercise 1:
 *     Basic memory barrier
 */

void mem_barrier() {
	asm volatile("mfence"
			:
			:
			:"memory");
}


/* Exercise 2: 
 *     Simple atomic operations 
 */

void atomic_sub( int * value, int dec_val) {
	//printf("Value before: %d\n", *value);
	asm("lock       ;\n"
		"sub %1,%0 ;\n"
		: "=m" (*value)
		: "r" (dec_val), "m" (*value)
		:
		);
	//printf("Value after: %d\n", *value);
	//*value = result;

}

void atomic_add( int * value, int inc_val) {
	asm("   lock       ;\n"
		"   addl %1,%0 ;\n"
		: "=m" (*value)
		: "r" (inc_val), "m" (*value)
		:
		);
}



/* Exercise 3:
 *     Spin lock implementation
 */


/* Compare and Swap
 * Returns the value that was stored at *ptr when this function was called
 * Sets '*ptr' to 'new' if '*ptr' == 'expected'
 */

unsigned int compare_and_swap(unsigned int * ptr, unsigned int expected,  unsigned int new) {
	unsigned int old_ptr = 0;
	unsigned int acquired_lock = 0;
    asm("mov %4, %%eax;\n"
    	"  lock   ;\n"
    	"  cmpxchg  %3, %0  ;\n"
    	"  jnz WRONG;    \n"
    	"  movl $1, %1 ;   \n"
    	"WRONG:  \n"
    	"  movl %%eax, %2"
    	: "=m"(*ptr), "=m"(acquired_lock), "=m"(old_ptr)
    	: "r"(new),"m"(expected), "m"(*ptr)
    	: "%eax", "memory"
    	);
    if(acquired_lock){
    	return expected;
    }
    else{
    	return old_ptr;    	
    }
}

void spinlock_init(struct spinlock * lock) {
	lock->state = FREE;
}

void spinlock_lock(struct spinlock * lock) {

	// Continue to check and lock until the state is FREE
	int lock_state = 0;
	do{
		lock_state = compare_and_swap(&(lock->state), FREE, LOCKED);
	}
	while(lock_state != FREE);
}


void spinlock_unlock(struct spinlock * lock) {
	lock->state = FREE;
}


/* Exercise 4:
 *     Barrier operations
 */


/* return previous value */
int atomic_add_ret_prev(int * value, int inc_val) {
	unsigned int old;
	unsigned int new;
	do{
		old = (unsigned int)*value;
		new = old + inc_val;

	}while(compare_and_swap((unsigned int *)value, old, new) != old);
    return (int)old;
}

void barrier_init(struct barrier * bar, int count) {
	bar->threads_enqueued = 0;
	bar->thread_threshold = count;
	bar->state = WAIT_FOR_OTHER_THREADS;
}

void barrier_wait(struct barrier * bar) {
	
	// Wait until all the previous threads are through the original barrier
	while(bar->state == ALLOW_THREAD_THROUGH);


	//Add one to the thread queue, and if it is the last one, allow all threads through.
	int current_thread_queue = atomic_add_ret_prev(&(bar->threads_enqueued), 1);
	if(current_thread_queue + 1 == bar->thread_threshold){
		bar->state = ALLOW_THREAD_THROUGH;
	}

	//Wait for all other threads to get to barrier.
	while(bar->state == WAIT_FOR_OTHER_THREADS);


	current_thread_queue = atomic_add_ret_prev(&(bar->threads_enqueued), -1);
	// The last thread went through the barrier, now we are back in init state.
	if(current_thread_queue == 1){
		bar->state = WAIT_FOR_OTHER_THREADS;
	}

}


/* Exercise 5:
 *     Reader Writer Locks
 */

void rw_lock_init(struct read_write_lock * lock) {
	lock->state = FREE;
	lock->readers = 0;
}


void rw_read_lock(struct read_write_lock * lock) {

	// Wait until writer is done, claim it if you can (or it is already in READER LOCKED)
	
	unsigned int previous_state;
	do{
		previous_state = compare_and_swap(&(lock->state), FREE, READER_LOCKED);
	}
	while(previous_state == WRITER_LOCKED || previous_state == WRITER_REQUESTED_LOCK);
	atomic_add(&(lock->readers), 1);
}

void rw_read_unlock(struct read_write_lock * lock) {
	int readers_left = atomic_add_ret_prev(&(lock->readers), -1);
	readers_left--;
	// Last reader
	if(readers_left == 0){

		lock->state = FREE;
	}
}

void rw_write_lock(struct read_write_lock * lock) {

	// If the previous state isn't Free, keep trying to acquire the lock.
	unsigned int previous_state;
	compare_and_swap(&(lock->state), READER_LOCKED, WRITER_REQUESTED_LOCK);
	do{
		previous_state = compare_and_swap(&(lock->state), FREE, WRITER_LOCKED);
	}
	while(previous_state != FREE);

}


void rw_write_unlock(struct read_write_lock * lock) {
	lock->state = FREE;
}



/* Exercise 6:
 *      Lock-free queue
 *
 * see: Implementing Lock-Free Queues. John D. Valois.
 *
 * The test function uses multiple enqueue threads and a single dequeue thread.
 *  Would this algorithm work with multiple enqueue and multiple dequeue threads? Why or why not?
 */


/* Compare and Swap 
 * Same as earlier compare and swap, but with pointers 
 * Explain the difference between this and the earlier Compare and Swap function
 */
uintptr_t compare_and_swap_ptr(uintptr_t * ptr, uintptr_t expected, uintptr_t new) {
	uintptr_t old_ptr = 0;
	unsigned int acquired_lock = 0;
    asm("mov %4, %%eax;\n"
    	"  lock   ;\n"
    	"  cmpxchg  %3, %0  ;\n"
    	"  jnz WRONG_POINTER;    \n"
    	"  movl $1, %1 ;   \n"
    	"WRONG_POINTER:  \n"
    	"  movl %%eax, %2"
    	: "=m"(*ptr), "=m"(acquired_lock), "=m"(old_ptr)
    	: "r"(new),"m"(expected), "m"(*ptr)
    	: "%eax", "memory"
    	);
    if(acquired_lock){
    	return expected;
    }
    else{
    	return old_ptr;    	
    }
}



void lf_queue_init(struct lf_queue * queue) {
	struct node * start_dummy = (struct node *)malloc(sizeof(struct node));
	start_dummy->next = NULL;

	queue->start_dummy = start_dummy;
	queue->head = start_dummy;
	queue->tail = start_dummy;
}

void lf_queue_deinit(struct lf_queue * queue) {
	struct node * curr = queue->start_dummy;
	while(curr != NULL){
		struct node * temp = curr;
		curr = curr->next;
		free(temp);
	}
}

void lf_enqueue(struct lf_queue * queue, int val) {
	struct node * record = (struct node *)malloc(sizeof(struct node));
	record->value = val;
	record->next = NULL;
	struct node * p;
	struct node * success;
	do{
		p = queue->tail;
		success = compare_and_swap_ptr(&(p->next), NULL, record);
		if(success != NULL){
			compare_and_swap_ptr(&(queue->tail), p, p->next);
		}

	} while(success != NULL);
	compare_and_swap_ptr(&(queue->tail), p, record);
}

int lf_dequeue(struct lf_queue * queue, int * val) {
	struct node * p;
	do {
		p = queue->head;
		if(p->next == NULL){
			return 0;
		}

	} while(compare_and_swap_ptr(&(queue->head), p, p->next));
    return 1;
}




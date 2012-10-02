#include <stdint.h>

/* Simple atomic operations */
void atomic_sub(int * dst, int dec_value);
void atomic_add(int * dst, int add_value);


/* Barriers */
int atomic_add_ret_prev(int * dst, int inc_value);

struct barrier {
	int state;
	int thread_threshold;
	int threads_enqueued;
};


void barrier_init(struct barrier * bar, int  count);
void barrier_wait(struct barrier * bar);



/* Spin locks */
struct spinlock {
	unsigned int state;
};

unsigned int compare_and_swap(unsigned int * ptr, unsigned int expected, unsigned int new);

void spinlock_init(struct spinlock * lock);
void spinlock_lock(struct spinlock * lock);
void spinlock_unlock(struct spinlock * lock);


/* Reader Writer Locks */
struct read_write_lock {
	unsigned int state;
	unsigned int readers;
};

void rw_lock_init(struct read_write_lock * lock);
void rw_read_lock(struct read_write_lock * lock);
void rw_read_unlock(struct read_write_lock * lock);
void rw_write_lock(struct read_write_lock * lock);
void rw_write_unlock(struct read_write_lock * lock);




/* Lock Free Queue */

struct node {
	struct node * next;
	int value;
};

struct lf_queue {
	struct node * head;
	struct node * tail;
	struct node * start_dummy;
};

uintptr_t compare_and_swap_ptr(uintptr_t * ptr, uintptr_t expected, uintptr_t new);

void lf_queue_init(struct lf_queue * queue);
void lf_queue_deinit(struct lf_queue * queue);
void lf_enqueue(struct lf_queue * queue, int new_val);
int lf_dequeue(struct lf_queue * queue, int * val);


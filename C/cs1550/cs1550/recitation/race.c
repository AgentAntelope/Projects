#include <stdio.h>
#include <pthread.h>
#include <errno.h>

long long shared;
int numthreads;

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
void* toRun(void * arg)
{
  int i;
  int myNum = (int) arg;
  
  for(i = 0; i < 1000000; i++)
  {
   pthread_mutex_lock(&mutex);
    shared++;
  pthread_mutex_unlock(&mutex);
  }

  return NULL;
}

int main(int argc, char* argv[])
{
  int i, rc;
  pthread_t* threads;

  if(argc < 2) { fprintf(stderr, "Specify # of threads on command line\n"); return 2;}

  numthreads = strtol(argv[1], NULL, 0);
  shared = 0;
  threads = (pthread_t*)malloc(sizeof(pthread_t) * numthreads);
  if(threads == NULL) { perror("Couldn't malloc:"); return 3;}

  for(i = 0; i < numthreads; i++)
  {
    rc = pthread_create(&threads[i], NULL, toRun, (void*) i);
    if(rc != 0) {fprintf(stderr, "Couldn't create thread: %s\n", strerror(rc));}
  }

  for(i = 0; i < numthreads; i++)
  {
    pthread_join(threads[i], NULL); // wait for all threads to finish
  }

  printf("Final result of shared var: %i\n", shared);

  free(threads);
  return 0;
}

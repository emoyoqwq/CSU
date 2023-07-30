#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <stdlib.h>

int getfib(int n){                     //generate fib sequence of first n numbers
    int r;
    int a1=0, a2=1;
    int nextTerm;
    for(int i=0;i<n;i++){
        printf("%d, ", a1);
        r = a1;
        nextTerm = a1+a2;
        a1 = a2;
        a2 = nextTerm;
    }
    printf("\n");
    return r;
}

int main(int argc, char *argv[]){
    int SIZE = 32;                      //size of the shared memory
    int a = atoi(argv[1]);              //the number of first n numbers of the sequence
    printf("Fibb[%d] : Number of terms in fibonacii series is %d\n", getpid(), a);      
    printf("Fibb[%d] : The first %d numbers of the Fibonacci sequence are:\n", getpid(),a);
    int result = getfib(a);             //result stores the last number of the sequence
    char shm_Fibb[] = "SHM_Fibb";       //get the name of the shared memory
    int shm_fd_fibb = shm_open(shm_Fibb, O_CREAT | O_RDWR, 0666);               //open the shared memory and write content in it
    void *shmFibbPtr = mmap(0, SIZE, PROT_WRITE, MAP_SHARED, shm_fd_fibb, 0);   //get the pointer points to the shared memory
    sprintf(shmFibbPtr, "%d", result);                                          //write content to the shared memory
    shm_unlink(shm_Fibb);                                                       //unlink the memory
    return 0;
}
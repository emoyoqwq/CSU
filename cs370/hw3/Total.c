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

int getSum(int n){                 //generate sum for first n numbers
    int sum = 0;
    for(int i=1;i<=n;i++){
        sum += i;
    }
    printf("%d\n", sum);
    return sum;
}
int main(int argc, char *argv[]){
    int SIZE = 32;                                      //size of the shared memory
    int a = atoi(argv[1]);                              //the number of n numbers of sequence
    printf("Total[%d] : Sum = ", getpid());             
    int result = getSum(a);                             //result stores the last number of the sequence
    char shm_Total[] = "SHM_Total";                     //name of the shared memory
    int shm_fd_total = shm_open(shm_Total, O_CREAT | O_RDWR, 0666);                             //open the shared memory
    void *shmTotalPtr = mmap(0, SIZE, PROT_WRITE, MAP_SHARED, shm_fd_total, 0);                 //get the pointer points to the shared memory
    sprintf(shmTotalPtr, "%d", result);                 //write content to the shared memory            
    shm_unlink(shm_Total);                              //unlink the memory
    return 0;
}
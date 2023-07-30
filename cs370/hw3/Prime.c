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

int getPrime(int n){         //get prime sequence of first n numbers
    int i=3, count=1, c;
    int r;
    if(n >= 1){
        printf("2, ");
    } 
    for(count;count<n;i++){
        for(c=2;c<i;c++){
            if(i%c == 0){
                break;
            }
        }
        if(c == i){
            printf("%d, ", i);
            count++;
            r = i;
        }
    }
    printf("\n");
    return r;
}
int main(int argc, char *argv[]){
    int SIZE = 32;                                                          //size of the shared memory
    int a = atoi(argv[1]);                                                  //get the number of first n numbers.
    printf("Prime[%d]: First %d prime numbers are:\n", getpid(), a);
    int result = getPrime(a);                                               //result stores the last number of the suqence
    char shm_Prime[] = "SHM_Prime";                                         //get the name of the shared memory
    int shm_fd_prime = shm_open(shm_Prime, O_CREAT | O_RDWR, 0666);         //open the shared memory
    void *shmPrimePtr = mmap(0, SIZE, PROT_WRITE, MAP_SHARED, shm_fd_prime, 0);     //get the pointer points to the shared memory and writes content in it.
    sprintf(shmPrimePtr, "%d", result);                                     //write the concent to shared memory
    shm_unlink(shm_Prime);                                                  //unlink with the shared memory
    return 0;
}
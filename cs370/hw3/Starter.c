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

int main(int argc, char *argv[]){
    if(argc != 2){                                  //check if there's file passed      
        printf("need a file to read\n");
        exit(1);
    }
    char *fname = argv[1];                          //create a pointer points to file
    int SIZE = 32;                                  //create the size of shared memory
    char number[16];                                //create a char array to store the sum passed from CharacterReader                     
    char buf[16];                                   //create a char array to store the write end for CharacterReader
    int fd[2];                                      //create a int array of size 2 for pipe between Starter and CharacterReader

    if(pipe(fd) < 0){                               //check if pipe created
        printf("Pipe failed.\n");
    }
    sprintf(buf, "%d" , fd[1]);                     //store the write end of the pipe in buf
    pid_t pidReader = fork();                       //fork a child process to implement CharacterReader
    if(pidReader == 0){                                                 //child process
        execlp("CharacterReader", "Character", fname, buf, NULL);       //let child process run CharacterReader.c
    }
    else{
        close(fd[1]);                               //close the write end of the pipe
        int status;                                 
        wait(&status);                              //wait child process finish
        int received_count = read(fd[0], number, sizeof(number));   
        number[received_count] = '\0';                              //ensure the array ends
        read(fd[0], number, sizeof(number));        //read the content in the pipe and store in the number
        close(fd[0]);                               //close the read end of the pipe
        printf("Starter[%d]: contents read from the read end pipe: %s\n", getpid(), number);
    }

    char shm_Fibb[] = "SHM_Fibb";                                       //shared memory name for Fibb
    int shm_fd_fibb = shm_open(shm_Fibb, O_CREAT | O_RDWR, 0666);       //create the shared memory
    ftruncate(shm_fd_fibb, SIZE);                                       //set the size of the shared memory
    void *shmFibbPtr = mmap(0, SIZE, PROT_READ, MAP_SHARED, shm_fd_fibb, 0);                        //get pointer pointes to the shared memory
    printf("Starter[%d]: Created shared memory \"SHM_Fibb\" with FD: %d\n", getpid(), shm_fd_fibb); 
    
    char shm_Prime[] = "SHM_Prime";                                         //shared memory name for Prime
    int shm_fd_prime = shm_open(shm_Prime, O_CREAT | O_RDWR, 0666);         //create Prime shared memory
    ftruncate(shm_fd_prime, SIZE);                                          //set the size of Prime shared memory
    void *shmPrimePtr = mmap(0, SIZE, PROT_READ, MAP_SHARED, shm_fd_prime, 0);              //get pointer points to Prime shared memory
    printf("Starter[%d]: Created shared memory \"SHM_Prime\" with FD: %d\n", getpid(), shm_fd_prime);

    char shm_Total[] = "SHM_Total";                                         //name of the shared memory for Total
    int shm_fd_total = shm_open(shm_Total, O_CREAT | O_RDWR, 0666);         //create Total shared memory
    ftruncate(shm_fd_total, SIZE);                                          //set the size of Total shared memory
    void *shmTotalPtr = mmap(0, SIZE, PROT_READ, MAP_SHARED, shm_fd_total, 0);              //get pointer pointes to Total shared memory
    printf("Starter[%d]: Created shared memory \"SHM_Total\" with FD: %d\n", getpid(), shm_fd_total);

    pid_t pid1 = fork();                                                    //fork the child process for Fibb.c
    if(pid1 == 0){                                                          //child process
        execlp("Fibb", "Fibb", number, NULL);                               //make the child process run Fibb.c
    }
    else{                           
        int a;
        wait(&a);                                                           //parent process wait for child process to finish running Fibb.c
    }

    pid_t pid2 = fork();                                                    //fork the child process for Prime.c
    if(pid2 == 0){                                                          //child process
        execlp("Prime", "Prime", number, NULL);                             //make the child process run Prime.c
    }
    else{                                                                   
        int b;
        wait(&b);                                                           //parent process wait for child process to finish running Prime.c
    }

    pid_t pid3 = fork();                                                    //fork the child process run Total.c
    if(pid3 == 0){                                                          //child process
        execlp("Total", "Total", number, NULL);                             //make the child process run Total.c
    }   
    else{
        int c;
        wait(&c);                                                           //parent process wait for child process to finish running Total.c
    }

    printf("Starter[%d]: Fibb last number:  %s\n", getpid(), shmFibbPtr);
    printf("Starter[%d]: Prime last number: %s\n", getpid(), shmPrimePtr);
    printf("Starter[%d]: Total last number: %s\n", getpid(), shmTotalPtr);
    
    return 0;

}
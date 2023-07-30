#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
# define MAXCHAR 10

int main(int argc, char *argv[]){
    if(argc != 2){                         //check if there is an argument
        printf("need a file to read\n");
        exit(1);
    }
    char *fname = argv[1];
    FILE *in = fopen(fname, "r");
    char buf[MAXCHAR];
    int fibb, prime, total;
    if(in == NULL){                               //check if open the file successfully
        printf("can't open the file, pls enter a file name\n");
        exit(0);
    }
    while(fgets(buf, MAXCHAR, in)){         //read the file.
        pid_t pid1 = fork();
        if(pid1 == 0){                      //child process
            execlp("Fibb", "Fibb", buf, NULL);                //make child process run Fibb.c
        }
        else{
            printf("Starter[%d] : Forked process with ID %d.\n", getpid(), pid1);
            printf("Starter[%d]: Waiting for process [%d].\n", getpid(), pid1);
            int a;
            wait(&a);
            int result = WEXITSTATUS(a);                //get return value of child process
            fibb = result;
            printf("Starter: Child process %d returned %d.\n", pid1, result);
        }

        pid_t pid2 = fork();
        if(pid2 == 0){                                   //second child process
            execlp("Prime", "Prime", buf, NULL);           //make child process run Prime.c
        }
        else{
            printf("Starter[%d] : Forked process with ID %d.\n", getpid(), pid2);
            printf("Starter[%d]: Waiting for process [%d].\n", getpid(), pid2);
            int b;
            wait(&b);
            int result = WEXITSTATUS(b);                //get return value of child process
            prime = result;                              
            printf("Starter: Child process %d returned %d.\n", pid2, result);
        }

        pid_t pid3 = fork();
        if(pid3 == 0){
            execlp("Total", "Total", buf, NULL); 
        }
        else{
            printf("Starter[%d] : Forked process with ID %d.\n", getpid(), pid3);
            printf("Starter[%d]: Waiting for process [%d].\n", getpid(), pid3);
            int c;
            wait(&c);
            int result = WEXITSTATUS(c);        
            total = result;
            printf("Starter: Child process %d returned %d.\n", pid3, result);
        }
    }
    fclose(in);
    printf("fibb:  %d\n", fibb);
    printf("Prime: %d\n", prime);
    printf("total Count:    %d\n", total);
    return 0;
}
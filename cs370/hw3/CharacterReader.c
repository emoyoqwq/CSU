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

# define MAXCHAR 10

int getSum(char *file){
    char buf[MAXCHAR];
    FILE *in = fopen(file, "r");
    int result = 0;
    if(in == NULL){                               //check if open the file successfully
        printf("can't open the file, pls enter a file name\n");
        exit(0);
    }
    while(fgets(buf, MAXCHAR, in)){                 //get content of the file
        result += atoi(buf);                        //calculate the sum of contents in the file and stores in the result
    }
    fclose(in);                                     //close the file
    return result;
}

int main(int argc, char *argv[]){
    char *filename = argv[1];                       //get the filename 
    char writeEnd = atoi(argv[2]);                  //get the write end of the pipe
    char message[16];                               //create a char array to store the sum
    int sum = getSum(filename);                     //get the sum of contents in the file
    sprintf(message, "%d", sum);                    //stroe the sum in message
    write(writeEnd, message, strlen(message) + 1);  //write message to the pipe
    close(writeEnd);                                //close the write end
    return 0;   
}
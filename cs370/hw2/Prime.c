#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

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
    int a = atoi(argv[1]);
    printf("Prime[%d]: First %d prime numbers are:\n", getpid(), a);
    int result = getPrime(a);
    return result;
}
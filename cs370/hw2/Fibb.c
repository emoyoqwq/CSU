#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

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
    int a = atoi(argv[1]);
    printf("Fibb[%d] : Number of terms in fibonacii series is %d\n", getpid(), a);
    printf("Fibb[%d] : The first %d numbers of the Fibonacci sequence are:\n", getpid(),a);
    int result = getfib(a);
    return result;
}
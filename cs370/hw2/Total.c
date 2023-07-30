#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int getSum(int n){                 //generate sum for first n numbers
    int sum = 0;
    for(int i=1;i<=n;i++){
        sum += i;
    }
    printf("%d\n", sum);
    return sum;
}
int main(int argc, char *argv[]){
    int a = atoi(argv[1]);
    printf("Total[%d] : Sum = ", getpid());
    int result = getSum(a);
    return result;
}
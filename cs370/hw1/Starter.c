#include <stdio.h>
#include <stdlib.h>
#include "Executor.h"


int main(int argc, char *argv[]){
    int seed = atoi(argv[1]);
    srand(seed);
    printf("[Starter]: With seed: %d\n", seed);
    double running_ratio = get_running_ratio();
    printf("[Starter]: Running ratio: %f\n", running_ratio);
    return 0;
}
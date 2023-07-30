#include <stdio.h>
#include <stdlib.h>
#include "Executor.h"

int get_iteration_no(int rand){
    return (rand % 50 + 50);
}
int get_arr_size(int rand){
    return (rand % 50 + 100);
}
   

char get_arr_val(int rand){
    return (char)(rand % 26 + 65);
}

float ratio(char *arr, int size, int *maxCountPointer){
    int vowel = 0;
    int con = 0;
    for(int i=0;i<size;i++){
        if(arr[i] == 'A' || arr[i] == 'E' || arr[i] == 'I' || arr[i] == 'O' || arr[i] == 'U'){
            vowel++;
        }
        else{
            con++;
        }
    }
    if(*maxCountPointer < vowel){                           //get max vowelcount
        *maxCountPointer = vowel;
    }

    
    return (float)vowel/con; 
}
double get_running_ratio(){
    int iteration_no = get_iteration_no(rand());
    printf("[Executor]: Number of iterations is %d\n", iteration_no);
    
    double sumRatio = 0;
    float rate[iteration_no];
    int *pointer;
    int maxCount;
    pointer = &maxCount;
    int result;                    //result of iteration with maxcount vowel
    int temp = 0;                  //temp max vowel conut
    char* buffer;                  

    for(int i=0;i<iteration_no;i++){
        int arrSize = get_arr_size(rand());             //get size for each ieration
        buffer = (char*)malloc(arrSize*sizeof(char));
        if(buffer == NULL){                             //check if allocate memory successfully
            exit(1);
        }
        for(int j=0;j<arrSize;j++){                     //change int array to char array
            buffer[j] = get_arr_val(rand());
        }
        rate[i] = ratio(buffer, arrSize, pointer);
        if(temp < maxCount){                            //pick the maxcount vowel
            result = i;
            temp = maxCount;
        }
        free(buffer);                                   //clear memory

    }
    for(int i=0;i<iteration_no;i++){
        sumRatio += (double)rate[i];
    }
    printf("[Executor]: iteration with maximum vowel count is %d\n", result+1);
    return sumRatio / iteration_no;
    

}


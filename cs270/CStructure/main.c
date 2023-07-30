 /** @mainpage 
   *  \htmlinclude "STRUCTS.html"
   */
/* CS270 
 *
 * Author: Mingtian Gong
 * Date:   2/12/2020
 */
#include<stdio.h>
#include <stdlib.h>
#include "struct.h"

int main(int argc, const char **argv){
	int number;
	scanf("%d", &number);
	
	ClassRoster c;
	c.numStudents = number;
	c.students = calloc(number, sizeof(Student*));

	for(int i=0;i<number;i++){
		readStudentAndEnroll(&(c.students[i]));
	}

	for(int i=0;i<number;i++){
		displayStudent(*c.students[i]);
		free(c.students[i]);
	}
	free(c.students);

	return 0;
}

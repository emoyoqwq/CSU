// P1 Assignment
// Author: Mingtian Gong
// Date:   8/31/2020
// Class:  CS270
// Email:  emoyo@rams.colostate.edu

// Include files
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

// example of a global array
double input[4];
double output[4];

// this function can be used as a guide for the funtions you will implement
void computeSphere(double radius, double *addressOfArea)
{
    // Compute volume
    double result =3.141593 * radius * radius;

    // Dereference pointer to return result
    *addressOfArea = result;
}

void computeTriangle(double side, double *addressOfArea)
{
    double result=0.433013*side*side;
    *addressOfArea=result;
}
void computeSquare(double side, double *addressOfArea)
{
    double result=side*side;
    *addressOfArea=result;
}
void computePentagon(double side, double *addressOfArea)
{
    double result=1.720477*side*side;
    *addressOfArea=result;
}
int main(int argc, char *argv[])
{
    // Check number of arguments
    if (argc !=5) {
        printf("usage: ./P1 <double> <double> <double> <double>\n");
        return EXIT_FAILURE;
    }

    // Parse arguments
    double radius = atof(argv[1]);
    double st=atof(argv[2]);
    double ss=atof(argv[3]);
    double sp=atof(argv[4]);
    
    // Local variable
    double area;
    double areaOfTriangle;
    double areaOfSquare;
    double areaOfPentagon;
    // Call function
    computeSphere(radius, &area);
    computeTriangle(st, &areaOfTriangle);
    computeSquare(ss, &areaOfSquare);
    computePentagon(sp, &areaOfPentagon);
    
    // Print volume
    printf("CIRCLE, radius = %.5f, area = %.5f.\n", radius, area);
    printf("TRIANGLE, length = %.5f, area = %.5f.\n", st, areaOfTriangle);
    printf("SQUARE, length = %.5f, area = %.5f.\n", ss, areaOfSquare);
    printf("PENTAGON, length = %.5f, area= %.5f.\n",sp, areaOfPentagon);

    // Return success
    return EXIT_SUCCESS;
}

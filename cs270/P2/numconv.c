/**
 *  @author  your name goes here
 */

#include <stdio.h>

/** @todo implement in <code>numconv.c</code> based on documentation contained 
 *  in <code>numconv.h</code>.
 */
char int2char (int radix, int value) {
  if(radix<2 || radix>36 || value<0 || value>radix-1){
     return '?';
  }else{
   if(value<10){
   char letter='0';
   return letter+value;
   }else{
    char letter='A';
    return letter+value-10;
    }    
   }
}

/** @todo implement in <code>numconv.c</code> based on documentation contained 
 *  in <code>numconv.h</code>.
 */
int char2int (int radix, char digit) {
  if(radix<2 || radix>36){
    return -1;
  }
  if(digit>='0' && digit<='9'){
    int a=(int)digit-(int)'0';
    if(a>radix-1){
    return -1;
    }
    else return a;
  }
  if(digit>='a' && digit<='z'){
    int a=(int)digit-(int)'a'+10;
    if(a>radix-1){
       return -1;
    }else return a;
  }
  if(digit>='A' && digit <='Z'){
    int a=(int)digit-(int)'A'+10;
    if(a>radix-1){
      return -1;
    }else return a;
  }else return -1;
}

/** @todo implement in <code>numconv.c</code> based on documentation contained 
 *  in <code>numconv.h</code>.
 */
void divRem (int numerator, int divisor, int* quotient, int* remainder) {
*quotient=numerator/divisor;
*remainder=numerator%divisor;
}

/** @todo implement in <code>numconv.c</code> based on documentation contained 
 *  in <code>numconv.h</code>.
 */
int ascii2int (int radix, int valueOfPrefix) {
  char a=getchar();
  if(a!='\n'){
  return ascii2int(radix, radix*valueOfPrefix+char2int(radix, a));
  }else return valueOfPrefix;
}

/** @todo implement in <code>numconv.c</code> based on documentation contained 
 *  in <code>numconv.h</code>.
 */
void int2ascii (int radix, int value) {
    if(value>=radix){
     int2ascii(radix,value/radix);
    }   
    putchar(int2char(radix,value%radix));
}

/** @todo implement in <code>numconv.c</code> based on documentation contained 
 *  in <code>numconv.h</code>.
 */
double frac2double (int radix) {
  char a=getchar();
  double n=0.0;
  if(a=='\n'){
    return n;  
  }else{
    n=(1.0/radix)*(char2int(radix,a)+frac2double(radix));
  }return n;
  
}


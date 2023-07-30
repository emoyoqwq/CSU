#include "Debug.h"
#include "iFloat.h"

/** @file iFloat.c
 *  @brief You will modify this file and implement nine functions
 *  @details Your implementation of the functions defined in iFloat.h.
 *  You may add other function if you find it helpful. Added function
 *  should be declared <b>static</b> to indicate they are only used
 *  within this file.
 *  <p>
 *  @author <b>Your name</b> goes here
 */

/* declaration for useful function contained in testFloat.c */
const char* getBinary (iFloat_t value);

/** @todo Implement based on documentation contained in iFloat.h */
iFloat_t floatGetSign (iFloat_t x) {
  return ((x & 0x8000) >> 15); /* implement this */
}

/** @todo Implement based on documentation contained in iFloat.h */
iFloat_t floatGetExp (iFloat_t x) {
  return ((x &0x7C00) >> 10); /* implement this */
}

/** @todo Implement based on documentation contained in iFloat.h */
iFloat_t floatGetVal (iFloat_t x) {
	int a = floatGetSign(x);
	int b=((x | 0x0400) & 0x07FF);
	if(!a){
		return b;
	}
	return (~b+1);
}

/** @todo Implement based on documentation contained in iFloat.h */
void floatGetAll(iFloat_t x, iFloat_t* sign, iFloat_t*exp, iFloat_t* val) {
	*sign = floatGetSign(x);
	*exp = floatGetExp(x);
	*val = floatGetVal(x);
}

/** @todo Implement based on documentation contained in iFloat.h */
iFloat_t floatLeftMost1 (iFloat_t bits) {
	for(int i=15;i>0;i--){
		int value = (bits & (1 << i)) != 0;
		if(value == 1){
			return i;
		}
	}
	return -1;
}

/** @todo Implement based on documentation contained in iFloat.h */
iFloat_t floatAbs (iFloat_t x) {
	return (x & 0x7FFF);
}

/** @todo Implement based on documentation contained in iFloat.h */
iFloat_t floatNegate (iFloat_t x) {
	if(x > 0 || x < 0 ){
		return x ^ 0x8000;
	}
	else{
		return x;
	}
}

/** @todo Implement based on documentation contained in iFloat.h */
iFloat_t floatAdd (iFloat_t x, iFloat_t y) {
	if(x == 0 || y == 0){
		if(x != 0){
			return x;
		}
		if(y != 0){
			return y;
		}
		else{
			return 0;
		}
	}
	iFloat_t signx = -1, expx=-1, valx=-1;
	iFloat_t signy = -1, expy=-1, valy=-1;
	floatGetAll(x, &signx, &expx, &valx);
	floatGetAll(y, &signy, &expy, &valy);

	if(expx > expy){
		int a = expx - expy;
		expy += a;
		valy = valy >> a;
	}
	else if(expx < expy){
		int a = expy - expx;
		expx += a;
		valx = valx >> a;
	}
	
	iFloat_t exp = expy;
	iFloat_t val = valx+valy;
	iFloat_t sign = floatGetSign(val);

	iFloat_t mag = val;
	if(sign == 1){
		mag = ~val+1;
	}

	if(floatLeftMost1(mag) < 10){
		int a = 10 - floatLeftMost1(mag);
		mag = mag << a;
		exp -= a;
	}
	
	if(floatLeftMost1(mag) > 10){
		int a = floatLeftMost1(mag) - 10;
		mag = mag >> a;
		exp += a;
	}

	mag = mag ^ 0x0400;
	iFloat_t result = (sign << 15) | (exp << 10) | mag;
	return result;
  
}

/** @todo Implement based on documentation contained in iFloat.h */
iFloat_t floatSub (iFloat_t x, iFloat_t y) {
	return floatAdd(x, floatNegate(y));
}


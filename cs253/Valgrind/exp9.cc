#include <cstdlib>
int main() {
    long double *a = new long double [10];
    *a+=3; 
    delete[] a;
    return 0;
}

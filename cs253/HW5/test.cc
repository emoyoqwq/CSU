#include "Jail.h"
#include "Jail.h"      // I meant to do that.
#include <iostream>
#include <fstream>
#include <string> 
using namespace std;
 
int main() {
    Jail j("abreturn");
    for (size_t i=0; i<j.size(); i++)
        cout << j[i] << '\n';
    cout << j << '\n';
    return 0;
}

#include "Lexan.h"
#include "Lexan.h"      // I meant to do that.
#include <iostream>
 
using namespace std;
 
int main() {
   [[maybe_unused]] auto prog = R"(
        n  = a n += b C/=123 # z=zz
 
        ifn≤3returN1FI)";
 
    Lexan lex("a=3");
    for (size_t i=0; i<lex.size(); i++)
        cout << lex[i] << '\n';
    cout << lex << '\n';
//	cout << prog;
    return 0;
}

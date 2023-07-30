#include <iostream>
#include <sstream>
#include <string>
#include <stdio.h>

using namespace std;

class Number {
  public:
    Number() = default;
    Number(const Number &) = default;
    Number(int v) : value(v) {}
    Number &operator=(int n) { value = n; return *this; }
    operator int() const { return value; }
  private:
    int value = 0;			// default value unless otherwise given
};

istream &operator>>(istream &is, Number &rhs) {
    int n;
    is >> n;
    if(is.fail()){
	is.clear();
	string s;
	s+=is.get();
	while(isalpha(is.peek())){
		s+=is.get();
	}
		if(s == "one"){
			rhs = 1;
			s="";
		}
		else if(s == "two"){
			rhs = 2;
			s="";
		}
		else if(s == "three"){
			rhs = 3;
			s="";
		}
		else if(s == "four"){
			rhs = 4;
			s="";
		}
		else if(s == "five"){
			rhs = 5;
			s="";
		}	
	
    }
    else{
        rhs = n;
    }
    return is;
}


int main() {
    Number n = 666;
    istringstream ss("12 34 three 789 five");

    while (ss >> n)
	cout << n << ' ';
    cout << '\n';
}

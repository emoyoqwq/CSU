#include <iostream>

using namespace std;

class Foo {
  public:
    double datum;
};

int main() {
    cout << "sizeof(double) = " << sizeof(double) << '\n'
	 << "sizeof(Foo) = " << sizeof(Foo) << '\n';
    Foo *p = new Foo;
    Foo *q = new Foo;
    cout << "p=" << p << '\n'
	 << "q=" << q << '\n';
    ifstream in("/proc/self/statm");
int blah, memsize;
in >> blah >> blah >> blah >> blah >> blah >> memsize;
cout << "Memory use is " << memsize << " pages.\n";
    delete p;
    delete q;
    return 0;
}

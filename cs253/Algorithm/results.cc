#include <algorithm>
#include <iostream>
#include <list>
#include <string>
#include <vector>

using namespace std;

int main() {
    vector<short> pi = {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6,2,6,4};
    string alpha="abcdefghijklmnopqrstuvwxyz", digits="0123456789";
    char cbuf[100] = {'\0'};	// contains 100 zero chars
    list<int> primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    short powers[] = {1,2,4,8,16,32,64};
    int ibuf[100];

    cout << "Exercise 0\n";
	copy(alpha.begin(), alpha.end(), cbuf);
	cout << cbuf << '\n';


    cout << "Exercise 1\n";
	reverse(begin(cbuf), end(cbuf));
	for(auto a : cbuf){
		cout << a;
	}
	cout << '\n';


    cout << "Exercise 2\n";
	transform(primes.begin(), primes.end(), ibuf,
		[](int a) {return ++a; });
	for(int i=0;i<10;i++){
		cout << ibuf[i] << ",";
	}	
	cout << '\n';


    cout << "Exercise 3\n";
	if(any_of(primes.begin(), primes.end(), [](int i){ return i % 2 == 0;})){
		cout << "div 2\n";
	}
	if(any_of(primes.begin(), primes.end(), [](int i){ return i % 42 == 0;})){
		cout << "div 42\n";
	}


    cout << "Exercise 4\n";
	auto result = find(begin(primes), end(primes), 13);
	result++;
	cout << *result << '\n';


    cout << "Exercise 5\n";
	int a = count(pi.begin(), pi.end(), 3);
	cout << a << '\n';	
	

    cout << "Exercise 6\n";
	int b = count_if(pi.begin(), pi.end(), [](int i){ return i<5 == true;});
	cout << b << '\n';


    cout << "Exercise 7\n";
	auto element = max_element(pi.begin(), pi.end());
	cout << *element << '\n';


    cout << "Exercise 8\n";
	sort(pi.begin(), pi.end());
	for(auto a : pi){
		cout << a << " ";
	}
	cout << '\n';


    cout << "Exercise 9\n";
	auto position = lower_bound(pi.begin(), pi.end(), 7);
	cout << "index of 7 is: " << position - pi.begin() << '\n';


    cout << "Exercise 10\n";
	auto q = set_intersection(begin(pi), end(pi),
			 begin(powers), end(powers),
			 ibuf);
	for(auto i=begin(ibuf);i<q;i++){
		cout << *i << " ";
	}
	cout << '\n';

}

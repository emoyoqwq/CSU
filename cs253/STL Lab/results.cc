#include <iostream>
#include <vector>
#include <set>
#include <string>
#include <fstream>
using namespace std;

int main(){
    vector<int> a;
    int n;
    while(cin >> n){
         if(n==0) break;  
         a.push_back(n);
    }
    for(auto p : a){
       cout << p << '\n';
    }
    
    //above codes are for 1 & 2

    ifstream file("/etc/resolv.conf",ios::in);
       //open the file and only read it
    string s="";
    char t;
    while(file.get(t)){
         s+=t; //read the characters
    }
    multiset<char> x;
    for(char c : s){
       x.insert(c); //copy characters from string to multiset  
    }
    set<char> y;
    for(char c : x){
       y.insert(c);
    }
    for(char c : s){
       cout  << c;     //display the string
    }
    cout << '\n';   
    cout <<"the size of string is " << s.size() << '\n';  //display size of string
    for(char c : x){
       cout << c;     //display the multiset
    }
    cout << '\n';
    cout <<"the size of multiset is "<< x.size() << '\n';   //display size of multiset
    for(char c : y){
       cout << c;     //display the set
    }
    cout << '\n';
    cout <<"the size of set is " <<y.size() << '\n';   //display size of set
    return 0;
    //QUESTION 7
    //the sizes of the string and multiset are the same because the string is copied 
    //by the multiset. the size of set is different because set doesn't allow 
    //duplicate elements and there are many duplicates in the string. So the size of
    //set is smaller. 
    
}

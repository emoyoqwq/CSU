#include <iostream>
#include <string>
#include <cctype>
#include <stdexcept>
#include <stddef.h>
#include <vector>
#include "Jail.h"
using namespace std;


Jail::Jail(){
	cerr << "no argument!"<<endl;	
}

Jail::Jail(string s){
	before=s;
	analyze(before);
	
}

Jail::Jail(const Jail &j){
	after = j.after;
}

Jail& Jail::operator=(const Jail &j){
	after = j.after;
	return *this;	
}

Jail::~Jail(){
	
}

size_t  Jail::size(){
	return after.size(); 
}

bool Jail::empty(){
	if(after.empty())	return true;
	else	return false;
}

void Jail::clear(){
	after.clear();
}

void Jail::analyze(string input){
	string token;
	while(get_token(input, token)){
		if(token.size()!=0)	after.push_back(token);
	}
}

string& Jail::operator[](size_t n){
	if(n>after.size()-1){
		throw out_of_range("errorneous subscript! numeber of tokens in the object is "+after.size()); 
	}
	else{
		return after[n];
	}	
}

ostream& operator << (ostream& os, const Jail &j){
	for(auto s : j.after){
		os <<s<<",";
	}
	return os;
}

//get int token
string Jail::digit(string &t){
       string a="";
       int n=0;
       while(t.size() != 0){
		if(isdigit(t[0])){
			a += t[0];
			n++;
			t.erase(0,1);
		}else{
            		t=t.substr(n+1); 
            		return a;
          } 
       }
	if(a.size()>15){
		throw runtime_error("too many digits!");
	}
	else{
		return a;
	}  
}

//get alpha token
string Jail::letter(string &t){
       string a="";
       if(t[0]=='i'&&t[1]=='f'){
         a+=t[0];
         a+=t[1];
         t=t.substr(2);
         return a;
       }
       if(t[0]=='f'&&t[1]=='i'){
         a+=t[0];
         a+=t[1];
         t=t.substr(2);
	 return a;
       }
       if(t.substr(0,5)=="print"){
         a=t.substr(0,5);
         t=t.substr(5);
	 return a;
       }
       if(t.substr(0,6)=="return"){
	 a=t.substr(0.6);
	 t=t.substr(6);
         return a;
       }
       else{ 
       a=t[0];
       t=t.substr(1);
       return a;
       }
       return a;
}

//get symbol token
string Jail::symbol(string &t){
       string a = "";
        if(t.substr(0,2)=="+=") {a += "+="; t=t.substr(2); return a;}
        if(t.substr(0,2)=="-=") {a+="-="; t=t.substr(2); return a;}
	if(t.substr(0,2)=="/=") {a+="/="; t=t.substr(2); return a;}
        if(t.substr(0,2)=="*=") {a+="*="; t=t.substr(2); return a;}
        if(t.substr(0,2)=="!=") {a+="≠"; t=t.substr(2); return a;}
        if(t.substr(0,2)=="<=") {a+="≤"; t=t.substr(2); return a;}
	if(t.substr(0,2)==">=") {a+="≥"; t=t.substr(2); return a;}
        else if(t[0]=='=') {a+="="; t=t.substr(1); return a;}
        else if(t[0]=='<') {a+="<"; t=t.substr(1); return a;}
        else if(t[0]=='>') {a+=">"; t=t.substr(1); return a;}
	else {return a;}
        return a;
         
}
//get all the valid tokens
bool Jail::get_token(string &line, string &s){
       
       for(unsigned int i=0;i < line.size();i++){
         line[i] =  tolower(line[i]);
       }

       while(line.size()!=0){
       if(isdigit(line[0])){
         s = digit(line);
         return true;
       }
       else if(isalpha(line[0])!=0){
         s=letter(line);
	 return true;
       }
       else if(isspace(line[0])){
         line.erase(0,1);
       	 s.clear();     
         return true;	 
       }
       else if(line[0]=='#'){
         
         s.clear();
         return false;
       }
       else{
         s=symbol(line);
         if(s.size() == 0){
         throw runtime_error("Invalid token");
	 
	 return false;
         }else{
		return true;
	}
       }
       }
       return false;
}

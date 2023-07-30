#include <iostream>
#include <string>
#include <cctype>
#include <stdexcept>
#include <stddef.h>
#include <vector>
#include "Lexan.h"
using namespace std;


Lexan::Lexan(){
	cerr << "no argument!"<<endl;	
}

Lexan::Lexan(string s){
	before=s;
	analyze(before);
	
}

Lexan::Lexan(const Lexan &lex){
	after = lex.after;
}

Lexan& Lexan::operator=(const Lexan &lex){
	after = lex.after;
	return *this;	
}

Lexan::~Lexan(){
	
}

size_t  Lexan::size(){
	return after.size(); 
}

bool Lexan::empty(){
	if(after.empty())	return true;
	else	return false;
}

void Lexan::clear(){
	after.clear();
}

void Lexan::analyze(string input){
	string token;
	while(get_token(input, token)){
		if(token.size()!=0)	after.push_back(token);
	}
}

string& Lexan::operator[](size_t n){
	if(n>after.size()-1){
		throw out_of_range("errorneous subscript! numeber of tokens in the object is "+after.size()); 
	}
	else{
		return after[n];
	}	
}

ostream& operator << (ostream& os, const Lexan &lex){
	for(auto s:lex.after){
		os <<s<<",";
	}
	return os;
}

//get int token
string Lexan::digit(string &t){
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
string Lexan::letter(string &t){
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
string Lexan::symbol(string &t){
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
bool Lexan::get_token(string &line, string &s){
       
       for(unsigned int i=0;i < line.size();i++){
         line[i] =  tolower(line[i]);
       }

       while(line.size()!=0){
       if(isdigit(line[0])){
         s = digit(line);
         return true;
       }
       if(isalpha(line[0])!=0){
         s=letter(line);
	 return true;
       }
       if(isspace(line[0])){
         line.erase(0,1);
       	 s.clear();     
         return true;	 
       }
       if(line[0]=='#'){
         
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




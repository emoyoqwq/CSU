#include<iostream>
#include<string>
#include<cctype>
using namespace std;

string program_name;

//get int token
string digit(string &t){
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
       return a;  
}

//get alpha token
string letter(string &t){
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
string symbol(string &t){
       string a = "";
        if(t.substr(0,2)=="+=") {a += "+="; t=t.substr(2); return a;}
        if(t.substr(0,2)=="-=") {a+="-="; t=t.substr(2); return a;}
	if(t.substr(0,2)=="/=") {a+="/="; t=t.substr(2); return a;}
        if(t.substr(0,2)=="*=") {a+="*="; t=t.substr(2); return a;}
        if(t.substr(0,2)=="!=") {a+="!="; t=t.substr(2); return a;}
        if(t.substr(0,2)=="<=") {a+="<="; t=t.substr(2); return a;}
	if(t.substr(0,2)==">=") {a+=">="; t=t.substr(2); return a;}
        else if(t[0]=='=') {a+="="; t=t.substr(1); return a;}
        else if(t[0]=='<') {a+="<"; t=t.substr(1); return a;}
        else if(t[0]=='>') {a+=">"; t=t.substr(1); return a;}
	else {return a;}
        return a;
         
}
//get all the valid tokens
bool get_token(string &line, string &s){
       
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
         cout << "invalid token!" << endl;
	 exit(0);
	 return false;
         }else{
		return true;
	}
       }
       }
       return false;
}

//write all the valid tokens
void analyze(string input){
     for (string token;get_token(input, token);){
	if(token.size() != 0) cout << token << '\n';
     }
}

//Read all the tokens from the give istream
void analyze(istream &in){
     for (string line;getline(in, line);)
         analyze(line);
}

int main(int, char *argv[]){
    program_name=argv[0];
    analyze(cin);
    return 0;
}

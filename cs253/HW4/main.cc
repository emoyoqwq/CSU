#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <unistd.h>
#include <string.h>
using namespace std;

string program_name;


void getfigure(vector<string> &af,vector<string> f, char a, char d, string b, string s){
	for(unsigned int i=0;i<f.size();i++){
		for(unsigned int j=0;j<f[0].size();j++){
			int n=0;
			if(i==0 && j==0){
				if(f[f.size()-1][f[0].size()-1]==a) n++;
				if(f[f.size()-1][0]==a) n++;
				if(f[f.size()-1][1]==a) n++;
				if(f[0][f[0].size()-1]==a) n++;
				if(f[0][1]==a) n++;
				if(f[1][f[0].size()-1]==a) n++;
				if(f[1][0]==a) n++;
				if(f[1][1]==a) n++;
				char num='0'+n;
				if(f[0][0]==a){
					for(unsigned int u=0;u<s.size();u++){
						if(num==s[u]){
							af[0][0]=a;
							break;
						}
						else { af[0][0]=d; }
					}
				}else{
					for(unsigned int u=0;u<b.size();u++){
						if(num==b[u]){
							af[0][0]=a;
							break;
						}
						else { af[0][0]=d; }
					}	
				}
			}
			if(i==0 && j==f[0].size()-1){
				if(f[f.size()-1][f[0].size()-2]==a) n++;
				if(f[f.size()-1][f[0].size()-1]==a) n++;
				if(f[f.size()-1][0]==a) n++;
				if(f[0][f[0].size()-2]==a) n++;
				if(f[0][0]==a) n++;
				if(f[1][f[0].size()-2]==a) n++;
				if(f[1][f[0].size()-1]==a) n++;
				if(f[1][0]==a) n++;
				char num='0'+n;
				if(f[0][f[0].size()-1]==a){
					for(unsigned int u=0;u<s.size();u++){
						if(num==s[u]){
							af[0][af[0].size()-1]=a;
							break;
						}
						else { af[0][af[0].size()-1]=d; }
					}
				}else{
					for(unsigned int u=0;u<b.size();u++){
						if(num==b[u]){
							af[0][af[0].size()-1]=a;
							break;
						}
						else{ af[0][af[0].size()-1]=d; }
					}
				}
			}
			if(i==f.size()-1 && j==0){
				if(f[f.size()-2][f[0].size()-1]==a) n++;
				if(f[f.size()-2][0]==a) n++;
				if(f[f.size()-2][1]==a) n++;
				if(f[f.size()-1][f[0].size()-1]==a) n++;
				if(f[f.size()-1][1]==a) n++;
				if(f[0][f[0].size()-1]==a) n++;
				if(f[0][0]==a) n++;
				if(f[0][1]==a) n++;
				char num='0'+n;
				if(f[f.size()-1][0]==a){
					for(unsigned int u=0;u<s.size();u++){
						if(num==s[u]){
							af[af.size()-1][0]=a;
							break;
						}else{ af[af.size()-1][0]=d; }
					}
				}else{
					for(unsigned int u=0;u<b.size();u++){
						if(num==b[u]){
							af[af.size()-1][0]=a;
							break;
						}else{ af[af.size()-1][0]=d; }
					}
				}
			}
			if(i==f.size()-1 && j==f[0].size()-1){
				if(f[f.size()-2][f[0].size()-2]==a) n++;
				if(f[f.size()-2][f[0].size()-1]==a) n++;
				if(f[f.size()-2][0]==a) n++;
				if(f[f.size()-1][f[0].size()-2]==a) n++;
				if(f[f.size()-1][0]==a) n++;
				if(f[0][f[0].size()-2]==a) n++;
				if(f[0][f[0].size()-1]==a) n++;
				if(f[0][0]==a) n++;
				char num='0'+n;
				if(f[f.size()-1][f[0].size()-1]==a){
					for(unsigned int u=0;u<s.size();u++){
						if(num==s[u]){
							af[af.size()-1][af[0].size()-1]=a;
							break;
						}else{ af[af.size()-1][af[0].size()-1]=d; }
					}
				}else{
					for(unsigned int u=0;u<b.size();u++){
						if(num==b[u]){
							af[af.size()-1][af[0].size()-1]=a;
							break;
						}else{ af[af.size()-1][af[0].size()-1]=d; }
					}
				}
			}
			if(i==0 && j!=0 && j!=f[0].size()-1){
				if(f[f.size()-1][j-1]==a) n++;
				if(f[f.size()-1][j]==a) n++;
				if(f[f.size()-1][j+1]==a) n++;
				if(f[i][j-1]==a) n++;
				if(f[i][j+1]==a) n++;
				if(f[i+1][j-1]==a) n++;
				if(f[i+1][j]==a) n++;
				if(f[i+1][j+1]==a) n++;
				char num='0'+n;
				if(f[i][j]==a){
					for(unsigned int u=0;u<s.size();u++){
						if(num==s[u]){
							af[i][j]=a;
							break;	
						}else{ af[i][j]=d; }
					}
				}else{
					for(unsigned int u=0;u<b.size();u++){
						if(num==b[u]){
							af[i][j]=a;
							break;
						}else{ af[i][j]=d; }
					}
				}
			}
			if(i!=0 && i!= f.size()-1 && j==0){
				if(f[i-1][f[0].size()-1]==a) n++;
				if(f[i-1][j]==a) n++;
				if(f[i-1][j+1]==a) n++;
				if(f[i][f[0].size()-1]==a) n++;
				if(f[i][j+1]==a) n++;
				if(f[i+1][f[0].size()-1]==a) n++;
				if(f[i+1][j]==a) n++;
				if(f[i+1][j+1]==a) n++;
				char num='0'+n;
				if(f[i][j]==a){
					for(unsigned int u=0;u<s.size();u++){
						if(num==s[u]){
							af[i][j]=a;
							break;
						}else{ af[i][j]=d; }
					}
				}else{
					for(unsigned int u=0;u<b.size();u++){
						if(num==b[u]){
							af[i][j]=a;
							break;
						}else{ af[i][j]=d; }
					}
				}
			}
			if(i!=0 && i!=f.size()-1 && j==f[0].size()-1){
				if(f[i-1][j-1]==a) n++;
				if(f[i-1][j]==a) n++;
				if(f[i-1][0]==a) n++;
				if(f[i][j-1]==a) n++;
				if(f[i][0]==a) n++;
				if(f[i+1][j-1]==a) n++;
				if(f[i+1][j]==a) n++;
				if(f[i+1][0]==a) n++;
				char num='0'+n;
				if(f[i][j]==a){
					for(unsigned int u=0;u<s.size();u++){
						if(num==s[u]){
							af[i][j]=a;
							break;
						}else{ af[i][j]=d; }
					}
				}else{
					for(unsigned int u=0;u<b.size();u++){
						if(num==b[u]){
							af[i][j]=a;
							break;
						}else{ af[i][j]=d; }
					}
				}
			}
			if(i==f.size()-1 && j!=0 && j!=f[0].size()-1){
				if(f[i-1][j-1]==a) n++;
				if(f[i-1][j]==a) n++;
				if(f[i-1][j+1]==a) n++;
				if(f[i][j-1]==a) n++;
				if(f[i][j+1]==a) n++;
				if(f[0][j-1]==a) n++;
				if(f[0][j]==a) n++;
				if(f[0][j+1]==a) n++;
				char num='0'+n;
				if(f[i][j]==a){
					for(unsigned int u=0;u<s.size();u++){
						if(num==s[u]){
							af[i][j]=a;
							break;
						}else{ af[i][j]=d; }
					}
				}else{
					for(unsigned int u=0;u<b.size();u++){
						if(num==b[u]){
							af[i][j]=a;
							break;
						}else{ af[i][j]=d; }
					}
				}
			}
			if(i!=0 && i!=f.size()-1 && j!=0 && j!=f[0].size()-1){
				if(f[i-1][j-1]==a) n++;
				if(f[i-1][j]==a) n++;
				if(f[i-1][j+1]==a) n++;
				if(f[i][j-1]==a) n++;
				if(f[i][j+1]==a) n++;
				if(f[i+1][j-1]==a) n++;
				if(f[i+1][j]==a) n++;
				if(f[i+1][j+1]==a) n++;
				char num='0'+n;
				if(f[i][j]==a){
					for(unsigned int u=0;u<s.size();u++){
						if(num==s[u]){
							af[i][j]=a;
							break;
						}else{ af[i][j]=d; }
					}
				}else{
					for(unsigned int u=0;u<b.size();u++){
						if(num==b[u]){
							af[i][j]=a;
							break;
						}else{ af[i][j]=d; }
					}
				}
			}	 
		}
	}
}


void checkfigure(vector<string> f, string file, char a, char d){

	if(f.size()<2 || f[0].size()<2){
                cerr << "Error: "<< file << " input should at least be 2x2"<< endl;
                exit(0);
        }

	for(unsigned int i=0;i<f.size()-1;i++){
		if(f[i].size()!=f[i+1].size()){
			cerr << "Error: "<< file << " Innput has Inconsistent Line Lengths"<< endl;		//check consistent line lengths
			exit(0);
		}
	}

	for(unsigned int i=0;i<f.size();i++){
		for(unsigned int j=0;j<f[i].size();j++){
			if(f[i][j]!=a && f[i][j]!=d){		//check bad character
				cerr << "Error: "<< file <<" Input has BAD CHARACTER: " << f[i][j] << endl;
				exit(0);
			}
		}
	}
}

int main(int argc, char *argv[]){
	program_name=argv[0];
	string s;
	vector<string> figure;
	vector<string> prefigure;
	int opt;
	bool cellalive=false;
	bool celldead=false;
	string alive;
	string dead;
	char a='O';
	char d='.';
	string born="3";
	string survive="23";
	int b;
	int slash;
	int u;
	string condition;
	bool infinity=false;
	int numg=0;
	int numl=0;
	int numd=0;
	
		while((opt = getopt(argc, argv, "+ig:l:d:")) != -1){
			switch(opt){
				case 'g':
					numg++;
					
					
					if(numg>1){
						cout << "option g is given multiple times!" << '\n';
						exit(EXIT_FAILURE);
					}
					condition=optarg;
					b=condition.find("B");
					slash=condition.find("/");
					u=condition.find("S");	
					if(b==-1 || slash==-1 || u==-1){
						printf("invalid option argument! option is g, argument is: %s\n", optarg);
						exit(EXIT_FAILURE);
					}
					if(!(b < slash && slash < u)){
						printf("invalid option argument! option is g, argument is: %s\n", optarg);
					}
					born=condition.substr(b+1,slash-1);
                                        survive=condition.substr(u+1);
					if(born.size()>1){
						for(unsigned int i=0;i<born.size()-1;i++){
							if(born[i] >= born[i+1]){
								printf("invalid option argument! option is g, argument is: %s\n", optarg);
							cout << born;
								exit(EXIT_FAILURE);
							}
						}
					}
					if(survive.size()>1){
						for(unsigned int i=0;i<survive.size()-1;i++){
							if(survive[i] >= survive[i+1]){
								printf("invalid option argument! option is g, argument is: %s\n", optarg);
								exit(EXIT_FAILURE);
							}
						}
					}
					break;
				case 'l':
					numl++;
					
					
					if(numl>1){
						cout << "option l is given multiple times!" <<'\n';
						exit(EXIT_FAILURE);
					}
					if(strlen(optarg)!=1){
						printf("invalid option argument! option is l, argument is: %s\n", optarg);
						exit(EXIT_FAILURE);
					}
					cellalive=true;
					alive=optarg;
					a=alive[0];
					break;
				case 'd':
					numd++;
					
					
					if(numd>1){
						cout << "option d is given multiple times!" <<'\n';
						exit(EXIT_FAILURE);
					}
					if(strlen(optarg)!=1){
						printf("invalid option argument! option is d, argument is: %s\n", optarg);
						exit(EXIT_FAILURE);
					}
					celldead=true;
					dead=optarg;
					d=dead[0];
					break;
				case 'i':
					infinity=true;
					break;
				case '?':
					printf("bad option! opt is %s, argument is: %s\n", argv[optind-1], optarg);
					exit(EXIT_FAILURE);
					
			}
		}
	if(argc == optind+1){
		ifstream file(argv[optind]);
		if(!file){
			cerr << "Error: "<< argv[0]<<" Can't open the file "<< argv[optind]<<endl;
			return 1;
		}
		else{
			while(getline(file,s)){
				figure.push_back(s);
			}
				if(cellalive==true){
					for(unsigned int i=0;i<figure.size();i++){
						for(unsigned int j=0;j<figure[0].size();j++){
							if(figure[i][j]=='O'){
								figure[i][j]=alive[0];
							}
						}
					}
				}
				if(celldead==true){
					for(unsigned int i=0;i<figure.size();i++){
						for(unsigned int j=0;j<figure[0].size();j++){
							if(figure[i][j]=='.'){
								figure[i][j]=dead[0];
							}
						}
					}
				}	
		}
	}
	else{
		while(getline(cin,s)){
			figure.push_back(s);
		}
	}
	prefigure=figure;
	checkfigure(figure, program_name,a,d);
	do{
		getfigure(figure,prefigure,a,d,born,survive);
		for(unsigned int i=0;i<figure.size();i++){
			cout << figure.at(i) <<endl;
		}
		prefigure=figure;
	}while(infinity);
	return 0;
}

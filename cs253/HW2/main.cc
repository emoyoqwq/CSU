#include <iostream>
#include <string>
#include <vector>
#include <fstream>
using namespace std;

string program_name;


void getfigure(vector<string> &af,vector<string> f){
	for(unsigned int i=0;i<f.size();i++){
		for(unsigned int j=0;j<f[0].size();j++){
			int n=0;
			if(i==0 && j==0){
				if(f[f.size()-1][f[0].size()-1]=='O') n++;
				if(f[f.size()-1][0]=='O') n++;
				if(f[f.size()-1][1]=='O') n++;
				if(f[0][f[0].size()-1]=='O') n++;
				if(f[0][1]=='O') n++;
				if(f[1][f[0].size()-1]=='O') n++;
				if(f[1][0]=='O') n++;
				if(f[1][1]=='O') n++;
				if(f[0][0]=='O'){
					if(n==2 || n==3){
						af[0][0]='O';
					}
					else { af[0][0]='.'; }
				}else{
					if(n==3){
						af[0][0]='O';
					}
					else { af[0][0]='.'; }	
				}
			}
			if(i==0 && j==f[0].size()-1){
				if(f[f.size()-1][f[0].size()-2]=='O') n++;
				if(f[f.size()-1][f[0].size()-1]=='O') n++;
				if(f[f.size()-1][0]=='O') n++;
				if(f[0][f[0].size()-2]=='O') n++;
				if(f[0][0]=='O') n++;
				if(f[1][f[0].size()-2]=='O') n++;
				if(f[1][f[0].size()-1]=='O') n++;
				if(f[1][0]=='O') n++;
				if(f[0][f[0].size()-1]=='O'){
					if(n==2 || n==3){
						af[0][af[0].size()-1]='O';
					}
					else { af[0][af[0].size()-1]='.'; }
				}else{
					if(n==3){
						af[0][af[0].size()-1]='O';
					}
					else{ af[0][af[0].size()-1]='.'; }
				}
			}
			if(i==f.size()-1 && j==0){
				if(f[f.size()-2][f[0].size()-1]=='O') n++;
				if(f[f.size()-2][0]=='O') n++;
				if(f[f.size()-2][1]=='O') n++;
				if(f[f.size()-1][f[0].size()-1]=='O') n++;
				if(f[f.size()-1][1]=='O') n++;
				if(f[0][f[0].size()-1]=='O') n++;
				if(f[0][0]=='O') n++;
				if(f[0][1]=='O') n++;
				if(f[f.size()-1][0]=='O'){
					if(n==2 || n==3){
						af[af.size()-1][0]='O';
					}else{ af[af.size()-1][0]='.'; }
				}else{
					if(n==3){
						af[af.size()-1][0]='O';
					}else{ af[af.size()-1][0]='.'; }
				}
			}
			if(i==f.size()-1 && j==f[0].size()-1){
				if(f[f.size()-2][f[0].size()-2]=='O') n++;
				if(f[f.size()-2][f[0].size()-1]=='O') n++;
				if(f[f.size()-2][0]=='O') n++;
				if(f[f.size()-1][f[0].size()-2]=='O') n++;
				if(f[f.size()-1][0]=='O') n++;
				if(f[0][f[0].size()-2]=='O') n++;
				if(f[0][f[0].size()-1]=='O') n++;
				if(f[0][0]=='O') n++;
				if(f[f.size()-1][f[0].size()-1]=='O'){
					if(n==2 || n==3){
						af[af.size()-1][af[0].size()-1]='O';
					}else{ af[af.size()-1][af[0].size()-1]='.'; }
				}else{
					if(n==3){
						af[af.size()-1][af[0].size()-1]='O';
					}else{ af[af.size()-1][af[0].size()-1]='.'; }
				}
			}
			if(i==0 && j!=0 && j!=f[0].size()-1){
				if(f[f.size()-1][j-1]=='O') n++;
				if(f[f.size()-1][j]=='O') n++;
				if(f[f.size()-1][j+1]=='O') n++;
				if(f[i][j-1]=='O') n++;
				if(f[i][j+1]=='O') n++;
				if(f[i+1][j-1]=='O') n++;
				if(f[i+1][j]=='O') n++;
				if(f[i+1][j+1]=='O') n++;
				if(f[i][j]=='O'){
					if(n==2 || n==3){
						af[i][j]='O';	
					}else{ af[i][j]='.'; }
				}else{
					if(n==3){
						af[i][j]='O';
					}else{ af[i][j]='.'; }
				}
			}
			if(i!=0 && i!= f.size()-1 && j==0){
				if(f[i-1][f[0].size()-1]=='O') n++;
				if(f[i-1][j]=='O') n++;
				if(f[i-1][j+1]=='O') n++;
				if(f[i][f[0].size()-1]=='O') n++;
				if(f[i][j+1]=='O') n++;
				if(f[i+1][f[0].size()-1]=='O') n++;
				if(f[i+1][j]=='O') n++;
				if(f[i+1][j+1]=='O') n++;
				if(f[i][j]=='O'){
					if(n==2 || n==3){
						af[i][j]='O';
					}else{ af[i][j]='.'; }
				}else{
					if(n==3){
						af[i][j]='O';
					}else{ af[i][j]='.'; }
				}
			}
			if(i!=0 && i!=f.size()-1 && j==f[0].size()-1){
				if(f[i-1][j-1]=='O') n++;
				if(f[i-1][j]=='O') n++;
				if(f[i-1][0]=='O') n++;
				if(f[i][j-1]=='O') n++;
				if(f[i][0]=='O') n++;
				if(f[i+1][j-1]=='O') n++;
				if(f[i+1][j]=='O') n++;
				if(f[i+1][0]=='O') n++;
				if(f[i][j]=='O'){
					if(n==2 || n==3){
						af[i][j]='O';
					}else{ af[i][j]='.'; }
				}else{
					if(n==3){
						af[i][j]='O';
					}else{ af[i][j]='.'; }
				}
			}
			if(i==f.size()-1 && j!=0 && j!=f[0].size()-1){
				if(f[i-1][j-1]=='O') n++;
				if(f[i-1][j]=='O') n++;
				if(f[i-1][j+1]=='O') n++;
				if(f[i][j-1]=='O') n++;
				if(f[i][j+1]=='O') n++;
				if(f[0][j-1]=='O') n++;
				if(f[0][j]=='O') n++;
				if(f[0][j+1]=='O') n++;
				if(f[i][j]=='O'){
					if(n==2 || n==3){
						af[i][j]='O';
					}else{ af[i][j]='.'; }
				}else{
					if(n==3){
						af[i][j]='O';
					}else{ af[i][j]='.'; }
				}
			}
			if(i!=0 && i!=f.size()-1 && j!=0 && j!=f[0].size()-1){
				if(f[i-1][j-1]=='O') n++;
				if(f[i-1][j]=='O') n++;
				if(f[i-1][j+1]=='O') n++;
				if(f[i][j-1]=='O') n++;
				if(f[i][j+1]=='O') n++;
				if(f[i+1][j-1]=='O') n++;
				if(f[i+1][j]=='O') n++;
				if(f[i+1][j+1]=='O') n++;
				if(f[i][j]=='O'){
					if(n==2 || n==3){
						af[i][j]='O';
					}else{ af[i][j]='.'; }
				}else{
					if(n==3){
						af[i][j]='O';
					}else{ af[i][j]='.'; }
				}
			}	 
		}
	}
}


void checkfigure(vector<string> f, string file){

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
			if(f[i][j]!='.' && f[i][j]!='O'){		//check bad character
				cerr << "Error: "<< file <<" Input has BAD CHARACTER"<<endl;
				exit(0);
			}
		}
	}
}

int main(int agrc, char *argv[]){
	program_name=argv[0];
	string s;
	vector<string> figure;
	vector<string> prefigure;
	if(agrc > 1){
		ifstream file(argv[1]);
		if(!file){
			cerr << "Error: "<< argv[0]<<" Can't open the file "<< argv[1]<<endl;
			return 1;
		}
		else{
			while(getline(file,s)){
				figure.push_back(s);
			}	
		}
	}
	else{
		while(getline(cin,s)){
			figure.push_back(s);
		}
	}
	prefigure=figure;
	checkfigure(figure, program_name);
	getfigure(figure,prefigure);
	for(unsigned int i=0;i<figure.size();i++){
		cout << figure.at(i) <<endl;
	}
	return 0;
}

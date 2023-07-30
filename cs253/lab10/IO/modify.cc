#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main(int argc, char *argv[]) {
    if (argc != 2) {
	cerr << "usage: " << argv[0] << " infile outfile\n";
	return 1;
    }
    fstream file(argv[1], ios::in | ios::out);
    if (!file) {
	cerr << argv[0] << ": can't open " << argv[1] << '\n';
	return 2;
    }
	size_t pos=0;
    for (string line; getline(file, line); ) {
	    while(line.find("Trump", pos) != line.npos){
		pos=line.find("Trump", pos);
		file.seekp(pos);
		file << "Biden";	
	    }

    }
    file.close();

    return 0;
}

// Note that line.npos ≡ string::npos.
// It’s a static constant, part of the string class.
// It’s a number that means “Sorry, I couldn’t find that.”

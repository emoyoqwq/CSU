#include "Directory.h"
#include <iostream>

using namespace std;

// The directory iterator pre-fetches the name.  That is, readdir()
// is called when you first create the iterator, and upon ++.
//
// An end() iterator is designated by a null pointer for its DIR *.
// When readdir() fails, we set our pointer to null.

DirectoryIterator::DirectoryIterator(DIR *dp_arg) : dp(dp_arg) {
    ++*this;				// get that first entry
}
//Fix #3
//DirectoryIterator::DirectoryIterator(DIR *dp_arg, string match=""): dp(dp_arg){
//	++*this;
//}

DirectoryIterator &DirectoryIterator::operator++() {	// pre-increment
    while (auto p = readdir(dp)) {	// Read a directory entry.
	name = p->d_name;		// C-string ⇒ C++ string
//Fix #2 if(wanted(name, match)){
//		return *this;
//	 }
	if (name == ".")		// Stupid entry for current directory?
	    continue;			// Nobody wants that.
	if (name == "..")		// Stupid entry for parent directory?
	    continue;			// Nobody wants that.
	return *this;			// Everything else, I like!
    }
    dp = nullptr;			// readdir failed; don’t try again.
    return *this;
}

string DirectoryIterator::operator*() const {
    return name;			// Fetched in a previous ++.
}

bool DirectoryIterator::operator!=(const DirectoryIterator &rhs) const {
    return dp != rhs.dp;
}

//Fix #2
//Fix #3
//bool wanted(string s, string m){
//	if(m==""){
//		if(s == ".") return false;
//		else if(s == "..") return false;
//		else return true;
//	}else{
//		if(s.find(m) != string::npos){
//			return true;
//		}
//		return false;
//	}
//}



//////////////// Directory methods

Directory::Directory(const char dirname[]) : dp(opendir(dirname)) {
//Fix #1 
//if(!dp){
//	throw runtime_error("non-existant directory name");
//}
}

Directory::~Directory() {
    if (dp)				// Only if opendir() succeeded:
	closedir(dp);			//   should we close it.
}

Directory::iterator Directory::begin() const {
    return iterator(dp);
}

Directory::iterator Directory::end() const {
    return iterator();
}

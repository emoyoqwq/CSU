#ifndef DIRECTORY_H_INCLUDED
#define DIRECTORY_H_INCLUDED

#include <dirent.h>
#include <string>

// Normally, I’d have DirectoryIterator be a nested class, Directory::iterator,
// to avoid namespace pollution.  However, this is easier to read.

class DirectoryIterator {
  public:
    DirectoryIterator() = default;
    DirectoryIterator(DIR *);
    DirectoryIterator &operator++();
    std::string operator*() const;
    bool operator!=(const DirectoryIterator &) const;
  private:
    DIR *dp = nullptr;
    std::string name;
//Fix #3 string match;
//Fix #2    bool wanted(std::string s); 
};

class Directory {
  public:
    typedef DirectoryIterator iterator;
    Directory() = delete;		// not really necessary
    Directory(const char dirname[]);
    ~Directory();
    iterator begin() const;
    iterator end() const;
  private:
    DIR *dp;				// null if open failed
};

#endif /* DIRECTORY_H_INCLUDED */

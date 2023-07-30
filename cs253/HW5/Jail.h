#ifndef JAIL_H
#define JAIL_H
#include <string>
#include <stddef.h>
#include <iostream>
#include <vector>
class Jail{
        public:
        Jail();
        Jail(std::string s);
        Jail(const Jail& j);
        Jail& operator=(const Jail& j);
        ~Jail();
        size_t size();
        bool empty();
        void clear();
        void analyze(std::string s);
        std::string& operator[](size_t n);
        std::string digit(std::string &t);
        std::string letter(std::string &t);
        std::string symbol(std::string &t);
        bool get_token(std::string &line, std::string &s);
        friend std::ostream& operator << (std::ostream& os, const Jail &j);
        std::string before;
        std::vector<std::string> after;

};
#endif//JAIL__H


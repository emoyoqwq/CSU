#ifndef LEXAN_H
#define LEXAN_H
#include <string>
#include <stddef.h>
#include <iostream>
#include <vector>
class Lexan{
	public:
	Lexan();
	Lexan(std::string s);
	Lexan(const Lexan& lex);
	Lexan& operator=(const Lexan& lex);
	~Lexan();
	size_t size();
	bool empty();
	void clear();
	void analyze(std::string s);
	std::string& operator[](size_t n);
	std::string digit(std::string &t);
	std::string letter(std::string &t);
	std::string symbol(std::string &t);
	bool get_token(std::string &line, std::string &s);
	friend std::ostream& operator << (std::ostream& os, const Lexan &lex);
	std::string before;
	std::vector<std::string> after;
	
};
#endif//Lexan_H

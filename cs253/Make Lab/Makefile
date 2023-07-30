# This is a Makefile.  Its purpose is to create the program “small”.
#
# The rules, below, indicate what depends on what, and how to build
# things when they get out-of-date.  It is VERY important to know that
# what looks like eight leading spaces is really a leading tab
# character.  Leading spaces won’t work--it MUST be a tab character.
#
# For the following questions, do the command if there is one,
# and then answer the question.  If you need another line for you
# answer, continue it on the next line.
#
# As an example, I’ve already answered (1), which has nothing to do
# with anything else in this lab.
#
# Question 1 is worth 0.09 points; questions 2–14 are worth 0.07
# points each.
#
#  1) Command:  date +%A
#     Question: Describe the output of that command.
#     Answer:   It’s today’s day of the week.
#
#  2) Command:  make
#     Question: Describe what happens.  Don’t just write the commands;
#     		describe what each command does.
#     Answer:   g++ -c main.cc turns main.cc into main.o
#               g++ -c foo.cc turns foo.cc into foo.o
#               g++ main.o foo.o -o small merges  main.o and foo.o into small
# 
#  3) Command:  ./small
#     Question: What is the output?  Reproduce the output exactly.
#     Answer:   Hi from main at main.cc:15
#               Hi from foo at foo.cc:14
#
#  4) Command:  make
#     Question: Why did nothing much happen?
#     Answer:   small is up to date because nothing changed in main.o and foo.o
#
#  5) Command:  rm small; make
#     Question: What got remade, and why?
#     Answer:   small, because it got deleted and nothing changed in main.cc and foo.cc
#
#  6) Command:  rm foo.o; make 
#     Question: What got remade, and why?
#     Answer:   foo.o and small got remade because foo.o changed and small depends on
#		foo.o and main.o, so it got remade too.
#
#  7) Command:  make clean 
#     Question: What did that do?
#     Answer:   it removes small, main.o and foo.o
#
#  8) Command:  make 
#     Question: What got remade, and why?
#     Answer:   main.o, foo.o and small got remade because clean cleans object files
#     		and executable file. In this case, they are main.o, foo.o and small
#
#  9) Question: Why does main.o depend on foo.h?
#		I know that it’s because the Makefile says so.
#		Why was that dependency put into the Makefile?
#     Answer:   Because foo.h is included in mian.cc and main.cc calls function in
#     		foo.h, so that main.cc depends on foo.h, and mian.o depends on
#     		main.cc, which makes main.o depends on foo.h
#
# 10) Command:  Create a new function bar() in bar.cc & bar.h,
# 		similar to foo.cc.  Change main.cc to call the new
# 		bar() function, and type “make”.
#     Question: Why did it fail?
#     Answer:   Because there is undefined reference to 'bar()' in makefile.
#
# 11) Command:  Fix the Makefile; verify with “make” until it compiles.
#     Question: Describe your changes.  Don’t just show the changed
#     		lines--describe the purpose of each change.
#     Answer:   I add bar.o: bar.cc bar.h
#            	      		$(CXX) $(CXXFLAGS) -c bar.cc, to make object file bar.o.
#            	Then I add bar.h after main.o because bar.h is included in main.cc,
#            	so main.o depends on bar.h too.
#            	Finally I add bar.o after small to link all objects together.
#
# 12) Command:  Uncomment the CXXFLAGS= line, and type “make”.
#     Question: Why didn't anything happen?
#     Answer:   because small is still up to date because no files that it depends on got changed, so it didn't get remade. 
#
# 13) Command:  Force a rebuild with: make clean; make 
#     Question: What’s new about the commands executed?
#     Answer:   there are many flags appear for making each object like main.o, foo.o and bar.o and executable file
#     		small
#
# 14) Command: 	make clean; ls -l clean
#     Question: The target is “clean”, but no file “clean” got created.
#     		Why not?
#     Answer:   Because make clean clean the object files and executable files up, it doesn't create a file, neither
#               ls commond does.
#
# For extra fame & glory (but no extra points):
#
# 15) Command:  Consult the make manual at
# 		https://www.gnu.org/software/make/manual/make.html
#		and improve this Makefile by using $@ in the rule
#		that creates “small” itself.

# The CXX variable contains the name of the C++ compiler.  You can’t
# have plus signs in a Make variable, so X is a rotated plus sign.
CXX=g++

CXXFLAGS=-std=c++17 -Wall -Wextra -Wpedantic -Werror -Wfatal-errors -Winit-self -Wshadow -Wlogical-op -Wcomment -Wctor-dtor-privacy -Wold-style-cast -D_GLIBCXX_DEBUG -fno-diagnostics-show-option

small: main.o foo.o bar.o
	$(CXX) $(CXXFLAGS) main.o foo.o bar.o -o small

main.o: main.cc foo.h bar.h
	$(CXX) $(CXXFLAGS) -c main.cc

foo.o: foo.cc foo.h
	$(CXX) $(CXXFLAGS) -c foo.cc

bar.o: bar.cc bar.h
	$(CXX) $(CXXFLAGS) -c bar.cc

clean:
	-rm -f small *.o

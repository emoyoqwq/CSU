JC = javac
OUT_EXE = Coordinator
TAR_NAME = Mingtian-Gong-HW5.tar

build:
	$(JC) Coordinator.java
	$(JC) Buffer.java
	$(JC) Producer.java
	$(JC) Consumer.java

package:
	tar cvzf $(TAR_NAME) Makefile README.txt Coordinator.java Buffer.java Producer.java Consumer.java

clean:
	-rm *.class
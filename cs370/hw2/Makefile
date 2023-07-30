CC = gcc
OUT_EXE = Starter
TAR_NAME = Mingtian-Gong-HW2.tar

build:
	$(CC) -o Prime Prime.c
	$(CC) -o Total Total.c
	$(CC) -o Fibb Fibb.c
	$(CC) -o $(OUT_EXE) Starter.c

package:
	tar cvzf $(TAR_NAME) Makefile README.txt Starter.c Fibb.c Prime.c Total.c

clean:
	-rm Prime Total Fibb $(OUT_EXE)
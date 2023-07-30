CC = gcc
OUT_EXE = Starter
TAR_NAME = Mingtian-Gong-HW3.tar

build:
	$(CC) -lrt -o Prime Prime.c
	$(CC) -lrt -o Total Total.c
	$(CC) -lrt -o Fibb Fibb.c
	$(CC) -lrt -o CharacterReader CharacterReader.c
	$(CC) -lrt -o $(OUT_EXE) Starter.c
	
package:
	tar cvzf $(TAR_NAME) Makefile README.txt Starter.c Fibb.c Prime.c Total.c CharacterReader.c

clean:
	-rm Prime Total Fibb CharacterReader $(OUT_EXE)
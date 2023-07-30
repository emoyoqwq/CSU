Starter.c forks 4 processes. The first child process called CharacterReader is used to get sum of the content in the passed txt file. and the Starter uses pipe to get that sum to 
pass it to the rest of 3 child processes. Fibb.c calculates the first n numbers of fibb series. Prime.c calculates the first n numbers of prime numbers. Total.c calculates the sum 
of first n numbers. all of the 3 child processes use shared memory to pass the last number of their calculations to Starter.c

Questions:
1.
int fd[2]; pipe(fd)
fd[0] denotes the read end. fd[1] denotes the write end

2.
void *mmap(void *addr, size_t length, int prot, int flags, int fd, off_t offset)

3.
shm_open(const char *name, int oflag, mode_t mode)
it returns file descripter.
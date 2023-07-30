Starter.c forks 3 processes which run Fibb.c, Prim.c and Total.c. it also reads the
file and pass contents to the 3 processes.
Fibb.c generates a fibb squence of first n numbers.
Prime.c generates a Prime squence of first n numbers.
Total generates sum of the first n numbers.
Questions
1. 8 bits.
2. <sys/types.h>
3. 0
4. fokr may fail because of a lack of available resources.
5. sequential processing because we use wait to let the first child process finish
before we forking another child process.
6. if the exit status of the child is too huge which is more than 8 bits, it will
cause overflow and there will be anomalies.
CC=gcc
CFLAGS=-c -Wall

wesql: wesql.o sqlite3.o
	$(CC) -g wesql.o sqlite3.o -o wesql

wesql.o: wesql.c 
	$(CC) $(CFLAGS) wesql.c

sqlite3.o: sqlite/sqlite3.c
	$(CC) $(CFLAGS) sqlite/sqlite3.c

.PHONY: clean
clean:
	rm -rf *.class *.o

.PHONY: all
all: clean wesql

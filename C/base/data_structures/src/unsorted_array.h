//
// Created by Andy Jarombek on 11/3/19.
//

#ifndef DATASTRUCTURES_UNSORTED_ARRAY_H
#define DATASTRUCTURES_UNSORTED_ARRAY_H

/**
 * Struct representing an unsorted, resizable array data structure.  This array explicitly holds characters.
 */
typedef struct UnsortedCharArray {
    int capacity;
    int size;
    char* content;
} UnsortedCharArray;

UnsortedCharArray init_empty();
UnsortedCharArray init(int length);
int insert(UnsortedCharArray* array, char item, int index);
void add(UnsortedCharArray* array, char item);
char* get(UnsortedCharArray* array, int index);
int search(UnsortedCharArray* array, char item);
int delete(UnsortedCharArray* array, char item);
char* pop(UnsortedCharArray* array, int index);

#endif

/**
 * Create and test an unsorted array data structure.
 * @author Andrew Jarombek
 * @date 10/20/2019
 */

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "DataStructures.h"

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
int remove(UnsortedCharArray* array, char item);
char* pop(UnsortedCharArray* array, int index);

UnsortedCharArray init_empty() {
    UnsortedCharArray array;
    array.capacity = 0;
    array.size = 0;
    array.content = NULL;
    return array;
}

UnsortedCharArray init(int length) {
    UnsortedCharArray array;
    array.capacity = length;
    array.size = 0;
    array.content = (char*) calloc(length, sizeof(char));
    return array;
}

int insert(UnsortedCharArray* array, char item, int index) {
    if (index >= array->capacity) {
        return EXIT_FAILURE;
    } else {
        array->content[index] = item;
        return EXIT_SUCCESS;
    }
}

void add(UnsortedCharArray* array, char item) {
    if (array->size + 1 >= array->capacity) {
        int newCapacity = array->capacity * 1.5 + 1;
        array->content = realloc(array->content, newCapacity * sizeof(char));
        array->capacity = newCapacity;
    }

    array->content[array->size] = item;
    array->size++;
}

char* get(UnsortedCharArray* array, int index) {
    if (index < array->size) {
        return &array->content[index];
    } else {
        return (void *) 0;
    }
}

int search(UnsortedCharArray* array, char item) {
    for (int i = 0; i < array->size; i++) {
        if (array->content[i] == item) {
            return i;
        }
    }
    return -1;
}

int remove(UnsortedCharArray* array, char item) {
    for (int i = 0; i < array->size; i++) {
        if (array->content[i] == item) {
            if (array->size - 1 != i) {
                array->content[i] = array->content[array->size - 1];
            }

            array->size = array->size - 1;
            return EXIT_SUCCESS;
        }
    }
    return EXIT_FAILURE;
}

char* pop(UnsortedCharArray* array, int index) {
    if (index <= 0 && index < array->size) {
        char* item = &array->content[index];

        for (int i = index + 1; i < array->size; i++) {
            array->content[i - 1] = array->content[i];
        }

        array->size = array->size - 1;
        return item;
    } else {
        return (void *) 0;
    }
}

int main() {
    printf("Data Structures: Unsorted Array Version %s.%s", DataStructures_VERISON_MAJOR, DataStructures_VERISON_MINOR);

    UnsortedCharArray unsortedArray = init_empty();

    assert(unsortedArray.size == 0);
    assert(unsortedArray.capacity == 0);

    int insertSuccess = insert(&unsortedArray, 'a', 0);

    assert(insertSuccess == 1);
    assert(unsortedArray.size == 0);
    assert(unsortedArray.capacity == 0);

    add(&unsortedArray, 'a');

    assert(unsortedArray.content[0] == 'a');
    assert(unsortedArray.size == 1);
    assert(unsortedArray.capacity == 1);

    char* firstChar = get(&unsortedArray, 0);

    assert(*firstChar == 'a');
    assert(unsortedArray.size == 1);
    assert(unsortedArray.capacity == 1);

    int aIndex = search(&unsortedArray, 'a');

    assert(aIndex == 0);
    assert(unsortedArray.size == 1);
    assert(unsortedArray.capacity == 1);

    add(&unsortedArray, 'j');

    assert(unsortedArray.content[1] == 'j');
    assert(unsortedArray.size == 2);
    assert(unsortedArray.capacity == 2);

    int removedSuccessfully = remove(&unsortedArray, 'a');

    assert(unsortedArray.content[0] == 'j');
    assert(removedSuccessfully == 0);
    assert(unsortedArray.size == 1);
    assert(unsortedArray.capacity == 2);

    char* jChar = pop(&unsortedArray, 0);

    assert(*jChar == 'j');
    assert(unsortedArray.size == 0);
    assert(unsortedArray.capacity == 2);

    add(&unsortedArray, 'a');
    add(&unsortedArray, 'j');

    assert(unsortedArray.content[0] == 'a');
    assert(unsortedArray.content[1] == 'j');
    assert(unsortedArray.size == 2);
    assert(unsortedArray.capacity == 4);
}
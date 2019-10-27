/**
 * Create and test an unsorted array data structure.
 * @author Andrew Jarombek
 * @date 10/20/2019
 */

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
//#include "DataStructuresConfig.h"

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

/**
 * Initialize a new unsorted array that is empty and with a capacity of zero.  Calling add() is the only way to create
 * a populated array from this empty one.
 * @return An empty unsorted array that can contain characters.
 */
UnsortedCharArray init_empty() {
    UnsortedCharArray array;
    array.capacity = 0;
    array.size = 0;
    array.content = NULL;
    return array;
}

/**
 * Initialize an unsorted character array of a given capacity.
 * @param length The initial capacity of the array.
 * @return An unsorted character array.
 */
UnsortedCharArray init(int length) {
    UnsortedCharArray array;
    array.capacity = length;
    array.size = 0;
    array.content = (char*) calloc(length, sizeof(char));
    return array;
}

/**
 * Insert a character into the unsorted array at a given index.  This is an O(1) operation.
 * @param array The unsorted array to insert the new character into.
 * @param item The character to insert into the array.
 * @param index The location in the array to insert the character.
 * @return 0 if the character was inserted successfully, 1 otherwise.
 */
int insert(UnsortedCharArray* array, char item, int index) {
    if (index >= array->capacity) {
        return EXIT_FAILURE;
    } else {
        array->content[index] = item;
        return EXIT_SUCCESS;
    }
}

/**
 * Add a new item to the end of the unsorted array.  This is an O(1) operation on most occasions, however the array
 * occasionally needs to be increased in size.  In that edge case, the runtime is O(n).
 * @param array The unsorted array to add the new item onto.
 * @param item The new character to add onto the end of the array.
 */
void add(UnsortedCharArray* array, char item) {
    if (array->size + 1 >= array->capacity) {
        int newCapacity = array->capacity * 1.5 + 1;
        array->content = realloc(array->content, newCapacity * sizeof(char));
        array->capacity = newCapacity;
    }

    array->content[array->size] = item;
    array->size++;
}

/**
 * Return the value at an index in the unsorted array.  This is an O(1) operation.
 * @param array The unsorted array to get a value from.
 * @param index The index of the array to return the value from.
 * @return A pointer to the character at a given index if the index is within the array.  Otherwise,
 * return a void pointer.
 */
char* get(UnsortedCharArray* array, int index) {
    if (index < array->size && index >= 0) {
        return &array->content[index];
    } else {
        return (void *) 0;
    }
}

/**
 * Search for a character within the array.  Since the array is unsorted, this operation takes O(n) time.
 * @param array The unsorted array to search for a character within.
 * @param item A character to search for.
 * @return The index of the first occurrence of the item if it's found, -1 otherwise.
 */
int search(UnsortedCharArray* array, char item) {
    for (int i = 0; i < array->size; i++) {
        if (array->content[i] == item) {
            return i;
        }
    }
    return -1;
}

/**
 * Delete the first instance of a character from the array.  This is an O(n) operation, since the array needs
 * to be looped through.
 * @param array The unsorted array to delete an item from.
 * @param item A character to search for and delete.
 * @return 0 if the character was deleted successfully, 1 otherwise.
 */
int delete(UnsortedCharArray* array, char item) {
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

/**
 * Delete and return an item from the array.  This is an O(1) operation since the implementation takes advantage of the
 * fact that the array is not expected to be sorted.
 * @param array The unsorted array to remove and return an item at a given index.
 * @param index The index in the array to delete and return a character from.
 * @return A pointer to the character at a given index if the index is within the array.  Otherwise,
 * return a void pointer.
 */
char* pop(UnsortedCharArray* array, int index) {
    if (index >= 0 && index < array->size) {
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
    //printf("Data Structures: Unsorted Array Version %s.%s", DataStructures_VERISON_MAJOR, DataStructures_VERISON_MINOR);

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

    int removedSuccessfully = delete(&unsortedArray, 'a');

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
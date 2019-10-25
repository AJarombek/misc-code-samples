/**
 * Create and test an unsorted array data structure.
 * @author Andrew Jarombek
 * @date 10/20/2019
 */

#include <stdlib.h>
#include <assert.h>

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
        array->content = realloc(array->content, sizeof(char));
        array->capacity = newCapacity;
    }

    array->content[array->size + 1] = item;
    array->size++;
}

char* get(UnsortedCharArray* array, int index) {
    if (index > array->size) {
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
    if (0 <= index < array->size) {
        for (int i = index + 1; i < array->size; i++) {
            // TODO
        }
        return &array->content[index];
    } else {
        return (void *) 0;
    }
}

int main() {
    UnsortedCharArray unsortedArray = init_empty();
    int insertSuccess = insert(&unsortedArray, 'a', 0);
    assert(insertSuccess == 0);

    add(&unsortedArray, 'a');
}
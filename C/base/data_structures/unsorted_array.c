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
int add(UnsortedCharArray* array, char item);
char get(UnsortedCharArray* array, int index);
char search(UnsortedCharArray* array, char item);

int main() {
    UnsortedCharArray unsortedArray = init_empty();
    int insertSuccess = insert(&unsortedArray, 'a', 0);
    assert(insertSuccess == 0);

    int addSuccess = add(&unsortedArray, 'a');
    assert(addSuccess == 1);
}

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
        return 0;
    } else {
        array->content[index] = item;
        return 1;
    }
}

int add(UnsortedCharArray* array, char item) {
    if (array->size + 1 >= array->capacity) {
        int newCapacity = array->capacity * 1.5 + 1;
        array->content = realloc(array->content, sizeof(char));
        array->capacity = newCapacity;
    }

    array->content[array->size + 1] = item;
    array->size++;
    return 1;
}

char get(UnsortedCharArray* array, int index) {
    return 'a'; // TODO
}

char search(UnsortedCharArray* array, char item) {
    return 'a'; // TODO
}
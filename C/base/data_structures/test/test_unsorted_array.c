#include <check.h>
#include "../src/unsorted_array.h"

#define EXIT_SUCCESS 0
#define EXIT_FAILURE 1

/**
 * A "Hello World" test.
 * @param _i The name of the test case.
 */
START_TEST(hello_world)
{
    char* hello = "Hello World";
    fail_if(!hello);
}
END_TEST

/**
 * Test that constructing an empty array works as expected.
 * @param _i The name of the test case.
 */
START_TEST(construct_empty_array)
{
    UnsortedCharArray unsortedArray = init_empty();
    ck_assert_int_eq(unsortedArray.size,0);
    ck_assert_int_eq(unsortedArray.capacity,0);
    ck_assert_ptr_eq(unsortedArray.content, (void*) 0);
}
END_TEST

/**
 * Test that constructing an array with an initial capacity works as expected.
 * @param _i The name of the test case.
 */
START_TEST(construct_array)
{
    UnsortedCharArray unsortedCharArray = init(10);
    ck_assert_int_eq(unsortedCharArray.size,0);
    ck_assert_int_eq(unsortedCharArray.capacity,10);
    ck_assert_ptr_nonnull(unsortedCharArray.content);
}
END_TEST

/**
 * Test that inserting a value into an array with a capacity of zero fails.
 * @param _i The name of the test case.
 */
START_TEST(insert_into_zero_capacity_array_fails)
{
    UnsortedCharArray unsortedArray = init_empty();
    int result = insert(&unsortedArray, 'a', 0);

    ck_assert_int_eq(result, EXIT_FAILURE);
    ck_assert_int_eq(unsortedArray.size,0);
    ck_assert_int_eq(unsortedArray.capacity,0);
}
END_TEST

/**
 * Test that adding an element to an empty array works as expected.
 * @param _i The name of the test case.
 */
START_TEST(add_to_empty_array)
{
    UnsortedCharArray unsortedArray = init_empty();
    add(&unsortedArray, 'a');

    ck_assert_int_eq(unsortedArray.content[0], 'a');
    ck_assert_int_eq(unsortedArray.size,1);
    ck_assert_int_eq(unsortedArray.capacity,1);
}
END_TEST

/**
 * Test that adding two elements to an empty array works as expected.
 * @param _i The name of the test case.
 */
START_TEST(add_twice_to_empty_array)
{
    UnsortedCharArray unsortedArray = init_empty();
    add(&unsortedArray, 'a');
    add(&unsortedArray, 'j');

    ck_assert_int_eq(unsortedArray.content[0], 'a');
    ck_assert_int_eq(unsortedArray.content[1], 'j');
    ck_assert_int_eq(unsortedArray.size,2);
    ck_assert_int_eq(unsortedArray.capacity,2);
}
END_TEST

/**
 * Test that adding an element to a non-empty array works as expected.
 * @param _i The name of the test case.
 */
START_TEST(add_to_array)
{
    UnsortedCharArray unsortedArray = init(10);
    add(&unsortedArray, 'a');

    ck_assert_int_eq(unsortedArray.content[0], 'a');
    ck_assert_int_eq(unsortedArray.size,1);
    ck_assert_int_eq(unsortedArray.capacity,10);
}
END_TEST

/**
 * Test that adding an element into an array and then retrieving it by its index works as expected.
 * @param _i The name of the test case.
 */
START_TEST(get_added_item_in_array)
{
    UnsortedCharArray unsortedArray = init_empty();
    add(&unsortedArray, 'a');
    char* firstChar = get(&unsortedArray, 0);
    ck_assert_int_eq(*firstChar, 'a');
}
END_TEST

/**
 * Test that adding an element into an array and then searching for it works as expected.
 * @param _i The name of the test case.
 */
START_TEST(search_array)
{
    UnsortedCharArray unsortedArray = init_empty();
    add(&unsortedArray, 'a');
    int aIndex = search(&unsortedArray, 'a');
    ck_assert_int_eq(aIndex, 0);
}
END_TEST

/**
 * Test that adding two elements to an empty array followed by deleting one element works as expected.
 * @param _i The name of the test case.
 */
START_TEST(add_twice_delete_once_array)
{
    UnsortedCharArray unsortedArray = init_empty();
    add(&unsortedArray, 'a');
    add(&unsortedArray, 'j');

    int removedSuccessfully = delete(&unsortedArray, 'a');

    ck_assert_int_eq(removedSuccessfully, EXIT_SUCCESS);
    ck_assert_int_eq(unsortedArray.content[0], 'j');
    ck_assert_int_eq(unsortedArray.size,1);
    ck_assert_int_eq(unsortedArray.capacity,2);
}
END_TEST

/**
 * Starting with an empty array, test that adding two elements, deleting one element, and then popping the last element
 * works as expected.
 * @param _i The name of the test case.
 */
START_TEST(add_twice_delete_once_pop_once_array)
{
    UnsortedCharArray unsortedArray = init_empty();
    add(&unsortedArray, 'a');
    add(&unsortedArray, 'j');

    delete(&unsortedArray, 'a');
    char* jChar = pop(&unsortedArray, 0);

    ck_assert_int_eq(*jChar, 'j');
    ck_assert_int_eq(unsortedArray.size,0);
    ck_assert_int_eq(unsortedArray.capacity,2);
}
END_TEST

/**
 * Starting with an empty array, test that adding two elements, deleting both elements, and then adding both elements
 * back works as expected.
 * @param _i The name of the test case.
 */
START_TEST(add_twice_delete_both_add_twice_array)
{
    UnsortedCharArray unsortedArray = init_empty();

    add(&unsortedArray, 'a');
    add(&unsortedArray, 'j');
    delete(&unsortedArray, 'a');
    delete(&unsortedArray, 'j');
    add(&unsortedArray, 'a');
    add(&unsortedArray, 'j');

    ck_assert_int_eq(unsortedArray.content[0], 'a');
    ck_assert_int_eq(unsortedArray.content[1], 'j');
    ck_assert_int_eq(unsortedArray.size,2);
    ck_assert_int_eq(unsortedArray.capacity,4);
}
END_TEST

/**
 * Configure the test suite and add unit tests to it.
 * @return The test suite instance.
 */
Suite* test_suite() {
    Suite* suite = suite_create("unsorted_array");
    TCase* tCase = tcase_create("Unsorted Array");

    tcase_add_test(tCase, hello_world);
    tcase_add_test(tCase, construct_empty_array);
    tcase_add_test(tCase, construct_array);
    tcase_add_test(tCase, insert_into_zero_capacity_array_fails);
    tcase_add_test(tCase, add_to_empty_array);
    tcase_add_test(tCase, add_twice_to_empty_array);
    tcase_add_test(tCase, add_to_array);
    tcase_add_test(tCase, get_added_item_in_array);
    tcase_add_test(tCase, search_array);
    tcase_add_test(tCase, add_twice_delete_once_array);
    tcase_add_test(tCase, add_twice_delete_once_pop_once_array);
    tcase_add_test(tCase, add_twice_delete_both_add_twice_array);

    suite_add_tcase(suite, tCase);
    return suite;
}

/**
 * Entrypoint to the test suite.
 * @return The exit code of the test suite is the number of tests that failed.
 */
int main() {
    int numberFailed;

    Suite* suite = test_suite();
    SRunner* sRunner = srunner_create(suite);
    srunner_set_log(sRunner, "unsorted_array.log");
    srunner_run_all(sRunner, CK_NORMAL);
    numberFailed = srunner_ntests_failed(sRunner);
    srunner_free(sRunner);
    return numberFailed;
}
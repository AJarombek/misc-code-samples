#include <stdio.h>
#include <check.h>

START_TEST(hello_world)
{
    char* hello = "Hello World";
    fail_if(!hello);
}
END_TEST

Suite* test_suite() {
    Suite* suite = suite_create("unsorted_array");
    TCase* tCase = tcase_create("hello_world");
    tcase_add_test(tCase, hello_world);
    suite_add_tcase(suite, tCase);
    return suite;
}

int main() {
    int numberFailed;

    printf("Hello from Test Suite");

    Suite* suite = test_suite();
    SRunner* sRunner = srunner_create(suite);
    srunner_run_all(sRunner, CK_NORMAL);
    numberFailed = srunner_ntests_failed(sRunner);
    srunner_free(sRunner);
    return numberFailed;
}
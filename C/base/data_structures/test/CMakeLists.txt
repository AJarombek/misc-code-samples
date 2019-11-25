# Build the unit test code in the C project for data structures.
# Author: Andrew Jarombek
# Date: 10/27/2019

message(C Tests)

find_package(Check REQUIRED)
include_directories(${CHECK_INCLUDE_DIRS})

add_executable(TestDataStructures test_unsorted_array.c)
target_link_libraries(TestDataStructures DataStructures ${CHECK_LIBRARIES} pthread)

add_test(NAME TestDataStructures COMMAND TestDataStructures WORKING_DIRECTORY ${CMAKE_BINARY_DIR}/Testing)
set_tests_properties(TestDataStructures PROPERTIES TIMEOUT 30)
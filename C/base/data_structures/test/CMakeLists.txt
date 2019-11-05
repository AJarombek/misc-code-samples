# Build the unit test code in the C project for data structures.
# Author: Andrew Jarombek
# Date: 10/27/2019

message(C Tests)

set(Check_DIR ../CMakeModules)

find_package(Check REQUIRED)
include_directories(${CHECK_INCLUDE_DIRS})
set(LIBS "check DataStructuresLib")

include_directories(. ../src)
add_executable(TestDataStructures test_unsorted_array.c)
target_link_libraries(TestDataStructures ${LIBS})
add_test(TestDataStructures ${CMAKE_CURRENT_BINARY_DIR}/TestDataStructures)
# Build the unit test code in the C project for data structures.
# Author: Andrew Jarombek
# Date: 10/27/2019

set(Check_DIR ../CMakeModules)

find_package(Check REQUIRED)
include_directories(${CHECK_INCLUDE_DIRS})
set(LIBS ${LIBS} ${CHECK_LIBRARIES} DataStructures)

include_directories(. ../src)
add_executable(TestDataStructures test_unsorted_array.c)
add_test(TestDataStructures ${CMAKE_CURRENT_BINARY_DIR}/TestDataStructures)
target_link_libraries(TestDataStructures ${LIBS})
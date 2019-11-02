# Build the source code in the C project for data structures.
# Author: Andrew Jarombek
# Date: 10/27/2019

add_library(DataStructuresLib unsorted_array.c)
add_executable(DataStructures unsorted_array.c)
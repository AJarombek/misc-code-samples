# Make the C project to test for type equality.
# Author: Andrew Jarombek
# Date: 8/17/2019

cmake_minimum_required(VERSION 3.9)
project(DataStructures)

set(DataStructures_VERSION_MAJOR 1)
set(DataStructures_VERSION_MINOR 0)

configure_file(
    "${PROJECT_SOURCE_DIR}/DataStructuresConfig.h.in",
    "${PROJECT_BINARY_DIR}/DataStructuresConfig.h"
)

include_directories("${PROJECT_BINARY_DIR}")

set(CMAKE_C_STANDARD 99)

add_executable(DataStructures unsorted_array.c)
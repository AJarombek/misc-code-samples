# Make the C project to test for type equality.
# Author: Andrew Jarombek
# Date: 8/17/2019

cmake_minimum_required(VERSION 3.9)
project(c C)

set(CMAKE_C_STANDARD 99)

add_executable(c unsorted_array.c)
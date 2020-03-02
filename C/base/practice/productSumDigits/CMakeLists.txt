# Make the C project to run the product sum digits practice problem.
# Author: Andrew Jarombek
# Date: 3/2/2020

cmake_minimum_required(VERSION 3.9)
project(c C)

set(CMAKE_C_STANDARD 99)

add_executable(c productSumDigits.h productSumDigits.c)
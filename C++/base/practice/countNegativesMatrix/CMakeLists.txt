# Make the C++ project to run the count negative numbers practice problem.
# Author: Andrew Jarombek
# Date: 3/17/2020

cmake_minimum_required(VERSION 3.9)
project(cpp)

set(CMAKE_CXX_STANDARD 17)

add_executable(cpp countNegativesMatrix.h countNegativesMatrix.cpp main.cpp)
#!/usr/bin/env bash

# Commands to build and execute C code with CMake and Make.
# Author: Andrew Jarombek
# Since: 3/2/2020

rm -rf build
mkdir build
cd build
cmake ..
make
./c
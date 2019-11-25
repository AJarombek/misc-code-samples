# Make the C project for data structures.
# Author: Andrew Jarombek
# Date: 10/27/2019

cmake_minimum_required(VERSION 3.9)
project(DataStructures)

option(ENABLE_TESTS "Enable Tests" ON)

set(CMAKE_C_STANDARD 11)

set(CMAKE_MODULE_PATH "${CMAKE_CURRENT_SOURCE_DIR}/CMakeModules")
set(BUILD_SHARED_LIBS OFF)
set(CMAKE_BUILD_TYPE Debug)

add_subdirectory(src)

if (${ENABLE_TESTS})
    enable_testing()
    add_subdirectory(test)
endif()
cmake_minimum_required(VERSION 3.8)
project(32)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.cpp sort.h sort.cpp)
add_executable(32 ${SOURCE_FILES})
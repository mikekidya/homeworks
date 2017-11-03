cmake_minimum_required(VERSION 3.8)
project(44)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.cpp stack.h stack.cpp)
add_executable(44 ${SOURCE_FILES})
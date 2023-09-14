cmake_minimum_required(VERSION 3.26)
project(demo)

set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_FLAGS "-Wall -Wextra")
set(CMAKE_CXX_FLAGS_DEBUG "-g")
set(CMAKE_CXX_FLAGS_RELEASE "-O3")


# JNI
include_directories("$ENV{JAVA_HOME}/include")
link_directories("$ENV{JAVA_HOME}/lib/server")
if (APPLE)
    # mac
    include_directories(/opt/homebrew/include)
    link_directories(/opt/homebrew/lib)
    # JNI
    include_directories("$ENV{JAVA_HOME}/include/darwin")
else()
    # linux
    include_directories(/usr/include/jsoncpp)
    # JNI
    include_directories("$ENV{JAVA_HOME}/include/linux")
endif ()

add_library(demo SHARED library.cpp)

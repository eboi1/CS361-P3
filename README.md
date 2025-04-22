# Project #3: Turing Machine Simulator

* Authors: Eric Johnson and Andrew Kobus
* Class: CS361 Section 002/001
* Semester: Spring 2025

## Overview
This program simulates a bi-infinite TM. A Turing machine is a hypothetical device that manipulates symbols on a strip
of tape according to a table of rules. Despite its simplicity, a Turing machine can
be adapted to simulate the logic of any computer algorithm, and is particularly
useful in explaining the functions of a CPU inside a computer.

## Reflection ~ Andrew Kobus
This project was as fun as it was challenging. I found the most interesting part to be designing the state transition logic. The bi-infinite tape implementation using a HashMap was a great solution, as it allowed us to handle potentially infinite space without wasting memory on unused cells. In terms of performance, using HashMaps allowed for constant time lookups, which is a crucial optimization for simulating complex machines efficiently. If I were to extend this, I would add visualization features to watch the TM execute in real-time.

## Reflection ~ Eric Johnson
I really like this project since I provided a hands-on opportunity to become more familiar with turing machines.  Altough there are still concepts that I am not completely clear on, I feel much more confident with TMs.  One of the key aspects of this project was ensuring that input validation errors are handled correctly and gracefully as well as handling parsing errors in a similar way.  It helped to write descriptive error statements to aid in debugging the program. I think it would fun to re work this project in the future and try different methods of optimizing the program when handling much larger .txt input files.  


## Compiling and Using

* To Compile:
    * javac tm/TMSimulator.java
* To Run:
    * java tm/TMSimulator [_file_name_here_].txt

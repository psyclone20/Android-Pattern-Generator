# Android-Pattern-Generator
A Java program that uses DFS to generate all the possible patterns on a traditional 3x3 Android lock screen.

A valid pattern must satisfy the following constraints: ([source](https://www.quora.com/Android-operating-system-How-many-combinations-does-Android-9-point-unlock-have "Quora"))
* Must connect at least four dots.
* All dots in the pattern must be distinct.
* If the line segment connecting any two consecutive dots in the pattern passes through any other dots, those other dots must have previously been in the pattern.

# Output
The program generates all the possible 389,112 patterns and creates 2 files:
* `sequential.txt` that contains the patterns arranged sequentially
* `shuffled.txt` that contains the patterns arranged randomly

For reference, the numbers in the output are mapped to the points as:
```
0     1     2


3     4     5


6     7     8
```

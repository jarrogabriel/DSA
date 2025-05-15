# Sorting Algorithms

This repository contains the documentation and implementation of some sorting algorithms.

## BubbleSort

The BubbleSort algorithm is a simple sorting method that repeatedly traverses the list, comparing adjacent elements and
swapping them if necessary.

## Pseudocode

```plaintext
For each element in the array:
  - Compare the current element with the next one.
  - If the current element is greater, swap their positions.
  - If no swaps were made during a pass, terminate the process.
```

## RadixSort

RadixSort sorts numbers by processing them digit by digit, starting either from the least significant digit (LSD - Least
Significant Digit) or the most significant digit (MSD - Most Significant Digit).

## Pseudocode

```plaintext
1. Find the largest number in the array to determine the number of digits.
    2. For each digit:
        a. Distribute the numbers into buckets based on the current digit.
        b. Recombine the numbers in the order of the buckets.
```

## Selection Sort

Selection Sort sorting works by dividing the array into two parts: the sorted
part and the unsorted part. The algorithm repeatedly selects the smallest (or largest, depending on the sorting order)
element from the unsorted part and moves it to the sorted part.

## Pseudocode

```plaintext
1. Start with the first element in the array.
2. Find the smallest element in the unsorted portion of the array.
3. Swap the smallest element with the first element of the unsorted portion.
4. Move the boundary between the sorted and unsorted portions one step forward.
5. Repeat the process until the entire array is sorted.
```
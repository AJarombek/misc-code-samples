"""
Practice problem: https://leetcode.com/problems/sort-array-by-parity-ii/
Description:
    Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
    Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.

Author: Andrew Jarombek
Date: 1/9/2020
"""


def sort_array_by_parity(a: list) -> list:
    """
    Solve the problem by swapping values every time an odd/even value is found out of place.  This is the slower
    of the two methods, with a time complexity of O(n^2).
    :param a: The input array.
    :return: The sorted by parity array.
    """
    for i in range(len(a)):
        if i % 2 == 0:
            this_mod = 1
            swap_mod = 0
        else:
            this_mod = 0
            swap_mod = 1

        if a[i] % 2 == this_mod:
            for j in range(i + 1, len(a), 2):
                if a[j] % 2 == swap_mod:
                    a[i], a[j] = a[j], a[i]

    return a


def sort_array_by_parity_v2(a: list) -> list:
    """
    Solve the problem by iterating only once and maintaining and odd and even pointer.  This is the faster of
    the two methods, taking O(n) time.
    :param a: The input array.
    :return: The sorted by parity array.
    """
    b = a.copy()
    even_index = 0
    odd_index = 1
    for i in range(len(a)):
        if a[i] % 2 == 0:
            b[even_index] = a[i]
            even_index += 2
        else:
            b[odd_index] = a[i]
            odd_index += 2

    return b
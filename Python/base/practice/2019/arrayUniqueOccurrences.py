"""
Interview practice problem:
Description:
    Given an array of integers arr, write a function that returns true if and only if the number of occurrences of each
    value in the array is unique.

Author: Andrew Jarombek
Date: 10/27/2019
"""


def unique_occurrences(arr: list) -> bool:
    """
    Solve the unique occurrences problem using sets.  The runtime is O(n^2) because there is a nested for loop.  The
    space complexity is O(m^2), where m is the unique items in the input array.  m must be less than the length of the
    input array, because False will be returned if each item in the array is unique.
    :param arr: The array to check the number of occurrences of each value.
    :return: True if and only if the number of occurrences of each value in the array is unique.
    """
    occurrences = set()
    found = set()

    for i in range(len(arr)):
        if arr[i] not in found:
            found.add(arr[i])
            length = sum(1 for item in arr[i:] if item == arr[i])

            if length in occurrences:
                return False
            else:
                occurrences.add(length)
    return True


if __name__ == '__main__':
    input1 = [1, 2, 2, 1, 1, 3]
    result1 = unique_occurrences(input1)
    assert result1

    input2 = [1, 2]
    result2 = unique_occurrences(input2)
    assert not result2

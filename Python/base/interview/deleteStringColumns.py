"""
Interview practice problem: https://leetcode.com/problems/delete-columns-to-make-sorted
Result: [SUCCESS - 0:25]
Description:
    We are given an array A of N lowercase letter strings, all of the same length.  Now, we may choose any set of
    deletion indices, and for each string, we delete all the characters in those indices.

    For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after
    deletions is ["bef", "vyz"], and the remaining columns of A are ["b","v"], ["e","y"], and ["f","z"].

    Suppose we chose a set of deletion indices D such that after deletions, each remaining column in A is in
    ascending sorted order.  Return the minimum possible value of D.length.

Author: Andrew Jarombek
Date: 11/27/2019
"""


def min_deletion_size(a: list) -> int:
    """
    Solve the problem with a set to maintain the deleted indexes and nested for loops.  The time complexity is O(nm),
    where n is the length of the list of strings and m is the length of the strings.  The space complexity is O(m),
    where m is the length of the strings.
    :param a: A list of strings, all of which have the same length.
    :return: The number of indexes that need to be removed from the strings to follow the above requirements.
    """
    deleted = set()
    for i in range(len(a[0])):
        prev = a[0][i]
        for j in range(1, len(a)):
            if prev > a[j][i]:
                deleted.add(i)
                break
            else:
                prev = a[j][i]
    return len(deleted)


if __name__ == '__main__':
    result = min_deletion_size(["cba", "daf", "ghi"])
    assert result == 1

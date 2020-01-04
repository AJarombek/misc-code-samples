"""
Interview practice problem: https://leetcode.com/problems/partition-labels/
Description:
    A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that
    each letter appears in at most one part, and return a list of integers representing the size of these parts.

Author: Andrew Jarombek
Date: 10/29/2019
"""


def partition_labels(string: str) -> list:
    """
    Solve the partition labels problem.  This program contains nested loops, which at most could take O(n^3) time.
    The space complexity is O(n), with a list of partitions being created.
    :param string: The original string.
    :return: A list of resulting partition lengths.
    """
    partition_lengths = []

    while string is not None and len(string) > 0:
        end = 0

        first_char = string[0]
        for i in range(len(string) - 1, 0, -1):
            if string[i] == first_char:
                end = i
                break

        viewed = {first_char}
        current = 0
        while current < end:
            if string[current] not in viewed:
                for i in range(len(string) - 1, end, -1):
                    if string[i] == string[current]:
                        end = i
                        break
            current += 1

        partition_lengths.append(end + 1)
        string = string[end + 1:]

    return partition_lengths


if __name__ == '__main__':
    result = partition_labels("ababcbacadefegdehijhklij")
    assert result == [9, 7, 8]

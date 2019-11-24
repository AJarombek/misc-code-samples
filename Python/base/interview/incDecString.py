"""
Interview practice problem: https://leetcode.com/problems/di-string-match/
Result: [SUCCESS - 0:18]
Description:
    Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.
    Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:

    If S[i] == "I", then A[i] < A[i+1]
    If S[i] == "D", then A[i] > A[i+1]

Author: Andrew Jarombek
Date: 11/24/2019
"""


def di_string_match(s: str) -> list:
    """
    Solve the problem by recognizing the pattern of integers assigned to 'I' and 'D'.  The time complexity and space
    complexity are O(n).
    :param s: The string of 'I' and 'D' characters.
    :return: An array of integers that follow the rules stated above.
    """
    low = 0
    high = len(s)
    result = []

    for c in s:
        if c == 'I':
            result.append(low)
            low += 1
        else:
            result.append(high)
            high -= 1

    result.append(low)
    return result


if __name__ == '__main__':
    result1 = di_string_match("IDID")
    assert result1 == [0, 4, 1, 3, 2]

    result1 = di_string_match("III")
    assert result1 == [0, 1, 2, 3]

    result1 = di_string_match("DDI")
    assert result1 == [3, 2, 0, 1]

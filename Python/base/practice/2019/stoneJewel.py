"""
Interview practice problem.
Description:
    You're given strings J representing the types of stones that are jewels, and S representing the stones you have.
    Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

    The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive,
    so "a" is considered a different type of stone from "A".

Author: Andrew Jarombek
Date: 10/13/2019
"""


def num_jewels_in_stones_v1(j: str, s: str) -> int:
    """
    Solve the jewels and stones problem.  This was my first attempt at the problem, and is the slowest of the three
    solutions.  The problem is it iterates over S once and J len(S) times.  This makes the time complexity O(n^2).
    :param j: The set of jewels (J).
    :param s: The list of stones (S).
    :return: The number of jewels in the list of stones.
    """
    if j is None or s is None or len(j) == 0 or len(s) == 0:
        return 0

    count = 0

    for stone in s:
        if stone in j:
            count = count + 1

    return count


def num_jewels_in_stones_v2(j: str, s: str) -> int:
    """
    Solve the jewels and stones problem.  This was my second attempt at the problem, which is much faster than the
    first.  The trick is converting the list of jewels into a hash set, which has a best case search time of O(1).
    There is now the one-time cost of converting the string of jewels into a set.
    This makes the time complexity O(n + m).
    :param j: The set of jewels (J).
    :param s: The list of stones (S).
    :return: The number of jewels in the list of stones.
    """
    if j is None or s is None or len(j) == 0 or len(s) == 0:
        return 0

    j_set = set(j)
    return sum(1 for stone in s if stone in j_set)


def num_jewels_in_stones_v3(j: str, s: str) -> int:
    """
    Solve the jewels and stones problem.  This is the same as the second solution except without the initial base case
    checks.  If the base cases are uncommon, this function will generally be faster.
    :param j: The set of jewels (J).
    :param s: The list of stones (S).
    :return: The number of jewels in the list of stones.
    """
    j_set = set(j)
    return sum(1 for stone in s if stone in j_set)


if __name__ == '__main__':
    result_v1 = num_jewels_in_stones_v1('aA', 'aAAbbbb')
    assert result_v1 == 3

    result_v2 = num_jewels_in_stones_v2('aA', 'aAAbbbb')
    assert result_v2 == 3

    result_v3 = num_jewels_in_stones_v3('aA', 'aAAbbbb')
    assert result_v3 == 3

"""
Interview practice problem.
Description:
    Given a string s, find the longest palindromic substring in s.
Example:
    Input: "babad"
    Output: "bab"
    Note: "aba" is also a valid answer.

Author: Andrew Jarombek
Date: 10/11/2019
"""


def longest_palindrome_brute_force(s: str) -> str:
    """
    Brute force algorithm for finding the longest palindrome substring in a string.
    :param s: A string to search for palindromes in.
    :return: The longest palindrome or an empty string if there are none.
    """
    if s is None or len(s) == 0:
        return ""

    longest = ""
    for i in range(0, len(s)):
        if len(s) - i > len(longest):
            for j in range(i, len(s) + 1):
                subStr = s[i:j]
                subStrRev = subStr[::-1]
                if subStr == subStrRev and len(subStr) > len(longest):
                    longest = subStr
        else:
            break

    return longest


def longest_palindrome_dynamic_programming(s: str) -> str:
    """
    Dynamic programming algorithm for finding the longest palindrome substring in a string.
    :param s: A string to search for palindromes in.
    :return: The longest palindrome or an empty string if there are none.
    """
    if s is None or len(s) == 0:
        return ""

    start = 0
    end = 0

    for i in range(0, len(s)):
        len1 = expand_around_center(s, i, i)
        len2 = expand_around_center(s, i, i + 1)
        max_len = max(len1, len2)

        if max_len > end - start:
            start = i - (max_len - 1) // 2
            end = i + max_len // 2

    return s[start:end + 1]


def expand_around_center(s: str, left: int, right: int) -> int:
    """
    Helper function that attempts to search the characters around a substring to see if they form a palindrome.
    :param s: A string that is a palindrome.
    :param left: The start index of the substring.
    :param right: The end index of the substring.
    :return: The length of the palindrome after expanding outwards.
    """
    while left >= 0 and right < len(s) and s[left] == s[right]:
        left = left - 1
        right = right + 1

    return right - left - 1


if __name__ == '__main__':
    brute_force_result_1 = longest_palindrome_brute_force("babad")
    brute_force_result_2 = longest_palindrome_brute_force("cbbd")

    print(brute_force_result_1)
    print(brute_force_result_2)

    dynamic_result_1 = longest_palindrome_dynamic_programming("babad")
    dynamic_result_2 = longest_palindrome_dynamic_programming("cbbd")

    print(dynamic_result_1)
    print(dynamic_result_2)

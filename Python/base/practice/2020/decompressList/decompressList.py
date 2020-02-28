"""
Practice problem: https://leetcode.com/problems/decompress-run-length-encoded-list/
Result: [SUCCESS - 0:03]
Description:
    Input: nums = [1,2,3,4]
    Output: [2,4,4,4]
    Explanation: The first pair [1,2] means we have freq = 1 and val = 2 so we generate the array [2].
    The second pair [3,4] means we have freq = 3 and val = 4 so we generate [4,4,4].
    At the end the concatenation [2] + [4,4,4] is [2,4,4,4].

Author: Andrew Jarombek
Date: 2/28/2020
"""


def decompress_list(nums: list) -> list:
    result = []
    for i in range(0, len(nums), 2):
        result += [nums[i + 1]] * nums[i]

    return result

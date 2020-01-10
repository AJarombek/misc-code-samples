"""
Unit Test the Practice problem: https://leetcode.com/problems/sort-array-by-parity-ii/
Author: Andrew Jarombek
Date: 1/9/2020
"""

import unittest

from .sortArrayByParity import sort_array_by_parity, sort_array_by_parity_v2


class TestSortArrayByParity(unittest.TestCase):

    def test_method_1(self):
        unsorted_array = [4, 2, 5, 7]
        result_array = sort_array_by_parity(unsorted_array)
        self.assertEqual('[4, 5, 2, 7]', str(result_array))

    def test_method_2(self):
        unsorted_array = [4, 2, 5, 7]
        result_array = sort_array_by_parity_v2(unsorted_array)
        self.assertEqual('[4, 5, 2, 7]', str(result_array))


if __name__ == '__main__':
    unittest.main()
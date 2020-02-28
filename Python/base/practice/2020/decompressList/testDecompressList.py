"""
Unit Test the Practice problem: https://leetcode.com/problems/decompress-run-length-encoded-list/
Author: Andrew Jarombek
Date: 2/28/2020
"""

import unittest

from .decompressList import decompress_list


class TestStepsToZero(unittest.TestCase):

    def test_odd(self):
        result = decompress_list([1, 2, 3, 4])
        self.assertEqual([2, 4, 4, 4], result)


if __name__ == '__main__':
    unittest.main()
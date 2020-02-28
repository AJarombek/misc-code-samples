"""
Unit Test the Practice problem: https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
Author: Andrew Jarombek
Date: 2/28/2020
"""

import unittest

from .stepsToZero import number_of_steps


class TestStepsToZero(unittest.TestCase):

    def test_odd(self):
        result = number_of_steps(9)
        self.assertEqual(5, result)

    def test_even(self):
        result = number_of_steps(8)
        self.assertEqual(4, result)

    def test_zero(self):
        result = number_of_steps(0)
        self.assertEqual(0, result)


if __name__ == '__main__':
    unittest.main()

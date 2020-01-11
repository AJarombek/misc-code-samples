"""
Unit Test the Practice problem: https://leetcode.com/problems/find-positive-integer-solution-for-a-given-equation/
Author: Andrew Jarombek
Date: 1/11/2020
"""

import unittest

from .positiveIntegerSolution import CustomSolution, find_solution


class TestPositiveIntegerSolution(unittest.TestCase):

    def test_addition_function(self):
        custom_solution = CustomSolution(lambda x, y: x + y)
        result_array = find_solution(custom_solution, 5)
        self.assertEqual("[[1, 4], [2, 3], [3, 2], [4, 1]]", str(result_array))

    def test_multiplication_function(self):
        custom_solution = CustomSolution(lambda x, y: x * y)
        result_array = find_solution(custom_solution, 5)
        self.assertEqual("[[1, 5], [5, 1]]", str(result_array))


if __name__ == '__main__':
    unittest.main()
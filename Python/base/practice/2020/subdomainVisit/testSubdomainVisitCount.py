"""
Unit Test the Practice problem: https://leetcode.com/problems/subdomain-visit-count/
Author: Andrew Jarombek
Date: 1/10/2020
"""

import unittest

from .subdomainVisitCount import subdomain_visits


class TestSubdomainVisitCount(unittest.TestCase):

    def test(self):
        domain_array = ["9001 discuss.leetcode.com"]
        result_array = subdomain_visits(domain_array)
        self.assertEqual("['9001 discuss.leetcode.com', '9001 leetcode.com', '9001 com']", str(result_array))


if __name__ == '__main__':
    unittest.main()
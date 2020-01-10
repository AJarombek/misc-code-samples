"""
Practice problem: https://leetcode.com/problems/subdomain-visit-count/
Description:
    A website domain like "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the
    next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com". When we visit a domain like
    "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.

    Now, call a "count-paired domain" to be a count (representing the number of visits this domain received), followed
    by a space, followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".

    We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, (in the same
    format as the input, and in any order), that explicitly counts the number of visits to each subdomain.

Author: Andrew Jarombek
Date: 1/10/2020
"""

import collections


def subdomain_visits(cpdomains: list) -> list:
    subdomain_dict = collections.defaultdict(int)

    for cpdomain in cpdomains:
        split_domain = cpdomain.split()
        count = int(split_domain[0])
        domain = split_domain[1]

        subdomains = [domain]
        for i in range(len(domain) - 1):
            if domain[i] == '.':
                subdomains.append(domain[i + 1:])

        for subdomain in subdomains:
            subdomain_dict[subdomain] = subdomain_dict[subdomain] + count

    return [f"{count} {subdomain}" for subdomain, count in subdomain_dict.items()]
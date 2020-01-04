"""
Unit Test the Practice problem: https://leetcode.com/problems/unique-email-addresses/solution/
Author: Andrew Jarombek
Date: 1/4/2020
"""

# If that is making you happy I am glad for you.  Of course, I just want happiness for you.  It sounds like someone
# else is making you happy and making you feel loved and more.  I'll still always be here for you if you ever need me.
#
# You will always have all my love and support.  You mean the world to me and I'm so grateful to have spent so much time
# with you.  Even if we never spend time together again, I will always be rooting for you and praying for your success.
# All I really know if that you deserve to be loved by whoever you love.  If any of those feelings you had for me still
# remain, you can rest assured that I still love you with all my heart.
# -Andy

import unittest

from .uniqueEmailAddresses import num_unique_emails


class TestUniqueEmailAddresses(unittest.TestCase):

    def test(self):
        email_list = ['an.dy+me@jarombek.com', 'a.n.d.y@jarombek.com', 'andy@jarombek.com', 'andrew@jarombek.com']
        result = num_unique_emails(email_list)
        self.assertEqual(result, 2)


if __name__ == '__main__':
    unittest.main()

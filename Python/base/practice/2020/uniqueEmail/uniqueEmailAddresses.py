"""
Practice problem: https://leetcode.com/problems/unique-email-addresses/solution/
Description:
    Every email consists of a local name and a domain name, separated by the @ sign.  For example, in
    alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.  Besides lowercase letters, these
    emails may contain '.'s or '+'s.

    If you add periods ('.') between some characters in the local name part of an email address, mail sent there will
    be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and
    "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

    If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows
    certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule
    does not apply for domain names.)

    It is possible to use both of these rules at the same time.  Given a list of emails, we send one email to each
    address in the list.  How many different addresses actually receive mails?

Author: Andrew Jarombek
Date: 1/4/2020
"""


def num_unique_emails(emails: list) -> int:
    result_set = set()
    for email in emails:
        cleaned_email = ''
        found_plus = False

        for index, char in enumerate(email):
            if char == '@':
                cleaned_email += email[index:]
                break
            elif char == '+':
                found_plus = True
            elif not found_plus and char != '.':
                cleaned_email += char

        result_set.add(cleaned_email)

    return len(result_set)
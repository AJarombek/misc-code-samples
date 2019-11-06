"""
Interview practice problem: https://leetcode.com/problems/find-and-replace-pattern/.
Result: [SUCCESS - 0:32]
Description:
    You have a list of words and a pattern, and you want to know which words in words matches the pattern.  A word
    matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the
    pattern with p(x), we get the desired word.  Return a list of the words in words that match the given pattern.

Author: Andrew Jarombek
Date: 11/5/2019
"""


def find_and_replace_pattern(words: list, pattern: str) -> list:
    """
    Solve the string pattern matching problem with two maps and nested for loops.  The time complexity is O(nm), where
    n is the length of the words list and m is the length of the pattern.  The space complexity is also O(mn), since
    dictionary's are created for each word and contain entries for each character in the pattern.
    :param words: A list of words to compare with the pattern string.
    :param pattern: A pattern string to compare against words in the words list.
    :return: A list of all the words that match the pattern.
    """
    result = []

    for word in words:
        pat_to_char_map = {}
        char_to_pat_map = {}

        for index, char in enumerate(word):
            pattern_mapping = pat_to_char_map.get(pattern[index])
            char_mapping = char_to_pat_map.get(char)

            # Check to see if the pattern character maps to the proper source string character
            if pattern_mapping is None:
                pat_to_char_map[pattern[index]] = char
            elif pattern_mapping != char:
                break

            # Check to see if the source string character maps to the proper pattern character
            if char_mapping is None:
                char_to_pat_map[char] = pattern[index]
            elif char_mapping != pattern[index]:
                break
        else:
            result.append(word)

    return result


if __name__ == '__main__':
    result = find_and_replace_pattern(["abc", "deq", "mee", "aqq", "dkd", "ccc"], "abb")
    assert result == ["mee", "aqq"]

"""
Interview practice problem: https://leetcode.com/problems/unique-morse-code-words/
Description:
    Given a list of words, each word can be written as a concatenation of the Morse code of each letter. For example,
    "cba" can be written as "-.-..--...", (which is the concatenation "-.-." + "-..." + ".-"). We'll call such a
    concatenation, the transformation of a word.  Return the number of different transformations among all words.

Author: Andrew Jarombek
Date: 10/22/2019
"""


class MorseCodes:

    morse_codes = [".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
                   "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."]

    def unique_morse_representations(self, words):
        """
        Solve the morse code problem with a set data structure.  The implementation of the set is a hash table.  This
        solution takes O(nm) or O(L), where L is the total number of characters in the list of words.  Space complexity
        is the same, since a new string is constructed after each character is observed.
        :param words: A list of words to convert to morse code.
        :return: The number of unique morse codes created from the 'words' list.
        """
        morse_code_set = set()

        for word in words:
            code = ''.join(self.morse_codes[ord(char) - 97] for char in word)
            morse_code_set.add(code)

        return len(morse_code_set)


if __name__ == '__main__':
    # You are so loved.  Don't be afraid, that love isn't going anywhere.
    morse = MorseCodes()
    result = morse.unique_morse_representations(["gin", "zen", "gig", "msg"])
    assert result == 2

"""
Interview practice problem: https://leetcode.com/problems/merge-two-binary-trees/
Result: [FAILURE - 2:00]
Description:
    Given a list of words, list of  single letters (might be repeating) and score of every character.  Return the
    maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more
    times).

Author: Andrew Jarombek
Date: 11/10/2019
"""

import collections
import itertools


def max_score_words_original(words: list, letters: list, score: list) -> int:
    """
    Try to solve the problem using brute force.  This method was too slow to be accepted.
    :param words: A list of words.
    :param letters: A list of lowercase letters.
    :param score: A list of scores for each lowercase letter.
    :return: The maximum score for a combination of words.
    """
    max_points = 0

    letters_dict = collections.defaultdict(int)
    for letter in letters:
        letters_dict[letter] += 1

    for i in range(1, len(words)):
        perms = itertools.permutations(words, r=i)

        for perm in perms:
            points = 0
            letters_dict_copy = letters_dict.copy()
            for char in ''.join(perm):
                if letters_dict_copy[char] > 0:
                    letters_dict_copy[char] -= 1
                    points += score[ord(char) - 97]
                else:
                    points = -1
                    break

            max_points = max(max_points, points)

    return max_points


class MaxScoreWordsWithMemoization:
    # Use memoization as an optimization technique to save prior results
    memo = {}

    def max_score_words(self, words: list, letters: list, score: list) -> int:
        """
        Try to solve the problem using recursion and memoization.  The approach is still brute-force, however the
        hope was that caching results with memoization would speed things up enough to be accepted.  It still wasn't
        fast enough.
        :param words: A list of words.
        :param letters: A list of lowercase letters.
        :param score: A list of scores for each lowercase letter.
        :return: The maximum score for a combination of words.
        """
        max_points = 0

        letters_dict = collections.defaultdict(int)
        for letter in letters:
            letters_dict[letter] += 1

        for i in range(1, len(words)):
            perms = itertools.permutations(words, r=i)

            for perm in perms:
                _, _, points = self.max_score_words_subset(perm, letters_dict.copy(), score, 0)
                max_points = max(max_points, points)

        return max_points

    def max_score_words_subset(self, words: list, letters_dict: dict, score_list: list, score: int) -> tuple:
        """
        Helper function which is called recursively.
        :param words: A list of words.
        :param letters_dict: A dictionary mapping a letter to the number of times it can be used.
        :param score_list: A list of scores for each lowercase letter.
        :param score: The current score of the words combination.
        :return: A tuple containing the updated words, letters_dict, and score parameters.
        """
        if len(words) == 0:
            return words, letters_dict, score
        if score == -1:
            return [], {}, -1

        words_str = ''.join(words)
        if (words_str, score) in self.memo:
            return self.memo[words_str, score]
        else:
            word = words[0]

            if len(words) > 1:
                new_words, new_letters_dict, new_score = \
                    self.max_score_words_subset(words[1:], letters_dict, score_list, score)
                words = new_words
                letters_dict = new_letters_dict
                score = new_score

            if score == -1:
                return [], {}, -1

            for char in word:
                if letters_dict[char] > 0:
                    letters_dict[char] -= 1
                    score += score_list[ord(char) - 97]
                else:
                    return [], {}, -1

            self.memo[''.join(words), score] = (words, letters_dict, score)
            return words, letters_dict, score


if __name__ == '__main__':
    # Test my initial solution
    max_points = max_score_words_original(
        ["xxxz", "ax", "bx", "cx"],
        ["z", "a", "b", "c", "x", "x", "x"],
        [4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 10]
    )
    assert max_points == 27

    solution = MaxScoreWordsWithMemoization()

    # Test the helper function for the solution with Memoization
    _, _, points = solution.max_score_words_subset(
        ['ax', 'bx'],
        {'a': 1, 'b': 1, 'c': 1, 'x': 3, 'z': 1},
        [4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 10],
        0
    )
    assert points == 18

    # Test the solution with Memoization
    max_points = solution.max_score_words(
        ["xxxz", "ax", "bx", "cx"],
        ["z", "a", "b", "c", "x", "x", "x"],
        [4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 10]
    )
    assert max_points == 27

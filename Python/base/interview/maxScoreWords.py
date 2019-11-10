import collections
import itertools


def max_score_words(self, words: list, letters: list, score: list) -> int:
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


class Solution:
    memo = {}

    def maxScoreWords(self, words: list, letters: list, score: list) -> int:
        max_points = 0

        letters_dict = collections.defaultdict(int)
        for letter in letters:
            letters_dict[letter] += 1

        for i in range(1, len(words)):
            perms = itertools.permutations(words, r=i)

            for perm in perms:
                _, _, points = self.maxScoreWordsSubset(perm, letters_dict.copy(), score, 0)
                print(''.join(perm))
                print(points)
                max_points = max(max_points, points)

        return max_points

    def maxScoreWordsSubset(self, words, letters_dict, score_list, score):
        if len(words) == 0:
            return words, letters_dict, score
        if score == -1:
            return [], {}, -1

        words_str = ''.join(words)
        letters_str = ''.join(letters_dict)
        if (words_str, letters_str) in self.memo:
            return self.memo[(words_str, letters_str)]
        else:
            word = words[0]

            if len(words) > 1:
                new_words, new_letters_dict, new_score = self.maxScoreWordsSubset(words[1:], letters_dict, score_list,
                                                                                  score)
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

            self.memo[(''.join(words), ''.join(letters_dict))] = (words, letters_dict, score)
            return words, letters_dict, score


if __name__ == '__main__':
    solution = Solution()
    result = solution.maxScoreWords(["xxxz","ax","bx","cx"], ["z","a","b","c","x","x","x"], [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10])
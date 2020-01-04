"""
Interview practice problem: https://leetcode.com/problems/letter-tile-possibilities/
Description:
    You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible
    non-empty sequences of letters you can make.

Author: Andrew Jarombek
Date: 10/24/2019
"""

import itertools


def num_tile_possibilities(tiles):
    """
    Solve the number tile problem using itertools.permutations.
    :param tiles: A string where each character represents a letter printed on a tile
    :return: The number of unique tile permutations of any length > 0.
    """
    unique_sequences = set()

    for i in range(len(tiles)):
        permutations = itertools.permutations(list(tiles), i + 1)
        for perm in permutations:
            unique_sequences.add(perm)

    return len(unique_sequences)


if __name__ == '__main__':
    result1 = num_tile_possibilities('AAB')
    assert result1 == 8

    result2 = num_tile_possibilities('AAABBC')
    assert result2 == 188

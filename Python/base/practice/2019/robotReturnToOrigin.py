"""
Interview practice problem: https://leetcode.com/problems/robot-return-to-origin/
Description:
    There is a robot starting at position (0, 0), the origin, on a 2D plane. Given a sequence of its moves,
    judge if this robot ends up at (0, 0) after it completes its moves.

    The move sequence is represented by a string, and the character moves[i] represents its ith move.
    Valid moves are R (right), L (left), U (up), and D (down). If the robot returns to the origin after it
    finishes all of its moves, return true. Otherwise, return false.

Author: Andrew Jarombek
Date: 10/31/2019
"""

# She is so strong.  During the tough times I hope she is able to remember how cared for she is.


def judge_circle(moves: str) -> bool:
    """
    Solution to the robot return problem.  This solution takes O(n) time, since it loops through each character in
    the moves string.
    :param moves: Moves that the robot will make
    :return: True if the robot ends at the same location it started, False otherwise.
    """
    x = 0
    y = 0
    for move in moves:
        if move == 'R':
            x += 1
        elif move == 'L':
            x -= 1
        elif move == 'U':
            y += 1
        elif move == 'D':
            y -= 1

    return x == 0 and y == 0


if __name__ == '__main__':
    result1 = judge_circle('LR')
    assert result1

    result2 = judge_circle('UU')
    assert not result2

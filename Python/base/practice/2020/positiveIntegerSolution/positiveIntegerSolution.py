"""
Practice problem: https://leetcode.com/problems/find-positive-integer-solution-for-a-given-equation/
Description:
    Given a function  f(x, y) and a value z, return all positive integer pairs x and y where f(x,y) == z.  The function
    is constantly increasing, i.e.:

    f(x, y) < f(x + 1, y)
    f(x, y) < f(x, y + 1)

    For custom testing purposes you're given an integer function_id and a target z as input, where function_id represent
    one function from an secret internal list, on the examples you'll know only two functions from the list.  You may
    return the solutions in any order.  Constraints:

    1 <= function_id <= 9
    1 <= z <= 100
    It's guaranteed that the solutions of f(x, y) == z will be on the range 1 <= x, y <= 1000
    It's also guaranteed that f(x, y) will fit in 32 bit signed integer if 1 <= x, y <= 1000

Author: Andrew Jarombek
Date: 1/11/2020
"""


class CustomSolution:
    def __init__(self, function):
        self.function = function

    def f(self, x, y):
        return self.function(x, y)


def find_solution(customfunction: CustomSolution, z: int) -> list:
    """
    Solve the problem using x and y pointers.  The time complexity is O(X + Y), and the space complexity is O(N),
    where N is min(X, Y).
    :param customfunction: The custom function used to compute Z from X and Y.
    :param z: The result of the function for all [X, Y] in the result list.
    :return: List of argument pairs that cause the custom function to result in Z.
    """
    result = []

    x = 1
    y = 1000
    while x <= 1000 and y >= 1:
        res = customfunction.f(x, y)
        if res == z:
            result.append([x, y])
            x += 1
            y -= 1
        elif res < z:
            x += 1
        else:
            y -= 1

    return result

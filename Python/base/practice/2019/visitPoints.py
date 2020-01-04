"""
Practice problem: https://leetcode.com/problems/minimum-time-visiting-all-points/
Result: [SUCCESS - 0:12]
Description:
    On a plane there are n points with integer coordinates points[i] = [xi, yi]. Your task is to find the minimum time
    in seconds to visit all points.  You can move according to the next rules:

    In one second always you can either move vertically, horizontally by one unit or diagonally (it means to move one
    unit vertically and one unit horizontally in one second).  You have to visit the points in the same order as they
    appear in the array.

Author: Andrew Jarombek
Date: 11/29/2019
"""


def min_time_to_visit_all_points(points: list) -> int:
    """
    Solve the problem by looping through the points.  The time complexity is O(n) and the space complexity is O(1).
    :param points: The points in the graph to visit in order.
    :return: The number of seconds it takes to visit all points.
    """
    seconds = 0
    current_point = points[0]

    for point in points[1:]:
        diag = min(abs(current_point[0] - point[0]), abs(current_point[1] - point[1]))
        x = abs(current_point[0] - point[0]) - diag
        y = abs(current_point[1] - point[1]) - diag

        seconds += diag + x + y
        current_point = point

    return seconds


if __name__ == '__main__':
    result = min_time_to_visit_all_points([[1, 1], [3, 4], [-1, 0]])
    assert result == 7

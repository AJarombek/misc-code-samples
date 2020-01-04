"""
Interview practice problem.
Description:
    In a 2 dimensional array grid, each value grid[i][j] represents the height of a building located there. We are
    allowed to increase the height of any number of buildings, by any amount (the amounts can be different for
    different buildings). Height 0 is considered to be a building as well.

    At the end, the "skyline" when viewed from all four directions of the grid, i.e. top, bottom, left, and right,
    must be the same as the skyline of the original grid. A city's skyline is the outer contour of the rectangles
    formed by all the buildings when viewed from a distance.

    What is the maximum total sum that the height of the buildings can be increased?

Author: Andrew Jarombek
Date: 10/14/2019
"""


def max_increase_keeping_skyline(grid: list) -> int:
    """
    Solve the city skyline problem by first finding the max height of each column and row.  Then determine the
    height increase at each index by subtracting the original height from either the max row or max column height
    (whichever one is smaller).  This algorithm executes in O(n^2 + n^2) time, simplified to O(n^2) time.
    :param grid: The original city building grid.
    :return: The total height increase of the buildings to maintain the skyline.
    """

    max_row_heights = [0] * len(grid)
    max_column_heights = [0] * len(grid[0])

    for r_index, row_heights in enumerate(grid):
        for c_index, height in enumerate(row_heights):
            max_row_heights[r_index] = max(max_row_heights[r_index], height)
            max_column_heights[c_index] = max(max_column_heights[c_index], height)

    height_increase = 0

    for r_index, row_height in enumerate(max_row_heights):
        for c_index, column_height in enumerate(max_column_heights):
            height_increase = height_increase + min(row_height, column_height) - grid[r_index][c_index]

    return height_increase


if __name__ == '__main__':
    building_grid = [
        [3, 0, 8, 4],
        [2, 4, 5, 7],
        [9, 2, 6, 3],
        [0, 3, 1, 0]
    ]
    increase = max_increase_keeping_skyline(building_grid)
    assert increase == 35

"""
Implement DFS (Depth First Search) to work on a grid.  This is prep work for the Unique Paths III problem
[https://leetcode.com/problems/unique-paths-iii/]
Author: Andrew Jarombek
Date: 12/6/2019
"""


def dfs(grid: list, cell: tuple) -> list:
    """
    DFS (Depth First Search) on a grid.
    :param grid: NxM length grid.
    :param cell: Current cell being viewed while traversing the grid.
    :return: A list of paths from the starting cell in the grid to the final cell in the grid.
    """
    if grid[cell[0]][cell[1]] == 2:
        return [[cell]]
    else:
        new_grid = []
        for row in grid:
            new_grid.append(row.copy())
        new_grid[cell[0]][cell[1]] = 3
        grid = new_grid

    paths = []
    if cell[0] < len(grid) - 1 and grid[cell[0] + 1][cell[1]] != 3:
        down_paths = dfs(grid, (cell[0] + 1, cell[1]))
        for down in down_paths:
            paths.insert(0, down)

    if cell[0] > 0 and grid[cell[0] - 1][cell[1]] != 3:
        up_paths = dfs(grid, (cell[0] - 1, cell[1]))
        for up in up_paths:
            paths.insert(0, up)

    if cell[1] < len(grid[0]) - 1 and grid[cell[0]][cell[1] + 1] != 3:
        right_paths = dfs(grid, (cell[0], cell[1] + 1))
        for right in right_paths:
            paths.insert(0, right)

    if cell[1] > 0 and grid[cell[0]][cell[1] - 1] != 3:
        left_paths = dfs(grid, (cell[0], cell[1] - 1))
        for left in left_paths:
            paths.insert(0, left)

    if len(paths) == 0:
        return []
    else:
        for path in paths:
            path.insert(0, cell)
        return paths


if __name__ == '__main__':
    # 1 is the start, 0 is an empty space, 2 is the end.
    grid = [
        [1, 2]
    ]
    result = dfs(grid, (0, 0))
    assert result == [[(0, 0), (0, 1)]]
    assert len(result) == 1

    grid = [
        [1, 0],
        [0, 2]
    ]

    result = dfs(grid, (0, 0))
    assert result == [[(0, 0), (0, 1), (1, 1)], [(0, 0), (1, 0), (1, 1)]]
    assert len(result) == 2

    grid = [
        [1, 0, 0],
        [0, 0, 2]
    ]

    result = dfs(grid, (0, 0))
    assert result == [
        [(0, 0), (0, 1), (1, 1), (1, 2)],
        [(0, 0), (0, 1), (0, 2), (1, 2)],
        [(0, 0), (1, 0), (1, 1), (1, 2)],
        [(0, 0), (1, 0), (1, 1), (0, 1), (0, 2), (1, 2)]
    ]
    assert len(result) == 4

    grid = [
        [1, 0, 0],
        [0, 0, 0],
        [0, 0, 2]
    ]

    assert result == [
        [(0, 0), (0, 1), (1, 1), (1, 0), (2, 0), (2, 1), (2, 2)],
        [(0, 0), (0, 1), (1, 1), (1, 2), (2, 2)],
        [(0, 0), (0, 1), (1, 1), (2, 1), (2, 2)],
        [(0, 0), (0, 1), (0, 2), (1, 2), (2, 2)],
        [(0, 0), (0, 1), (0, 2), (1, 2), (1, 1), (1, 0), (2, 0), (2, 1), (2, 2)],
        [(0, 0), (0, 1), (0, 2), (1, 2), (1, 1), (2, 1), (2, 2)],
        [(0, 0), (1, 0), (2, 0), (2, 1), (1, 1), (1, 2), (2, 2)],
        [(0, 0), (1, 0), (2, 0), (2, 1), (1, 1), (0, 1), (0, 2), (1, 2), (2, 2)],
        [(0, 0), (1, 0), (2, 0), (2, 1), (2, 2)],
        [(0, 0), (1, 0), (1, 1), (1, 2), (2, 2)],
        [(0, 0), (1, 0), (1, 1), (0, 1), (0, 2), (1, 2), (2, 2)],
        [(0, 0), (1, 0), (1, 1), (2, 1), (2, 2)]
    ]
    assert len(result) == 12

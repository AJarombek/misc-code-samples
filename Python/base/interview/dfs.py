"""
Implement DFS (Depth First Search) to work on a grid.  This is prep work for the Unique Paths III problem
[https://leetcode.com/problems/unique-paths-iii/]
Author: Andrew Jarombek
Date: 12/6/2019
"""


def dfs(grid: list, cell: tuple, paths: list) -> list:
    print(grid)
    for path in paths:
        path.append(cell)

    if grid[cell[0]][cell[1]] == 2:
        return paths
    else:
        grid[cell[0]][cell[1]] = 3

    if cell[0] < len(grid) - 1 and grid[cell[0] + 1][cell[1]] != 3:
        paths += dfs(grid, (cell[0] + 1, cell[1]), paths)
    if cell[0] > 0 and grid[cell[0] - 1][cell[1]] != 3:
        paths += dfs(grid, (cell[0] - 1, cell[1]), paths)
    if cell[1] < len(grid[0]) - 1 and grid[cell[0]][cell[1] + 1] != 3:
        paths += dfs(grid, (cell[0], cell[1] + 1), paths)
    if cell[1] > 0 and grid[cell[0]][cell[1] - 1] != 3:
        paths += dfs(grid, (cell[0], cell[1] - 1), paths)

    return paths


if __name__ == '__main__':
    grid = [
        [1, 0, 0, 0],
        [0, 0, 0, 0],
        [0, 0, 0, 2]
    ]

    result = dfs(grid, (0, 0), [[]])
    print(result)
    print(len(result))

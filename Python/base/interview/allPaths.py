"""
Interview practice problem: https://leetcode.com/problems/all-paths-from-source-to-target/
Description:
    Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1,
    and return them in any order.

    The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j
    for which the edge (i, j) exists.
Example:
    Input: [[1,2], [3], [3], []]
    Output: [[0,1,3],[0,2,3]]
    Explanation: The graph looks like this:
    0--->1
    |    |
    v    v
    2--->3
    There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

Author: Andrew Jarombek
Date: 11/1/2019
"""


def all_paths_source_target(graph: list) -> list:
    """
    Solve the problem with a helper function that runs recursively.  It will generate the result list.
    :param graph: A list representing a directed, acyclic graph.
    :return:  All paths from node 0 to N-1.
    """
    return analyze_path(graph, graph[0], {0}, [0])


def analyze_path(graph: list, current: list, visited: set, path: list) -> list:
    """
    Solve the problem using recursion.
    :param graph: A list representing a directed, acyclic graph.
    :param current: The current node in the graph being visited.
    :param visited: A set of visited nodes.  This is used to optimize node lookups at the expense of memory consumption.
    :param path: A list of the visited nodes so far, in order.
    :return: All paths from node 0 to N-1.
    """
    if len(current) == 0:
        return []

    result = []
    for dest in current:
        if dest == len(graph) - 1:
            result.append(path + [dest])
        elif dest not in visited:
            new_visited = visited & {dest}
            new_path = path + [dest]
            result += analyze_path(graph, graph[dest], new_visited, new_path)

    return result


if __name__ == '__main__':
    result = all_paths_source_target([[1, 2], [3], [3], []])
    assert result == [[0, 1, 3], [0, 2, 3]]

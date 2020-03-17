/**
 * Practice problem: https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/.
 * Result: [SUCCESS: 0:25]
 * Description:
 *      Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.
 *      Return the number of negative numbers in grid.
 *
 * @author Andrew Jarombek
 * @date 3/17/2020
 */

#include "countNegativesMatrix.h"

using namespace std;

int Solution::countNegatives(vector<vector<int>>& grid) {
    // Number of rows
    int rows = grid.size();

    // Number of columns
    int cols = grid[0].size();

    int result = 0;

    for (int i = 0; i < cols; i++) {
        for (int j = 0; j < rows; j++) {
            int value = grid[j][i];

            if (value < 0) {
                result += rows - j;
                break;
            }
        }
    }

    return result;
}

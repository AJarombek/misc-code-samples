/**
 * Main method to test the practice problem.
 * @author Andrew Jarombek
 * @date 3/17/2020
 */

#include "countNegativesMatrix.h"
#include <iostream>
#include <vector>

using namespace std;

int main() {
    cout << "Testing Count Negatives in Matrix Problem" << endl;

    vector<int> row1 = {4, 3, 2, -1};
    vector<int> row2 = {3, 2, 1, -1};
    vector<int> row3 = {1, 1, -1, -2};
    vector<int> row4 = {-1, -1, -2, -3};
    vector<vector<int>> v = {row1, row2, row3, row4};

    int result = Solution::countNegatives(v);
    assert(result == 8);

    cout << "Result:" << endl;
    cout << result << endl;
}

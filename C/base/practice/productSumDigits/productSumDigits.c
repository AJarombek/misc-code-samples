/**
 * Practice problem: https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/.
 * Result: [SUCCESS: 0:05]
 * Description:
 *      Given an integer number n, return the difference between the product of its digits and the sum of its digits.
 *
 * @author Andrew Jarombek
 * @date 3/2/2020
 */

#include "productSumDigits.h"
#include <assert.h>
#include <stdio.h>

int subtractProductAndSum(int n) {
    int product = 1;
    int sum = 0;
    int m = n;

    while (m > 0) {
        product *= m % 10;
        sum += m % 10;
        m = m / 10;
    }

    return product - sum;
}

int main() {
    printf("Practice Problem: Product Sum Digits\n");
    int result = subtractProductAndSum(234);
    assert(result == 15);

    int result = subtractProductAndSum(4421);
    assert(result == 21);
}
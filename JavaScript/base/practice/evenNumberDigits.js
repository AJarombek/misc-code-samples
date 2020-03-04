/**
 * Practice Problem: https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
 * Description:
 *      Given an array nums of integers, return how many of them contain an even number of digits.
 *
 * @author Andrew Jarombek
 * @since 3/3/2020
 */

const findNumbers = (nums) => {
    let result = 0;
    for (let num of nums) {
        result += `${num * 10}`.length % 2;
    }
    return result;
};

module.exports = findNumbers;
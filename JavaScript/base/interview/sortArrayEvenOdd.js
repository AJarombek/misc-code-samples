/**
 * Interview Practice Problem: https://leetcode.com/problems/sort-array-by-parity/
 * Description:
 *      Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed
 *      by all the odd elements of A.  You may return any answer array that satisfies this condition.
 * @author Andrew Jarombek
 * @since 10/27/2019
 */

const assert = (assertion) => {
    console.assert(assertion, `Assertion failed!`);
};

/**
 * Solve the array sorting problem.  This is the slower of the two approaches, because it uses the spread operator on
 * arrays.  The time complexity is O(n^2), because it first loops through each element in the original array.  Inside
 * this loop, a new array is constructed by copying over elements from the 'even' or 'odd' array.  At most the length
 * of the 'even' or 'odd' arrays can be n - 1, where n is the length of the input array.  The space complexity is also
 * O(n^2) because n arrays of at most length n are created.
 * @param array The original unsorted array.
 * @returns {*[]} The new sorted array.
 */
const sortArrayByParity = (array) => {
    let even = [];
    let odd = [];

    array.forEach(i => {
       if (i % 2 === 0) {
           even = [...even, i];
       } else {
           odd = [...odd, i];
       }
    });

    return [...even, ...odd];
};

/**
 * Solve the array sorting problem.  This is the faster of the two approaches, because it does not treat the 'even' and
 * 'odd' arrays as immutable objects.  Therefore, the time complexity is O(n) and the space complexity is O(n).  This
 * is noticeably quicker especially on large arrays.
 * @param array The original unsorted array.
 * @returns {*[]} The new sorted array.
 */
const sortArrayByParityFast = (array) => {
    let even = [];
    let odd = [];

    array.forEach(i => {
       if (i % 2 === 0) {
           even.push(i);
       } else {
           odd.push(i);
       }
    });

    return even.concat(odd);
};

// Test the slow and fast approaches.
const unsortedArray = [3,1,2,4];

const sortedArraySlow = sortArrayByParity(unsortedArray);
const sortedArrayFast = sortArrayByParityFast(unsortedArray);

[2,4,3,1].forEach((value, index) => {
    assert(sortedArraySlow[index] === value);
    assert(sortedArrayFast[index] === value);
});

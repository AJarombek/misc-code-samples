package smallernumbersarray;

import java.util.Arrays;

/**
 * Interview Practice Problem: https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number
 * Result: [SUCCESS: 0:14]
 * Description:
 *      Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is,
 *      for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
 *      Return the answer in an array.
 *
 * @author Andrew Jarombek
 * @since 3/1/2020
 */

public class SmallerNumbersArray {

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int j = 0;
            while (sortedNums[j] < nums[i]) {
                j++;
            }
            result[i] = j;
        }
        return result;
    }
}

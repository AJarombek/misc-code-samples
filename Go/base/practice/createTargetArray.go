/**
 * Practice Problem:
 * Result: [SUCCESS: 1:30]
 * Description:
 * Given two arrays of integers nums and index. Your task is to create target array under the following rules:
 *	+ Initially target array is empty.
 *	+ From left to right read nums[i] and index[i], insert at index index[i] the value nums[i] in target array.
 *	+ Repeat the previous step until there are no elements to read in nums and index.
 * Return the target array.
 *
 * Author: Andrew Jarombek
 * Date: 4/18/2020
 */

package main

import "fmt"

// Solve the problem using a for loop and slices (variable length views on top of an array).  Since the amortized time
// complexity of append() is O(1) [it very rarely is O(n)], the time complexity of this algorithm is O(n).
func createTargetArray(nums []int, index []int) []int {
	target := make([]int, len(nums))

	for i := 0; i < len(nums); i++ {
		insertAt := index[i]

		if insertAt == 0 {
			target = append([]int{nums[i]}, target...)
		} else if insertAt == len(nums) - 1 {
			target[insertAt] = nums[i]
		} else {
			target = append(target[:insertAt + 1], target[insertAt:]...)
			target[insertAt] = nums[i]
		}
	}

	return target[:len(nums)]
}

func main() {
	result := createTargetArray([]int{0, 1, 2, 3, 4}, []int{0, 1, 2, 2, 1})
	fmt.Printf("Result: %v", result)
}
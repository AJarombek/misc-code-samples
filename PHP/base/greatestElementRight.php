<?php

/**
 * Practice Problem: https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/
 * Result: [SUCCESS: 0:05]
 * Description:
 *  Given an array arr, replace every element in that array with the greatest element among the elements to
 *  its right, and replace the last element with -1.  After doing so, return the array.
 *
 * @author Andrew Jarombek
 * @since 3/19/2020
 */

class Solution {

    /**
     * Solve the problem using a simple for loop.
     * @param $arr The array that is modified in place.
     * @return The array instance.
     */
    function replaceElements($arr) {
        $maxRight = -1;
        $right = $arr[count($arr) - 1];
        $arr[count($arr) - 1] = -1;

        for ($i = count($arr) - 2; $i >= 0; $i--) {
            $maxRight = max($maxRight, $right);
            $right = $arr[$i];

            $arr[$i] = $maxRight;
        }

        return $arr;
    }
}

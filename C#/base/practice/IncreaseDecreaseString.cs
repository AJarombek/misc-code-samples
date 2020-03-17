/**
 * Practice Problem: https://leetcode.com/problems/increasing-decreasing-string/
 * Result: [SUCCESS - 1:05]
 * Author: Andrew Jarombek
 * Date: 3/16/2020
 */

public class Solution {
    public string SortString(string s) {
        var sortedStr = String.Concat(s.OrderBy(c => c));
        var result = new System.Text.StringBuilder();

        var index = 0;
        var goingUp = true;
        char? prevChar = null;

        while (sortedStr.Length > 0) {
            char? charToAdd = null;

            if (goingUp) {
                while (charToAdd == null) {
                    if (index == sortedStr.Length - 1) {
                        goingUp = !goingUp;
                        charToAdd = sortedStr[index];
                    } else {
                        if (prevChar == null || sortedStr[index] > prevChar) {
                            charToAdd = sortedStr[index];
                        } else {
                            index++;
                        }
                    }
                }

            } else {
                while (charToAdd == null) {
                    if (index == 0) {
                        goingUp = !goingUp;
                        charToAdd = sortedStr[index];
                    } else {
                        if (prevChar == null || sortedStr[index] < prevChar) {
                            charToAdd = sortedStr[index];
                        } else {
                            index--;
                        }
                    }
                }
            }

            sortedStr = sortedStr.Remove(index, 1);
            result.Append(charToAdd);
            prevChar = charToAdd;

            if (goingUp) {
                index = Math.Min(index + 1, sortedStr.Length - 1);
            } else {
                index = Math.Max(index - 1, 0);
            }
        }

        return result.ToString();
    }
}

#!/usr/bin/swift

/**
 Practice Problem: https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer
 Result: [SUCCESS - 0:20]
 Description:
     Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either
     0 or 1. The linked list holds the binary representation of a number.  Return the decimal value of the number
     in the linked list.
 - Author: Andrew Jarombek
 - Date: 3/10/2020
 */

class ListNode {
    public var val: Int
    public var next: ListNode?

    public init(_ val: Int) {
        self.val = val
        self.next = nil
    }
}

class Solution {
    var length: Int = -1

    func getDecimalValue(_ head: ListNode?) -> Int {
        guard let node = head else {
            return 0
        }

        length += 1
        let index = length

        let prevResult = getDecimalValue(node.next)
        let currResult = node.val << (length - index)
        return prevResult + currResult
    }
}

let rootNode = ListNode(1)
rootNode.next = ListNode(0)
rootNode.next?.next = ListNode(1)

let solution = Solution()
let result = solution.getDecimalValue(rootNode)
print(result)
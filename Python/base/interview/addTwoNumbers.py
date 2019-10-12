"""
Interview practice problem.
Description:
    You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse
    order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
Example:
    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8
    Explanation: 342 + 465 = 807.

Author: Andrew Jarombek
Date: 10/11/2019
"""


class ListNode:
    def __init__(self, x):
        """
        Construct a node in a linked list.
        :param x: The value of the linked list node.
        """
        self.val = x
        self.next = None

    def __repr__(self):
        """
        String representation of the linked list.
        :return: A string containing linked list node values and arrows between each node.
        """
        result = ''
        node = self

        while node is not None:
            result += str(node.val)
            if node.next is not None:
                result += ' -> '

            node = node.next

        return result


class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        """
        Add two numbers together.  Each number is represented as a linked list, so it must be converted to an integer.
        :param l1: A linked list representing the first number.
        :param l2: A linked list representing the second number.
        :return: The result of adding two numbers together.  The format of the result is a linked list with the numbers
        in reverse order.
        """
        num1 = self.linkedListToInt(l1)
        num2 = self.linkedListToInt(l2)

        resultInt = num1 + num2
        resultStr = str(resultInt)

        node = None

        for integer in resultStr:
            newNode = ListNode(int(integer))
            newNode.next = node
            node = newNode

        return node

    def linkedListToInt(self, node: ListNode) -> int:
        """
        Helper method to convert a linked list to an integer.  Note - the numbers in the linked list are in
        reverse order.
        :param node: The linked list.
        :return: An integer representation of the linked list.
        """
        result = 0
        count = 0

        while node is not None:
            result += (node.val * (10 ** count))

            node = node.next
            count += 1

        return result


if __name__ == '__main__':
    node1 = ListNode(2)
    node1.next = ListNode(4)
    node1.next.next = ListNode(3)

    node2 = ListNode(5)
    node2.next = ListNode(6)
    node2.next.next = ListNode(4)

    solution = Solution()
    result = solution.addTwoNumbers(node1, node2)
    print(result)  # 7 -> 0 -> 8

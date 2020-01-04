"""
Interview practice problem: https://leetcode.com/problems/number-of-recent-calls/
Result: [FAILURE (PERFORMANCE) - 0:25]
Description:
    Write a class RecentCounter to count recent requests.  It has only one method: ping(int t), where t represents some
    time in milliseconds.  Return the number of pings that have been made from 3000 milliseconds ago until now.  Any
    ping with time in [t - 3000, t] will count, including the current ping.  It is guaranteed that every call to ping
    uses a strictly larger value of t than before.

Author: Andrew Jarombek
Date: 11/25/2019
"""

from collections import deque


class RecentCounter:
    """
    My initial attempt worked, however it failed performance tests for a large number of pings.
    """

    def __init__(self):
        self.times = []

    def ping(self, t: int) -> int:
        """
        Initial solution using a list of times that pings occur.  The time and space complexity is O(n), where n is
        the total number of pings.
        :param t: The time at which the ping occurs.
        :return: The number of pings in the last 3000ms.
        """
        self.times.append(t)

        recent_after = t - 3000
        recents = 0

        for i in range(len(self.times), 0, -1):
            if self.times[i - 1] >= recent_after:
                recents += 1
            else:
                break

        return recents


class RecentCounterDeque:
    """
    With a hint, solved the problem using a double-ended queue.
    """

    def __init__(self):
        self.times = deque()

    def ping(self, t: int) -> int:
        """
        Second solution popping older pings off the front of the queue, and then getting the length of the queue.
        Worst case time complexity is still O(n), because all the pings could be popped off the queue.  However, in
        many scenarios this method is faster.  The space complexity is still O(n) as well.
        :param t: The time at which the ping occurs.
        :return: The number of pings in the last 3000ms.
        """
        self.times.append(t)

        recent_after = t - 3000

        while self.times[0] < recent_after:
            self.times.popleft()

        return len(self.times)


if __name__ == '__main__':
    recent_counter = RecentCounter()
    result1 = recent_counter.ping(1)
    result2 = recent_counter.ping(100)
    result3 = recent_counter.ping(3001)
    result4 = recent_counter.ping(3002)

    assert all([
        result1 == 1,
        result2 == 2,
        result3 == 3,
        result4 == 3
    ])

    recent_counter_deque = RecentCounterDeque()
    result1 = recent_counter_deque.ping(1)
    result2 = recent_counter_deque.ping(100)
    result3 = recent_counter_deque.ping(3001)
    result4 = recent_counter_deque.ping(3002)

    assert all([
        result1 == 1,
        result2 == 2,
        result3 == 3,
        result4 == 3
    ])

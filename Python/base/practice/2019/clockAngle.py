"""
Clock Angle Problem: Find the angle between hour hand and minute hand at a given time.
Author: Andrew Jarombek
Date: 12/6/2019
"""


def clock_hand_angle_diff(time: str) -> float:
    """
    Calculate the angle difference (in degrees) between the minute and hour hands of a clock at a given time.
    This is an O(1) operation for both execution time and space complexity.
    :param time: A string representing the time represented on an analog clock.  The time is formatted HH:mm.
    :return: The difference between the two clock hands in degrees.
    """
    hour = int(time[:2])
    minute = int(time[3:])

    hour_angle = ((hour % 12) + (minute / 60)) * (360 / 12)
    minute_angle = minute * 360 / 60
    return min(abs(hour_angle - minute_angle), 360 - abs(hour_angle - minute_angle))


if __name__ == '__main__':
    result = clock_hand_angle_diff('12:45')
    assert result == 112.5

    result2 = clock_hand_angle_diff('03:30')
    assert result2 == 75

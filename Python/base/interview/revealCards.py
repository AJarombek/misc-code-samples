"""
Interview practice problem: https://leetcode.com/problems/reveal-cards-in-increasing-order/
Description:
    In a deck of cards, every card has a unique integer.  You can order the deck in any order you want.  Initially, all
    the cards start face down (unrevealed) in one deck.  Now, you do the following steps repeatedly, until all cards
    are revealed:

    Take the top card of the deck, reveal it, and take it out of the deck.
    If there are still cards in the deck, put the next top card of the deck at the bottom of the deck.
    If there are still unrevealed cards, go back to step 1.  Otherwise, stop.

    Return an ordering of the deck that would reveal the cards in increasing order.  The first entry in the answer is
    considered to be the top of the deck.

Author: Andrew Jarombek
Date: 10/30/2019
"""

import collections


def deck_revealed_increasing(deck: list) -> list:
    """
    My initial approach to the problem was to use tuples.  My motivation for using tuples was that appending to the
    beginning of a list is expensive (O(n)), and that operation would need to happen in every loop iteration.  However,
    the time complexity is O(n^2) here because the cards are looped through and then a new tuple is created with a size
    up to the length of cards.  The space complexity is also O(n^2) because n tuples of at most length n are created.
    :param deck: The original unsorted deck of cards.
    :return: The sorted deck of cards.
    """
    deck.sort(reverse=True)
    final_deck = (deck[0],)

    for card in deck[1:]:
        final_deck = (card,) + (final_deck[len(final_deck) - 1],) + final_deck[:len(final_deck) - 1]

    return list(final_deck)


def deck_revealed_increasing_fast(deck: list) -> list:
    """
    After looking through other submissions, I decided that using a deque (double ended queue) would be a faster
    approach to this problem.  The time complexity drops to O(n log n) since appendleft() and pop() operations take O(1)
    time.  The sort() function takes the most time here, overshadowing the for loop.  The space complexity also drops to
    O(n) because a single deque is maintained.
    :param deck: The original unsorted deck of cards.
    :return: The sorted deck of cards.
    """
    deck.sort(reverse=True)
    final_deck = collections.deque()
    final_deck.append(deck[0])

    for card in deck[1:]:
        final_deck.appendleft(final_deck.pop())
        final_deck.appendleft(card)

    return list(final_deck)


if __name__ == '__main__':
    result = deck_revealed_increasing([17, 13, 11, 2, 3, 5, 7])
    assert result == [2, 13, 3, 11, 5, 17, 7]

    result_fast = deck_revealed_increasing_fast([17, 13, 11, 2, 3, 5, 7])
    assert result_fast == [2, 13, 3, 11, 5, 17, 7]

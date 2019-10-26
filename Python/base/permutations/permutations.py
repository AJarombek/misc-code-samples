"""
Understanding how permutation functions work.
Author: Andrew Jarombek
Date: 10/25/2019
"""

import itertools


def permutations(iterable, r=None):
    """
    Get the permutations of an iterable type of a certain length.  This function is a generator which yields results
    on demand.  This function is defined in the Python documentation (I added my own comments to help myself
    understand how it works): https://docs.python.org/3/library/itertools.html#itertools.permutations
    :param iterable: An iterable type that can be converted to a tuple.
    :param r: length of the permutations.
    :return: Yield all the permutations.
    """
    # Convert the iterable type to a tuple.  This allows the input argument to be a number of different potential types.
    pool = tuple(iterable)
    # Get the length of the input iterable after it was converted to a tuple.
    pool_length = len(pool)
    # Determine the length of the permutations.  Either the length is argument 'r' or the length of the input iterable.
    perms_length = pool_length if r is None else r

    # If argument 'r' is greater than the length of the input argument, return nothing.  This isn't a valid use case.
    if perms_length > pool_length:
        return

    # Create a list of indices from 0 to the length of the input iterable.
    indices = list(range(pool_length))
    # Create a list of indices in reverse.
    cycles = list(range(pool_length, pool_length - perms_length, -1))

    # Return the original input iterable, trimmed down the the length of the 'r' argument.
    yield tuple(pool[i] for i in indices[:perms_length])

    while pool_length:
        for i in reversed(range(perms_length)):
            cycles[i] -= 1
            if cycles[i] == 0:
                indices[i:] = indices[i+1:] + indices[i:i+1]
                cycles[i] = pool_length - 1
            else:
                j = cycles[i]
                indices[i], indices[-j] = indices[-j], indices[i]
                yield tuple(pool[i] for i in indices[:perms_length])
                break
        # Yes, Python supports 'else' blocks appended to a 'for' loop.  It's contents run after the for loop finishes
        # executing normally (without a 'break' statement.
        # https://book.pythontips.com/en/latest/for_-_else.html#else-clause
        else:
            return


if __name__ == '__main__':
    permutations_4 = permutations(list('ANDY'), 4)
    for result in permutations_4:
        print(result)

import math


def path_in_zig_zag_tree(label: int) -> list:
    # TODO
    result = []
    row = int(math.ceil(math.log(label, 2)))

    while row >= 1:
        result.append(label)
        prev_first = 2 ** (row - 2)
        first = 2 ** (row - 1)
        last = 2 ** row - 1

        offset = (first - prev_first - 1) - ((label - first) / 2)

        label = prev_first + offset


if __name__ == '__main__':
    pass

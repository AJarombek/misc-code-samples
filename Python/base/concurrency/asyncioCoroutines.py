"""
Demonstrate calling an API with asyncio.
Author: Andrew Jarombek
Date: 10/3/2020
"""

import asyncio

"""
Asyncio example in Python 3.5+
"""


async def predicted_sunday_run_length():
    await asyncio.sleep(1)
    return 12.31


async def predicted_weekly_mileage():
    await asyncio.sleep(2)
    return 44


async def running_predictions():
    sunday_run = await predicted_sunday_run_length()
    weekly_mileage = await predicted_weekly_mileage()
    return sunday_run, weekly_mileage


def main():
    sunday_run, weekly_mileage = asyncio.run(running_predictions())
    print(sunday_run)
    print(weekly_mileage)


"""
Asyncio example in Python 3.4
"""


@asyncio.coroutine
def predicted_sunday_run_length_deprecated():
    yield from asyncio.sleep(1)
    return 13.5


@asyncio.coroutine
def predicted_weekly_mileage_deprecated():
    yield from asyncio.sleep(2)
    return 48


@asyncio.coroutine
def running_predictions_deprecated():
    sunday_run = yield from predicted_sunday_run_length()
    weekly_mileage = yield from predicted_weekly_mileage()
    return sunday_run, weekly_mileage


def main_deprecated():
    try:
        loop = asyncio.get_event_loop()
    except:
        loop = asyncio.new_event_loop()

    sunday_run, weekly_mileage = loop.run_until_complete(running_predictions_deprecated())
    loop.close()

    print(sunday_run)
    print(weekly_mileage)


if __name__ == '__main__':
    main()
    main_deprecated()

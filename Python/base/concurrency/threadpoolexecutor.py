"""
Demonstrate calling an API with the ThreadPoolExecutor class in the concurrent.futures module.  On my machine, this is
approximately 2.5x faster than using requests to make API calls sequentially.
Author: Andrew Jarombek
Date: 10/3/2020
"""

import time
from concurrent import futures

import requests

domain = 'https://jsonplaceholder.typicode.com/'
endpoints = ['posts', 'comments', 'albums', 'photos', 'todos', 'users']


def make_request(endpoint):
    url = f'{domain}{endpoint}'
    print(url)
    response = requests.get(url)
    print(response.status_code)


def make_requests():
    workers = 5
    with futures.ThreadPoolExecutor(workers) as executor:
        # make_request() will be called concurrently from multiple threads.
        res = executor.map(make_request, endpoints)

    return len(list(res))


def main():
    start = time.time()
    make_requests()
    end = time.time()
    print(f'API calls made in: {end - start}')


if __name__ == '__main__':
    main()

"""
Interview practice problem.  https://leetcode.com/problems/encode-and-decode-tinyurl/
Description:
    TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and
    it returns a short URL such as http://tinyurl.com/4e9iAk.  Design the encode and decode methods for the TinyURL
    service.

Author: Andrew Jarombek
Date: 10/19/2019
"""

import random


class Bitly:

    # Store mappings shortUrl->longUrl and longUrl->shortUrl.
    codes = {}

    def encode(self, longUrl):
        """
        Encodes a URL to a shortened URL.  This function takes O(1) time to complete.  Searching the 'codes' dictionary
        for an existing shortUrl for a longUrl takes O(1) time (unless if there are collisions in the underlying
        dictionary structure - which is a hash map).  The for loop to add characters to the encoded string takes a
        constant 5 iterations, which can be simplified to O(1).  Finally adding the two entries in the dictionary takes
        O(1) time (again, unless if there are collisions).  Add all these constant time performances together and you
        get an O(1) runtime.  This function takes up O(1) space itself (a constant two entries are added to the map),
        however since this method can be called many times the 'codes' dictionary can grow to O(n) size.
        """
        if longUrl in self.codes:
            return self.codes[longUrl]

        encodingChars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789'
        encodedString = 'http://tinyurl.com/'.join(random.choice(encodingChars) for _ in range(5))

        self.codes[longUrl] = encodedString
        self.codes[encodedString] = longUrl

        return encodedString

    def decode(self, shortUrl):
        """
        Decodes a shortened URL to its original URL.  This method takes O(1) time (a hash map retrieval by key) and
        has O(1) space complexity (doesn't store any objects on the heap and places a single item on the stack for the
        duration of the method call).
        """
        return self.codes[shortUrl]


if __name__ == '__main__':
    bitly = Bitly()

    long_url = 'https://jarombek.com/blog'
    short_url = bitly.encode(long_url)

    same_long_url = bitly.decode(short_url)
    assert long_url == same_long_url

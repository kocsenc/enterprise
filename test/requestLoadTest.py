#!/usr/env python
"""
Python Version 2
Kocsen Chung
Team 1

Test balancing PayBook

Paybook has to be running locally for this to work, or change URL variable
"""

import requests
import grequests

import unittest


class LoadTestPayBook(unittest.TestCase):
        
    def test_load(self):
        """
        Load Tests Paybook 
        Creates requests synchronously and then asynchronously
        """
        URL = "http://vm343a.se.rit.edu/api/user/"
        USER_COUNT = 15
        NUM_REQS = 15 * 50

        url_ary = []

        # Send requests synchronously
        for i in range(NUM_REQS):
            print("Issuing request #" + str(i+1))

            user_num = i%USER_COUNT

            url_i = URL + str(i) + "/getuser"
            url_ary.append(url_i)
            r = requests.get(url_i)
            self.assertEqual(r.status_code, 200)


        # Send requests all at the same time
        print("Sending " +  str(NUM_REQS) + "requests at the same time")
        rs = (grequests.get(u) for u in url_ary)
        resary = grequests.map(rs)
        for response in resary:
            self.assertEqual(response.status_code, 200)

    
if __name__ == '__main__':
    unittest.main()


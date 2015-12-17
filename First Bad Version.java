/*
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Subscribe to see which companies asked this question
*/
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if (n <= 0) {
            return -1;
        }
        int start = 1;
        int end = n;
        while (start + 1 < end) {
            int middle = start + (end - start) / 2;
            boolean middleResult = isBadVersion(middle);
            if (middleResult == true && isBadVersion(middle - 1) == false) {
                return middle;
            }
            else if (middleResult == false) {
                start = middle + 1;
            }
            else {
                end = middle - 1;
            }
        }
        while (isBadVersion(start) == false) {
            start++;
        }
        return start;
    }
}

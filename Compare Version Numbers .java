/*
Compare two version numbers version1 and version1.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/
public class Solution {
    public int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            return 0;
        }
        String[] verOne = version1.split("\\.");
        String[] verTwo = version2.split("\\.");
        int length = Math.min(verOne.length, verTwo.length);
        for (int i = 0; i < length; i++) {
            int verOneNum = Integer.parseInt(verOne[i]);
            int verTwoNum = Integer.parseInt(verTwo[i]);
            if (verOneNum < verTwoNum) {
                return -1;
            }
            else if (verOneNum > verTwoNum) {
                return 1;
            }
        }
        if (verOne.length > verTwo.length && isNotZero(verOne, verTwo.length)) {
            return 1;
        }
        else if (verOne.length < verTwo.length && isNotZero(verTwo, verOne.length)) {
            return -1;
        }
        return 0;
    }
    
    private boolean isNotZero(String[] str, int index) {
        for (int i = index; i < str.length; i++) {
            int num = Integer.parseInt(str[i]);
            if (num != 0) {
                return true;
            }
        }
        return false;
    }
}

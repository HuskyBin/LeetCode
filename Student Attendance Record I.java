/*
You are given a string representing an attendance record for a student. The record only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False

*/
class Solution {
    public boolean checkRecord(String s) {
        if (s == null) {
            return false;
        }
        boolean hasA = false;
        int start = 0;
        while (start < s.length()) {
            char c = s.charAt(start);
            if (c == 'A') {
                if (hasA) {
                    return false;
                }
                else {
                    hasA = true;
                }
            }
            else if (c == 'L') {
                int end = start + 1;
                while (end < s.length() && s.charAt(end) == 'L') {
                    end++;
                    if (end - start > 2) {
                        return false;
                    }
                }
                start = end - 1;
            }
            start++;
        }
        return true;
    }
}

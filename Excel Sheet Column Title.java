/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
Credits:
Special thanks to @ifanchu for adding this problem and creating all test cases.
*/
public class Solution {
    public String convertToTitle(int n) {
        if (n < 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();

        while ( n > 0) {
            int number = 26;
            int remander = n % number;
            if (remander == 0) {
                sb.append('Z');
            }
            else {
                sb.append((char)('A' + (remander - 1)));
            }
            if (remander == 0) {
                n = n / 26 - 1;
            }
            else {
                n /= 26;
            }
        }
        return sb.reverse().toString();
    }
}


//
string convertToTitle(int n) {
    string res="";
    while(n>0){
        res=char('A'+(n-1)%26)+res;
        n=(n-1)/26;
    }
    return res;
}

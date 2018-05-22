/*
Given a list of strings, you could concatenate these strings together into a loop, where for each string you could choose to reverse it or not. Among all the possible loops, you need to find the lexicographically biggest string after cutting the loop, which will make the looped string into a regular one.

Specifically, to find the lexicographically biggest string, you need to experience two phases:

Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.
Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the character at the cutpoint.
And your job is to find the lexicographically biggest one among all the possible regular strings.

Example:
Input: "abc", "xyz"
Output: "zyxcba"
Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-", 
where '-' represents the looped status. 
The answer string came from the fourth looped one, 
where you could cut from the middle character 'a' and get "zyxcba".
Note:
The input strings will only contain lowercase letters.
The total length of all the strings will not over 1,000.
*/
class Solution {
     public String splitLoopedString(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs.length; i++) {
            StringBuilder reverseBuilder = new StringBuilder(strs[i]);
            String reverse = reverseBuilder.reverse().toString();
            strs[i] = (reverse.compareTo(strs[i]) < 0) ? strs[i] : reverse;
        }
        String result = "";
        for (int i = 0; i < strs.length; i++) {
            String curStr = strs[i];
            String reverseStr = new StringBuilder(curStr).reverse().toString();
            String[] strArr = new String[] {reverseStr, curStr};
            for (String str : strArr) {
                for (int k = 0; k < str.length(); k++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str.substring(k));
                    for (int j = i + 1; j < strs.length; j++) {
                        sb.append(strs[j]);
                    }
                    for (int j = 0; j < i; j++) {
                        sb.append(strs[j]);
                    }
                    sb.append(str.substring(0, k));
                    if (sb.toString().compareTo(result) > 0) {
                        result = sb.toString();
                    }
                }   
            }
            
        }
        return result;
    }
}

/*
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.
*/
public class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        if (abbr == null || word == null) {
            return false;
        }
        int aIndex = 0;
        int bIndex = 0;
        while (aIndex < word.length() && bIndex < abbr.length()) {
            char curWordChar = word.charAt(aIndex);
            char curAbbChar = abbr.charAt(bIndex);
            if (Character.isDigit(curAbbChar)) {
                if (curAbbChar == '0') return false;
                int fastIndex = bIndex + 1;
                while (fastIndex < abbr.length() && Character.isDigit(abbr.charAt(fastIndex))) {
                    fastIndex++;
                }
                aIndex += Integer.valueOf(abbr.substring(bIndex, fastIndex)) - 1;
                bIndex = fastIndex - 1;
            }
            else {
                if (curWordChar != curAbbChar) {
                    return false;
                }
            }
            aIndex++;
            bIndex++;
        } 
        return (aIndex == word.length() && bIndex == abbr.length());
    }
}

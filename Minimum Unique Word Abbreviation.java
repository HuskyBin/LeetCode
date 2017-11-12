
/*
A string such as "word" contains the following abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with the smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.

Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.

Note:
In the case of multiple answers as shown in the second example below, you may return any one of them.
Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
Examples:
"apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")

"apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").

*/
class Solution {
    public String result = "";
    public String minAbbreviation(String target, String[] dictionary) {
        result = target;
        allAbbrs("", 0, target, 0, dictionary);
        return result;
    }
    
    private void allAbbrs(String cur, int count, String target, int index, String[] dictionary) {
        if (cur.length() >= result.length()) {
            return;
        }
        if (index == target.length()) {
            if (count != 0) {
                cur += count;
            }
            if (cur.length() < result.length() && checkDictionary(cur, dictionary)) {
                result = cur;
            }
            return;
        }
        allAbbrs(cur, count + 1, target, index + 1, dictionary);
        if (count != 0) {
            cur = cur + count + target.charAt(index);
        } else {
            cur = cur + target.charAt(index);
        }
        allAbbrs(cur, 0, target, index + 1, dictionary);
    }
    
    
    private boolean checkDictionary(String cur, String[] dictionary) {
        for (String word : dictionary) {
            if (checkWord(cur, word)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkWord(String abbr, String word) {
        int num = 0;
        int aIndex = 0;
        int wIndex = 0;
        while (aIndex < abbr.length() && wIndex < word.length()) {
            if (Character.isLetter(abbr.charAt(aIndex))) {
                wIndex = wIndex + num;
                if (wIndex >= word.length() || word.charAt(wIndex) != abbr.charAt(aIndex)) {
                    return false;
                }
                wIndex++;
                num = 0;
            } else {
                num = num * 10 + abbr.charAt(aIndex) - '0';
            }
            aIndex++;
        }
        wIndex += num;
        return (aIndex == abbr.length() && wIndex == word.length());
    }
}

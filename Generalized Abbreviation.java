/*
Write a function to generate the generalized abbreviations of a word.

Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
*/
public class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> resultList = new ArrayList<>();
        if (word == null) {
            return resultList;
        }
        generateCore(word, 0, "", 0, resultList);
        return resultList;
    }
    
    private void generateCore(String word, int position, String cur, int count, List<String> resultList) {
        if (position >= word.length()) {
            if (count > 0) {
                cur += count;
            }
            resultList.add(cur);
        }
        else {
            generateCore(word, position + 1, cur, count + 1, resultList);
            generateCore(word, position + 1, cur + ((count > 0) ? count : "") + word.charAt(position), 0, resultList);
        }
    }
}

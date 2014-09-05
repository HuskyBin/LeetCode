/*
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.
*/
public class Solution {
    public List<String> fullJustify(String[] words, int L) {
        List<String> resultList = new ArrayList<>();
        if (words == null || words.length == 0 || L <= 0) {
            resultList.add("");
            return resultList;
        }
        int currentSize = words[0].length();
        int startIndex = 0;
        for (int i = 1; i < words.length; i++) {
            currentSize += words[i].length() + 1;
            if (currentSize > L) {
                String oneLine = getOneLineString(words, startIndex, i - 1, currentSize - words[i].length() - 1, L);
                startIndex = i;
                currentSize = words[i].length();
                resultList.add(oneLine);
            }
            else if (currentSize == L){
                if (i != words.length - 1) {
                    String oneLine = getOneLineString(words, startIndex, i, currentSize, L);
                    startIndex = i + 1;
                    currentSize = words[i + 1].length();
                    i++;
                    resultList.add(oneLine);
                }
            }
        }
        String lastLine = getLastLineString(words, startIndex, words.length - 1, currentSize, L);
        resultList.add(lastLine);
        return resultList;
    }

    private String getOneLineString(String[] words, int startIndex, int endIndex, int size, int L) {
        StringBuilder sb = new StringBuilder();
        if (startIndex == endIndex) {
            sb.append(words[startIndex]);
            int spaceNum = L - size;
            while (spaceNum > 0) {
                sb.append(' ');
                spaceNum--;
            }
            return sb.toString();
        }
        int evenSpace = ((L - size) / (endIndex - startIndex)) + 1;
        int moreSpace = (L - size) % (endIndex - startIndex);
        int startNumber = startIndex;
        while (startNumber < endIndex) {
            sb.append(words[startNumber]);
            int tempEven = evenSpace;
            while (tempEven > 0) {
                sb.append(' ');
                tempEven--;
            }
            if (moreSpace > 0) {
                sb.append(' ');
                moreSpace--;
            }
            startNumber++;
        }
        sb.append(words[endIndex]);
        return sb.toString();
    }


    private String getLastLineString(String[] words, int startIndex, int endIndex, int size, int L) {
        StringBuilder sb = new StringBuilder();
        int lastEmpty = L - size;
        int startNumber = startIndex;
        while (startNumber < endIndex) {
            sb.append(words[startNumber]);
            sb.append(' ');
            startNumber++;
        }
        sb.append(words[endIndex]);
        while (lastEmpty > 0) {
            sb.append(' ');
            lastEmpty--;
        }
        return sb.toString();
    }
}

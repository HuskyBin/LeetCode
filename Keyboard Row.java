/*
Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.


American keyboard


Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]
Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.

*/
class Solution {
    public String[] findWords(String[] words) {
        if (words == null) {
            return null;
        }
        Set<Character> s1 = new HashSet<>(
        Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p')
        );
        
        Set<Character> s2 = new HashSet<>(
        Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l')
        );
        
        Set<Character> s3 = new HashSet<>(
        Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm')
        );
        List<String> result = new ArrayList<>();
        for (String word : words) {
            boolean hasS1 = false;
            boolean hasS2 = false;
            boolean hasS3 = false;
            boolean isResult = true;
            for (char c : word.toCharArray()) {
                char uppercase = Character.toLowerCase(c);
                if (s1.contains(uppercase)) {
                    hasS1 = true;
                }
                if (s2.contains(uppercase)) {
                    hasS2 = true;
                }
                if (s3.contains(uppercase)) {
                    hasS3 = true;
                }
                if ((hasS1 && hasS2) || (hasS1 && hasS3) || (hasS2 && hasS3)) {
                    isResult = false;
                    break;
                }
            }
            if (isResult) {
                result.add(word);
            }
        }
        String[] resultArr = new String[result.size()];
        return result.toArray(resultArr);
        
    }
}

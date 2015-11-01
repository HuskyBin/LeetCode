/*
You are playing the following Bulls and Cows game with your friend: You write a 4-digit secret number and ask your friend to guess it, each time your friend guesses a number, you give a hint, the hint tells your friend how many digits are in the correct positions (called "bulls") and how many digits are in the wrong positions (called "cows"), your friend will use those hints to find out the secret number.

For example:

Secret number:  1807
Friend's guess: 7810
Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
*/
public class Solution {
    public String getHint(String secret, String guess) {
        if (secret == null || guess == null || secret.length() != guess.length()) {
            return null;
        }
        Map<Character, Integer> map = new HashMap<>();
        int hit = 0;
        for (int i = 0; i < secret.length(); i++) {
            char curChar = secret.charAt(i);
            char gusChar = guess.charAt(i);
            if (curChar == gusChar) {
                hit++;
            }
            else if (map.containsKey(curChar)) {
                map.put(curChar, map.get(curChar) + 1);
            }
            else {
                map.put(curChar, 1);
            }
        }
        int miss = 0;
        
        for (int i = 0; i < secret.length(); i++) {
            char secChar = secret.charAt(i);
            char guesChar = guess.charAt(i);
            if (secChar == guesChar) {
                continue;
            }
            else if (map.containsKey(guesChar)) {
                if (map.get(guesChar) != 0) {
                    miss++;
                    map.put(guesChar, map.get(guesChar) - 1);
                }
            }
        }
        return String.valueOf(hit) + "A" + String.valueOf(miss) + "B";
    }
}

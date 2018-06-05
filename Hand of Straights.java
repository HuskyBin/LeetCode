/*
Alice has a hand of cards, given as an array of integers.

Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.

Return true if and only if she can.

 

Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
Example 2:

Input: hand = [1,2,3,4,5], W = 4
Output: false
Explanation: Alice's hand can't be rearranged into groups of 4.
*/
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand == null) {
            return false;
        }

        Arrays.sort(hand);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int start = 0;
        while (start < hand.length) {
            if (map.getOrDefault(hand[start], 0) > 0) {
                for (int i = 0; i < W; i++) {
                    if (map.getOrDefault(hand[start] + i, 0) == 0) {
                        return false;
                    }
                    map.put(hand[start] + i, map.get(hand[start] + i) - 1);
                }
            }
            start++;
        }
        return true;
    }
}

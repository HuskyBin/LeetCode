/*
On a table are N cards, with a positive integer printed on the front and back of each card (possibly different).

We flip any number of cards, and after we choose one card. 

If the number X on the back of the chosen card is not on the front of any card, then this number X is good.

What is the smallest number that is good?  If no number is good, output 0.

Here, fronts[i] and backs[i] represent the number on the front and back of card i. 

A flip swaps the front and back numbers, so the value on the front is now on the back and vice versa.

Example:

Input: fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
Output: 2
Explanation: If we flip the second card, the fronts are [1,3,4,4,7] and the backs are [1,2,4,1,3].
We choose the second card, which has number 2 on the back, and it isn't on the front of any card, so 2 is good.
 

Note:

1 <= fronts.length == backs.length <= 1000.
1 <= fronts[i] <= 2000.
1 <= backs[i] <= 2000.
*/
class Solution {
    public int flipgame(int[] fronts, int[] backs) {
        if (fronts == null || backs == null) {
        	return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < fronts.length; i++) {
        	if (fronts[i] == backs[i]) {
        		set.add(fronts[i]);
        	}
        }
        int result = 99999;
        for (int i = 0; i < fronts.length; i++) {
        	if (!set.contains(fronts[i])) {
        		result = Math.min(result, fronts[i]);
        	}
            if (!set.contains(backs[i])) {
                result = Math.min(result, backs[i]);
            }
        }
        return result % 99999;
    }
}

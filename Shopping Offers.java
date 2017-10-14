/*
In LeetCode Store, there are some kinds of items to sell. Each item has a price.

However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.

You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.

Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.

You could use any of special offers as many times as you want.

Example 1:
Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
Output: 14
Explanation: 
There are two kinds of items, A and B. Their prices are $2 and $5 respectively. 
In special offer 1, you can pay $5 for 3A and 0B
In special offer 2, you can pay $10 for 1A and 2B. 
You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
Example 2:
Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
Output: 11
Explanation: 
The price of A is $2, and $3 for B, $4 for C. 
You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C. 
You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C. 
You cannot add more items, though only $9 for 2A ,2B and 1C.
Note:
There are at most 6 kinds of items, 100 special offers.
For each item, you need to buy at most 6 of them.
You are not allowed to buy more items than you want, even if that would lower the overall price.
*/
class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        Map<String, Integer> map = new HashMap<>();
        return dfsCore(price, special, needs, map);
    }
    
    private int dfsCore(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<String, Integer> map) {
        String key = getKey(needs);
        if (map.containsKey(key)) {
            map.get(key);
        }
        if (isZero(needs)) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        
        for (List<Integer> s : special) {
            List<Integer> copyNeeds = new ArrayList<>(needs);
            boolean isValid = true;
            for (int i = 0; i < copyNeeds.size(); i++) {
                if (copyNeeds.get(i) < s.get(i)) {
                    isValid = false;
                    break;
                }
                copyNeeds.set(i, copyNeeds.get(i) - s.get(i));
            }
            if (isValid) {
                result = Math.min(result, s.get(copyNeeds.size()) + dfsCore(price, special, copyNeeds, map));
            }
        }
        int noSpecial = 0;
        for (int i = 0; i < needs.size(); i++) {
            noSpecial += needs.get(i) * price.get(i);
        }
        result = Math.min(result, noSpecial);
        map.put(key, result);
        return result;
    }
    
    private String getKey(List<Integer> needs) {
        return needs.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
    
    private boolean isZero(List<Integer> needs) {
        return !needs.stream().anyMatch(value -> value != 0);
    }
}

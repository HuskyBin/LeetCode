/*
A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:

A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
Example 1:

Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:

Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

Output:
1

Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.
*/
class Solution {
     public int minTransfers(int[][] transactions) {
        if (transactions == null) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] t : transactions) {
            map.put(t[0], map.getOrDefault(t[0], 0) + t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) - t[2]);
        }
        List<Integer> account = new ArrayList<>();
        for (int user : map.keySet()) {
            if (map.get(user) != 0) {
                account.add(map.get(user));
            }
        }
        
        return dfsCore(account, 0, 0);
        
    }
    
    private int dfsCore(List<Integer> account, int start, int num) {
        int result = Integer.MAX_VALUE;
        while (start < account.size() && account.get(start) == 0) {
            start++;
        }
        for (int i = start + 1; i < account.size(); i++) {
            if (account.get(start) * account.get(i) < 0) {
                account.set(i, account.get(i) + account.get(start));
                result = Math.min(result, dfsCore(account, start + 1, num + 1));
                account.set(i, account.get(i) - account.get(start));
            }
        }
        return result == Integer.MAX_VALUE ? num : result;
    }
}

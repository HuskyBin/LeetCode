/*
Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:
Input: [5, 4, 3, 2, 1]
Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
For the left two athletes, you just need to output their relative ranks according to their scores.
Note:
N is a positive integer and won't exceed 10,000.
All the scores of athletes are guaranteed to be unique.
*/
public class Solution {
    class Obj {
        public int val;
        public int index;

        public Obj(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
    
    public String[] findRelativeRanks(int[] nums) {
        String[] result = new String[nums.length];
        if (nums == null || nums.length == 0) {
            return result;
        }
        List<Obj> ObjList = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            Obj newObj = new Obj(nums[i], i);
            ObjList.add(newObj);
        }
        Collections.sort(ObjList, new Comparator<Obj>(){
            public int compare(Obj a, Obj b) {
                return b.val - a.val;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            Obj curObj = ObjList.get(i);
            if (i == 0) {
                result[curObj.index] = "Gold Medal";
            }
            else if (i == 1) {
                result[curObj.index] = "Silver Medal";
            }
            else if (i == 2) {
                result[curObj.index] = "Bronze Medal";
            }
            else {
                result[curObj.index] = String.valueOf(i + 1);
            }
        }
        return result;
    }
}

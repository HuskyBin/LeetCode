/*
Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/
public class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null) {
            return "";
        }
        Integer[] numsInteger = Arrays.stream( nums ).boxed().toArray( Integer[]::new );
        Arrays.sort(numsInteger, new Comparator<Integer>(){
            public int compare(Integer a, Integer b) {
                String aStr = a.toString();
                String bStr = b.toString();
                return -(aStr + bStr).compareTo(bStr + aStr);
            }
        });
        
        StringBuilder sb = new StringBuilder();
        boolean allZero = false;
        for (int a : numsInteger) {
            if (a != 0) {
                allZero = true;
            }
            sb.append(a);
        }
        if (allZero == false) {
            return "0";
        }
        return sb.toString();
    }
}

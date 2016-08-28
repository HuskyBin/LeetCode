/*
Given a nested list of integers represented as a string, implement a parser to deserialize it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Note: You may assume that the string is well-formed:

String is non-empty.
String does not contain white spaces.
String contains only digits 0-9, [, - ,, ].
Example 1:

Given s = "324",

You should return a NestedInteger object which contains a single integer 324.
Example 2:

Given s = "[123,[456,[789]]]",

Return a NestedInteger object containing a nested list with 2 elements:

1. An integer containing value 123.
2. A nested list containing two elements:
    i.  An integer containing value 456.
    ii. A nested list with one element:
         a. An integer containing value 789.
*/
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public NestedInteger deserialize(String s) {
        if (s == null) {
            return null;
        }
        NestedInteger result = new NestedInteger();
        if (s.length() == 0) {
            return result;
        }
        if (s.charAt(0) == '[') {
            List<NestedInteger> nestValue = new ArrayList<>();
            String subString = s.substring(1, s.length() - 1);
            List<String> subValue = seperateString(subString);
            for (String sub : subValue) {
                NestedInteger subNestedInteger = deserialize(sub);
                if (subNestedInteger != null) {
                    nestValue.add(subNestedInteger);
                }
            }
            for (NestedInteger i : nestValue) {
                result.add(i);
            }
        }
        else {
            result.setInteger(Integer.valueOf(s));
        }
        return result;
    }
    
    private List<String> seperateString(String s) {
        List<String> resultList = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return resultList;
        }
        int firstIndex = 0;
        int secondIndex = 0;
        int bracketSum = 0;
        while (firstIndex < s.length() && secondIndex < s.length()) {
            while (secondIndex < s.length()) {
                char curChar = s.charAt(secondIndex); 
                if (curChar == '[') {
                    bracketSum++;
                }
                else if (curChar == ']') {
                    bracketSum--;
                }
                else if (curChar == ',') {
                    if (bracketSum == 0) {
                        break;
                    }
                }
                secondIndex++;
            }
            resultList.add(s.substring(firstIndex, secondIndex));
            secondIndex++;
            firstIndex = secondIndex;
        }
        return resultList;
    } 
}

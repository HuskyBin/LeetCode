/*
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
*/
public class Solution {
    public boolean isValidSerialization(String preorder) {
        if (preorder == null) return false;
        Queue<Integer> queue = new LinkedList<>();
        
        int index = 0;
        if (preorder.charAt(index) == '#' && preorder.length() == 1) return true;
        String nextStr = readNext(preorder, index);
        if (!nextStr.equals("#")) {
            queue.add(Integer.valueOf(nextStr));    
        }
        
        index += nextStr.length() + 1;
        while (index < preorder.length() && queue.size() > 0) {
            String strFirst = readNext(preorder, index);
            
            index += strFirst.length() + 1;
            if (index >= preorder.length()) return false;
            String strSecond = readNext(preorder, index);
            if (!strFirst.equals("#")) {
                queue.add(Integer.valueOf(strFirst));
            }
            if (!strSecond.equals("#")) {
                queue.add(Integer.valueOf(strSecond));
            }
            queue.poll();
            index += strSecond.length() + 1;
        }
        if (index < preorder.length() || queue.size() > 0) return false;
        return true;
    }
    
    private String readNext(String str, int index) {
        StringBuilder sb = new StringBuilder();
        while (index < str.length()) {
            if (str.charAt(index) == ',') break;
            sb.append(str.charAt(index));
            index++;
        }
        return sb.toString();
    }
}

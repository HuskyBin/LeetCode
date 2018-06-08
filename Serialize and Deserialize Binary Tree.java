/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless
*/
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return sb.toString();
        }
        serializeDfs(root, sb);
        return sb.toString();
    }
    
    private void serializeDfs(TreeNode pNode, StringBuilder sb) {
        if (pNode == null) {
            sb.append("#,");
            return;
        }
        sb.append(pNode.val);
        sb.append(",");
        serializeDfs(pNode.left, sb);
        serializeDfs(pNode.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        String[] strs = data.split(",");
        int[] count = new int[1];
        count[0] = 0;
        return deserializeDfs(strs, count);
    }
    
    private TreeNode deserializeDfs(String[] strs, int[] count) {
        if (count[0] >= strs.length) {
            return null;
        }
        String str = strs[count[0]];
        ++count[0];
        if (str.equals("#")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(str));
        node.left = deserializeDfs(strs, count);
        node.right = deserializeDfs(strs, count);
        return node;
    }
}

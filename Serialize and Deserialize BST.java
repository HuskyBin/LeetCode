/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.


*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// better one
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return sb.toString();
        }
        dfsCore(root, sb);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    
    private void dfsCore(TreeNode pNode, StringBuilder sb) {
        if (pNode == null) {
            return;
        }
        sb.append(pNode.val);
        sb.append("#");
        dfsCore(pNode.left, sb);
        dfsCore(pNode.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        String[] strs = data.split("#");
        TreeNode pNode = dfsCreateTree(strs, 0, strs.length - 1);
        return pNode;
    }
    
    private TreeNode dfsCreateTree(String[] strs, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return null;
        }
        TreeNode pNode = new TreeNode(Integer.valueOf(strs[startIndex]));
        int lastSmallerIndex = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (Long.valueOf(strs[i]) <= pNode.val) {
                lastSmallerIndex = i;
            } else {
                break;
            }
        }
        pNode.left = dfsCreateTree(strs, startIndex + 1, lastSmallerIndex);
        pNode.right = dfsCreateTree(strs, lastSmallerIndex + 1, endIndex);
        return pNode;
    }
}

//
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root == null) {
            return sb.toString();
        }
        serializeCore(root, sb);
        return sb.toString();
    }
    
    private void serializeCore(TreeNode pNode, StringBuilder sb) {
        if (pNode == null) {
            return;
        }
        sb.append(pNode.val);
        sb.append(",");
        serializeCore(pNode.left, sb);
        serializeCore(pNode.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        String[] values = data.split(",");
        for (String value : values) {
            queue.add(Integer.valueOf(value));
        }
        return getNode(queue);
    }
    
    private TreeNode getNode(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        
        int value = queue.poll();
        TreeNode pNode = new TreeNode(value);
        Queue<Integer> subQueue = new LinkedList<>();
        while (!queue.isEmpty() && queue.peek() <= value) {
            subQueue.add(queue.poll());
        }
        pNode.left = getNode(subQueue);
        pNode.right = getNode(queue);
        return pNode;
    }
    

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

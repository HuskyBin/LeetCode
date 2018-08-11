
/*
Print a binary tree in an m*n 2D string array following these rules:

The row number m should be equal to the height of the given binary tree.
The column number n should always be an odd number.
The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
Each unused space should contain an empty string "".
Print the subtrees following the same rules.
Example 1:
Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]
Example 2:
Input:
     1
    / \
   2   3
    \
     4
Output:
[["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
Example 3:
Input:
      1
     / \
    2   5
   / 
  3 
 / 
4 
Output:

[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]

分析：
It could be fairly easy when we made our first observation on the problem. For the output matrix, the number of rows is height of the tree. What about the number of columns?

row = 1 => col = 1 = 2^1 - 1
row = 2 => col = 3 = 2^2 - 1
row = 3 => col = 7 = 2^3 - 1
row = 4 => col = 15 = 2^4 - 1
...
row = m => col = 2^m - 1
This can be derived from the number of leaves of a full tree (i.e 2^(height - 1)) with spaces joined (i.e 2^(height - 1) - 1).

Then we can fill the node in level by level. Another observation is we always print a node at the center of its subtree index range. What I mean is for the left or right child of a node, the subtree rooted at the child will use half of the indices of the node.

root is at the center of left and right, say mid
root.left (if not null) is at the center of left and mid - 1
root.right (if not null) is at the center of mid + 1 and right
Then we can easily have our solution as we always keep track of the left and right of the node.
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

// Better solution DFS
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        int height = getHeight(root);
        int width = (int)Math.pow(2, height) - 1;
        for (int i = 0; i < height; i++) {
            List<String> oneLine = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                oneLine.add("");
            }
            results.add(oneLine);
        }
        
        dfsCore(root, 0, width - 1, 0, height, results);
        return results;
    }
    
    private void dfsCore(TreeNode pNode, int start, int end, int curHeight, int height, List<List<String>> results) {
        if (pNode == null || curHeight == height) {
            return;
        }
        int mid = start + (end - start) / 2;
        results.get(curHeight).set(mid, String.valueOf(pNode.val));
        dfsCore(pNode.left, start, mid, curHeight + 1, height, results);
        dfsCore(pNode.right, mid + 1, end, curHeight + 1, height, results);
    }
    
    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
}



class Solution {
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        int row = getHeight(root);
        int column = (int) Math.pow(2, row) - 1;
        for (int i = 0; i < row; i++) {
            List<String> oneRow = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                oneRow.add("");
            }
            result.add(oneRow);
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<int[]> indexQueue = new LinkedList<>();
        queue.add(root);
        indexQueue.add(new int[] {0, column - 1});
        int curRow = -1;
        while (!queue.isEmpty()) {
            curRow++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                int[] index = indexQueue.poll();
                int curColumn = (index[0] + index[1]) / 2;
                result.get(curRow).set(curColumn, String.valueOf(curNode.val));
                if (curNode.left != null) {
                    queue.add(curNode.left);
                    indexQueue.add(new int[] {index[0], curColumn - 1});
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                    indexQueue.add(new int[] {curColumn + 1, index[1]});
                }
            }
        }
        return result;
    }
    
    private int getHeight(TreeNode pNode) {
        if (pNode == null) {
            return 0;
        }
        return Math.max(getHeight(pNode.left), getHeight(pNode.right)) + 1;
    }
}

// dfs
public List<List<String>> printTree(TreeNode root) {
    List<List<String>> res = new LinkedList<>();
    int height = root == null ? 1 : getHeight(root);
    int rows = height, columns = (int) (Math.pow(2, height) - 1);
    List<String> row = new ArrayList<>();
    for(int i = 0; i < columns; i++)  row.add("");
    for(int i = 0; i < rows; i++)  res.add(new ArrayList<>(row));
    populateRes(root, res, 0, rows, 0, columns - 1);
    return res;
}

public void populateRes(TreeNode root, List<List<String>> res, int row, int totalRows, int i, int j) {
    if (row == totalRows || root == null) return;
    res.get(row).set((i+j)/2, Integer.toString(root.val));
    populateRes(root.left, res, row+1, totalRows, i, (i+j)/2 - 1);
    populateRes(root.right, res, row+1, totalRows, (i+j)/2+1, j);
}

public int getHeight(TreeNode root) {
     if (root == null) return 0;
     return 1 + Math.max(getHeight(root.left), getHeight(root.right));
}

/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
*/
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> resultList = new ArrayList<>();
        if (n <= 0) {
            resultList.add(null);
            return resultList;
        }
        
        resultList = generateTreesCore(1, n);
        return resultList;
    }
    
    private List<TreeNode> generateTreesCore(int start, int end) {
        if (start > end) {
            return null;
        }
        List<TreeNode> allCandidates = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> allLeftNodes = generateTreesCore(start, i - 1);
            List<TreeNode> allRightNodes = generateTreesCore(i + 1, end);
            if (allLeftNodes != null && allRightNodes != null) {
                for (TreeNode left : allLeftNodes) {
                    for (TreeNode right : allRightNodes) {
                        TreeNode newNode = new TreeNode(i);
                        newNode.left = left;
                        newNode.right = right;
                        allCandidates.add(newNode);
                    }
                }
            }
            else if (allLeftNodes != null) {
                for (TreeNode left : allLeftNodes) {
                    TreeNode newNode = new TreeNode(i);
                    newNode.left = left;
                    allCandidates.add(newNode);
                }
            }
            else if (allRightNodes != null) {
                for (TreeNode right : allRightNodes) {
                    TreeNode newNode = new TreeNode(i);
                    newNode.right = right;
                    allCandidates.add(newNode);
                }
            }
            else {
                TreeNode newNode = new TreeNode(i);
                allCandidates.add(newNode);
            }
        }
        return allCandidates;
    }
}

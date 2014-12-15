/*
Fair Binary Tree Maximum Path Sum Show Result My Submissions

23% Accepted
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

Example
Given the below binary tree,

       1
      / \
     2   3

*/
/*
为了简化分析，我们先假设二叉树中所有节点的值都是正数。通过观察可以发现，一棵二叉树的最大路径，就是其左子树的最大路径加上右子树的最大路径。看起来可以从根节点出发通过深度优先递归来求解：

函数 查找路径

   如果是叶子节点，返回叶子节点的值

   如果不是叶子节点

      左子树路径和 ＝ 查找路径（左子树）

      右子树路径和 ＝ 查找路径（右子树）

      如果左子树路径＋右子树路径和＋当前节点值 > 当前最大路径，更新最大路径

      返回左子树路径＋右子树路径和＋当前节点值

用题目中的简单例子来验证，是可以得出答案的。但是使用复杂一点的树来验证后就发现其中的问题了，如

              1

           /      \

         2         3

      /      \

    4        5

使用前面的伪代码得出的结果是15，但是其实答案应该是11，由3，1，2，5或者2，4，5得到。分析可以发现问题在于计算2，4，5这棵子树时，它的最长路径为11，这是正确的。但是当它作为左子树向父节点返回最长路径时，因该返回7而不是11。因为从1出发不走重复路径不可能同时到达4或5的－通常二叉树节点路径的定义是每个节点只能访问一次，通过测试数据也可以验证题目就是这样要求的。因此我们需要两个最大值，一个是当前树的最大路径，即前面伪代码算出来的那个值；另一个是当前树向父节点提供的最大路径，这个值应该是根节点的值加上路径最长的子树那边的最大路径。我们向上层递归函数返回这个值。

好了，现在全是正数的情况解决了。让我们开始把负数引入。负数引入后，将会导致以下几个变化：

叶子节点的值也有可能成为最大路径。在全是正数的情形下，叶子节点的值肯定不可能会是最大路径，因为加上父节点的值后必然会变大。有了负数以后，这个情况就不成立了，如：
                －1

              /      

            3

        这时最大路径就是3。
*/
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        // write your code here
        if (root == null) {
            return max;
        }
        maxPathSumHelper(root);
        return max;
    }
    
    private int maxPathSumHelper(TreeNode pNode) {
        if (pNode == null) {
            return 0;
        }
        int curVal = pNode.val;
        int leftMax = maxPathSumHelper(pNode.left);
        int rightMax = maxPathSumHelper(pNode.right);
        if (leftMax > 0) {
            curVal += leftMax;
        }
        if (rightMax > 0) {
            curVal += rightMax;
        }
        max = Math.max(max, curVal);
        return Math.max(pNode.val, Math.max(pNode.val + leftMax, pNode.val + rightMax));
    }
}

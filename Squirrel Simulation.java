/*
There's a tree, a squirrel, and several nuts. Positions are represented by the cells in a 2D grid. Your goal is to find the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one. The squirrel can only take at most one nut at one time and can move in four directions - up, down, left and right, to the adjacent cell. The distance is represented by the number of moves.

Example 1:
Input: 
Height : 5
Width : 7
Tree position : [2,2]
Squirrel : [4,4]
Nuts : [[3,0], [2,5]]
Output: 12
Explanation:

Note:
All given positions won't overlap.
The squirrel can take at most one nut at one time.
The given positions of nuts have no order.
Height and width are positive integers. 3 <= height * width <= 10,000.
The given positions contain at least one nut, only one tree and one squirrel.
*/
这道题是关于可爱的小松鼠的题目，不由得让人想起来冰河世纪里面的那只对粟子执着追求的原始松鼠。每天在校园里也能见到抱着粟子啃的小家伙，
有的挺个大白肚皮，吃的巨肥，完全没有天敌啊。本题说有一只小松鼠，一堆在不同位置的粟子，还有一棵树，小松鼠目标是把所有的粟子都运到树的位置
，问怎样的顺序可以使用最少的步数。那么我们这么想，如果小松鼠本身就在树的位置，那么把所有的栗子运回树的步数就是一个定值，为每个粟子距树的距离总和乘以2。
那么只有当小松鼠不在树的位置时候，它首先要走到一个粟子的位置，然后再去树那儿。而且一旦小松鼠到了树那，再出发，之后的步数就是定值了。
所以关键就在于决定小松鼠首先去哪个粟子那。博主最开始犯了一个这道题很容易犯的一个错误，就是在选起始粟子的时候的判定条件是松鼠到该粟子的距离加上该粟子
到树的距离之和最小当作判定条件，其实这样是不对的。举个简单的反例，比如此时有两个粟子A和B，小松鼠到粟子A的距离为2，粟子A到树的距离为1，
小松鼠到粟子B的距离为2，粟子B到树的距离为2。那么按照博主之前的选择方法，会选先去粟子A，因为小松鼠到粟子A再到树的距离之和为3，
小于先去粟子B再去树的距离之和(为4)。然而小松鼠先去粟子A的话，总距离就是7，而如果先去粟子B的话，总距离为6，这就说明之前的判定条件不对。
那么正确思路应该是，假设小松树最先应该去粟子i，那么我们假设粟子i到树的距离为x，小松鼠到粟子i的距离为y，那么如果小松鼠不去粟子i，累加步数就是2x，
如果小松鼠去粟子i，累加步数就是x+y，我们希望x+y尽可能的小于2x，那么就是y尽可能小于x，即x-y越大越好。这样我们遍历每个粟子，找出x-y最大的那个，
让小松鼠先去捡就好了。话说萌萌的小松鼠真是很可爱，希望这些小萌物们远离马路，不要随便过马路，真是太危险了。。。

 
class Solution {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int sum = 0;
        int maxDiff = Integer.MIN_VALUE;
        for (int[] nut : nuts) {
            int dist = Math.abs(nut[0] - tree[0]) + Math.abs(nut[1] - tree[1]);
            sum += 2 * dist;
            maxDiff = Math.max(maxDiff, dist - (Math.abs(nut[0] - squirrel[0]) + Math.abs(nut[1] - squirrel[1])));
        }
        return sum - maxDiff;
    }
}

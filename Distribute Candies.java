/*
题目真的很简单 理清思路
妹妹得到的糖果数为（总数/2），其中最多 （总数/2） 类
若原糖果总类数不足 （总数/2），那妹妹最多能得到的糖果类数，就是原糖果的总类数
*/
class Solution {
    public int distributeCandies(int[] candies) {
        List<Integer> list = Arrays.stream(candies).boxed().collect(Collectors.toList());
        Set<Integer> set = new HashSet<>(list);
        return (set.size() > candies.length / 2) ? candies.length / 2 : set.size();
    }
}

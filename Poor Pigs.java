/*
There are 1000 buckets, one and only one of them contains poison, the rest are filled with water. They all look the same. If a pig drinks that poison it will die within 15 minutes. What is the minimum amount of pigs you need to figure out which bucket contains the poison within one hour.

Answer this question, and write an algorithm for the follow-up general case.

Follow-up:

If there are n buckets and a pig drinking poison will die within m minutes, how many pigs (x) you need to figure out the "poison" bucket within p minutes? There is exact one bucket with poison.

分析：对于例子，1000桶水，死亡时间15mins，测试时间1小时。需要至少死几头猪能找到有毒的水桶呢？
对于每头猪，它应有5种状态：15min、30min、45min、60min死亡和活着。假设每个桶都有对应标签（0，1，2，3，4）对应5个状态。
假设有5桶水，那么只需一头猪就可以了，就可以判断那桶水有毒。
如果有25桶水呢？把（0~24）桶水按照5进制进行标签，分别对应（00，01，02，03，04，10，11，12，。。。，40，41，42，43，44）.这是只需2头猪即可。
在t=0时刻，第一个猪喝第一位为0的桶水，第2个猪喝下第2位为0的水，在t=15时刻，第一个猪喝第一位为1的桶水，第2个猪喝下第2位为1的水，依此类推，
我们可以通过猪的死亡判断有毒的水。
对于n桶水，已知基的情况下，b^x>=n即可，我们要找到x.
对于例题，b=4+1=5；故x=log5(1000)=5
*/
class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets == 1) {
            return 0;
        }
        int base = minutesToTest / minutesToDie + 1;
        int result = 1;

        for (int i = 1; ; i++) {
            result = result * base;
            if (result >= buckets) {
                return i;
            }
        }
    }
}

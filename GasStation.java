/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
*/
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
            return 0;
        }
        
        int[] combinedArr = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            combinedArr[i] = gas[i] - cost[i];
        }
        int curIndex = 0;
        boolean hasPassOneCircle = false;
        while (true) {
            int curSum = 0;
            int forwardIndex = curIndex;
            while (curSum >= 0) {
                curSum += combinedArr[forwardIndex];
                forwardIndex++;
                if (forwardIndex == gas.length) {
                    forwardIndex = 0;
                    hasPassOneCircle = true;
                }
                if (curIndex == forwardIndex && curSum >= 0) {
                    return curIndex;
                }
            }
            if (hasPassOneCircle == true) {
                return -1;
            }
            curIndex = forwardIndex;
        }
    }
}

/*
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.
*/
public class Solution {
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return Integer.MIN_VALUE;
        }
        Queue<Long> twoList = new LinkedList<>();
        Queue<Long> threeList = new LinkedList<>();
        Queue<Long> fiveList = new LinkedList<>();
        Long result = 1L;
        twoList.add(2L);
        threeList.add(3L);
        fiveList.add(5L);

        while (n > 1) {
            Long twoMin = twoList.peek();
            Long threeMin = threeList.peek();
            Long fiveMin = fiveList.peek();

            if (twoMin <= threeMin && twoMin <= fiveMin) {
                result = twoList.remove();
                twoList.add(twoMin * 2);
                threeList.add(twoMin * 3);
                fiveList.add(twoMin * 5);
            }
            else if (threeMin <= twoMin && threeMin <= fiveMin) {
                result = threeList.remove();
                threeList.add(threeMin * 3);
                fiveList.add(threeMin * 5);
            }
            else if (fiveMin <= twoMin && fiveMin <= threeMin) {
                result = fiveList.remove();
                fiveList.add(fiveMin * 5);
            }
            n--;
        }
        return result.intValue();
    }
}

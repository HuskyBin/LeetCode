/*
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false

*/
import java.util.*;

public class TwoSumIII{
    public static void main(String[] args) {

    }

    private Map<Integer, Boolean> map;
    private Map<Integer, Integer> mapCount;

    public TwoSumIII() {
        map = new HashMap<>();
        mapCount = new HashMap<>();
    }

    public void add(int num) {
        if (map.containsKey(num)) {
            mapCount.put(num, mapCount.get(num) + 1);
            return;
        }
        mapCount.put(num, 1);
        map.put(num, true);
        return;
    }

    public boolean find(int num) {
        for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
            int key = entry.getKey();
            if (map.containsKey(num - key) && (num - key) != key) {
                return true;
            }
            else if ((num - key) == key && map.containsKey(num - key) && mapCount.get(key) >= 2) {
                return true;
            }
        }
        return false;
    }
}

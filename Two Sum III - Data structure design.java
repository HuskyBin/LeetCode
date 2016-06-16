/*
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
*/
public class TwoSum {

    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> list = new LinkedList<>();
    // Add the number to an internal data structure.
	public void add(int number) {
	    if (map.containsKey(number)) {
	        map.put(number, map.get(number) + 1);
	    }
	    else {
	        map.put(number, 1);
	    }
	    list.add(number);
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for (Integer i : list) {
	        if (value - i == i) {
	            if (map.get(i) > 1) {
	                return true;
	            }
	        }
	        else {
	            if (map.containsKey(value - i)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
}

/*
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
*/
public class RandomizedCollection {

    public List<Integer> list;
    public int length;
    public Map<Integer, List<Integer>> map;

    /** Initialize your data structure here. */

    public RandomizedCollection() {
        list = new ArrayList<>();
        length = 0;
        map = new HashMap<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean result = true;
        if (map.containsKey(val) && map.get(val).size() > 0) {
            result = false;
            List<Integer> curList = map.get(val);
            curList.add(length);
            map.put(val, curList);
        }
        else {
            List<Integer> newList = new ArrayList<>();
            newList.add(length);
            map.put(val, newList);
        }
        if (length == list.size()) {
            list.add(val);
        }
        else {
            list.set(length, val);
        }
        length++;
        return result;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val) || (map.get(val).size() == 0)) {
            return false;
        }
        List<Integer> curList = map.get(val);
        int index = curList.remove(curList.size() - 1);
        map.put(val, curList);
        int lastElement = list.get(length - 1);
        list.set(index, lastElement);
        List<Integer> lastEleIndex = map.get(lastElement);
        for(int i = 0; i < lastEleIndex.size(); i++) {
            if (lastEleIndex.get(i) == (length - 1)) {
                lastEleIndex.remove(i);
                lastEleIndex.add(index);
                break;
            }
        }
        length--;
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        Random random = new Random();
        int nextInt = random.nextInt(length);
        return list.get(nextInt);
    }
}

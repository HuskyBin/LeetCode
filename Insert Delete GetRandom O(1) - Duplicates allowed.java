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
class RandomizedCollection {
    
    Map<Integer, List<Integer>> map = new HashMap<>();
    List<Integer> list = new ArrayList<>();


    /** Initialize your data structure here. */
    public RandomizedCollection() {
        
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean result = map.containsKey(val) ? false : true;
        map.computeIfAbsent(val, k -> new ArrayList<>()).add(list.size());
        list.add(val);
        return result;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        List<Integer> indexList = map.get(val);
        int removeIndex = indexList.get(indexList.size() - 1);
        indexList.remove(indexList.size() - 1);
        int lastVal = list.get(list.size() - 1);
 
        list.set(removeIndex, lastVal);
            
        List<Integer> lastValIndex = map.get(lastVal);
        for (int i = 0; i < lastValIndex.size(); i++) {
            if (lastValIndex.get(i) == (list.size() - 1)) {
                lastValIndex.remove(i);
                lastValIndex.add(removeIndex);
                break;
            }
        }
        if (indexList.size() == 0) {
            map.remove(val);
        }
        list.remove(list.size() - 1);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}

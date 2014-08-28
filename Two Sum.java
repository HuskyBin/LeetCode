/*
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        if (numbers == null || numbers.length == 0) {
            return result;
        }
        ArrayList<objectWithIndex> objectArr = new ArrayList<>(numbers.length);
        for (int i = 0; i < numbers.length; i++) {
            objectWithIndex newObject = new objectWithIndex(numbers[i], i);
            objectArr.add(newObject);
        }
        Collections.sort(objectArr, new Comparator<objectWithIndex>() {
            public int compare(objectWithIndex obj1, objectWithIndex obj2) {
                if (obj1.value < obj2.value) {
                    return -1;
                }
                else if (obj1.value > obj2.value) {
                    return 1;
                }
                else {
                    return Integer.compare(obj1.index, obj2.index);
                }
            }
        });
        findMatchIndex(objectArr, result, target);
        return result;
    }
    
    private void findMatchIndex(List<objectWithIndex> objectArr, int[] result, int target) {
        int startIndex = 0;
        int endIndex = objectArr.size() - 1;
        while (startIndex < endIndex) {
            objectWithIndex startObj = objectArr.get(startIndex);
            objectWithIndex endObj = objectArr.get(endIndex);
            if (startObj.value + endObj.value == target) {
                result[0] = Math.min(startObj.index, endObj.index) + 1;
                result[1] = Math.max(startObj.index, endObj.index) + 1;
                break;
            }
            else if (startObj.value + endObj.value < target) {
                startIndex++;
            }
            else {
                endIndex--;
            }
        }
    }
}

class objectWithIndex {
    public int index;
    public int value;
    
    public objectWithIndex(int value, int index) {
        this.value = value;
        this.index = index;
    }
}

/*
Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
*/
public class ZigzagIterator {

    public int nextIndex;
    public List<Integer> v1;
    public List<Integer> v2;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        nextIndex = 1;
        this.v1 = v1;
        this.v2 = v2;
    }

    public int next() {
        int result = 0;
        if (nextIndex == 0) {
            result = v1.get(0);
            v1.remove(0);
        }
        else {
            result = v2.get(0);
            v2.remove(0);
        }
        return result;
    }

    public boolean hasNext() {
        if (v1.size() > 0 && v2.size() > 0) {
            if (nextIndex == 0) {
                nextIndex = 1;
            }
            else {
                nextIndex = 0;
            }
        }
        else if (v1.size() > 0) {
            nextIndex = 0;
        }
        else if (v2.size() > 0) {
            nextIndex = 1;
        }
        else {
            return false;
        }
        return true;
    }
}

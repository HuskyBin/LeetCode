/*
Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Hint:

How many variables do you need to keep track?
Two variables is all you need. Try with x and y.
Beware of empty rows. It could be the first few rows.
To write correct code, think about the invariant to maintain. What is it?
The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
Not sure? Think about how you would implement hasNext(). Which is more complex?
Common logic in two different places should be refactored into a common method.
Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.
*/
public class Vector2D implements Iterator<Integer> {

    public int firstIndex;
    public int nestIndex;
    public List<List<Integer>> vec2d;
    public Vector2D(List<List<Integer>> vec2d) {
        this.vec2d = vec2d;
        this.firstIndex = 0;
        this.nestIndex = -1;
    }

    @Override
    public Integer next() {
        return vec2d.get(firstIndex).get(nestIndex);
    }

    @Override
    public boolean hasNext() {
        if (firstIndex >= vec2d.size()) {
            return false;
        }
        while (firstIndex < vec2d.size() && vec2d.get(firstIndex).size() == 0) {
            firstIndex++;
        }
        if (firstIndex == vec2d.size()) return false;
        if ( (vec2d.get(firstIndex).size() - 1 == nestIndex)) {
           while (firstIndex < vec2d.size() - 1 && vec2d.get(firstIndex + 1).size() == 0) {
              firstIndex++;
              nestIndex = -1;
           }
            if (firstIndex == vec2d.size() - 1 && vec2d.get(firstIndex).size() == 0) {
               return false;
           }    
        }
        if (firstIndex == vec2d.size() - 1 && vec2d.get(firstIndex).size() - 1 == nestIndex) {
            return false;
        }
        if (vec2d.get(firstIndex).size() - 1 == nestIndex) {
            firstIndex++;
            nestIndex = 0;
        }
        else {
            nestIndex++;
        }
        return true;
    }
}

// clean code

public class Vector2D implements Iterator<Integer> {

    public int firstIndex;
    public int nestIndex;
    public List<List<Integer>> vec2d;
    public Vector2D(List<List<Integer>> vec2d) {
        this.vec2d = vec2d;
        this.firstIndex = 0;
        this.nestIndex = 0;
    }

    @Override
    public Integer next() {
        return vec2d.get(firstIndex).get(nestIndex++);
    }

    @Override
    public boolean hasNext() {
        while (firstIndex < vec2d.size() && nestIndex >= vec2d.get(firstIndex).size()) {
            firstIndex++;
            nestIndex = 0;
        }
        return firstIndex < vec2d.size() && nestIndex < vec2d.get(firstIndex).size();
    }
}

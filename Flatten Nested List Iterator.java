/*
Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

Subscribe to see which companies asked this question
*/
public class NestedIterator implements Iterator<Integer> {

    private Stack<NestedInteger> stack = new Stack<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        if (stack.size() == 0) {
            return null;
        }
        NestedInteger top = stack.pop();
        return top.getInteger();
    }

    @Override
    public boolean hasNext() {
        if (stack.size() == 0) {
            return false;
        }
        
        NestedInteger top = stack.peek();
        if (top.isInteger() == true) {
            return true;
        }
        while (top != null && top.isInteger() != true) {
            stack.pop();
            List<NestedInteger> nextLevel = top.getList();
            for (int i = nextLevel.size() - 1; i >= 0; i--) {
                stack.push(nextLevel.get(i));
            }
            if (stack.size() == 0) {
                top = null;
            }
            else {
                top = stack.peek();
            }
        }
        return stack.size() > 0;
    }
}

// Second Solution Queue
public class NestedIterator implements Iterator<Integer> {

    private Queue<Integer> queue = new LinkedList<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        inQueue(nestedList);
    }
    
    private void inQueue(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            return;
        }
        for (int i = 0; i < nestedList.size(); i++) {
            NestedInteger curNestedInteger = nestedList.get(i);
            if (curNestedInteger.isInteger()) {
                queue.add(curNestedInteger.getInteger());
            }
            else {
                inQueue(curNestedInteger.getList());
            }
        }
    }

    @Override
    public Integer next() {
        if (queue.size() == 0) return null;
        return queue.remove();
    }

    @Override
    public boolean hasNext() {
        return queue.size() > 0;
    }
}

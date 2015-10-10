/*
Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
*/
class MyQueue {
    
    private Stack<Integer> addStack;
    private Stack<Integer> popStack;
    
    public MyQueue() {
        addStack = new Stack<>();
        popStack = new Stack<>();
    }
    
    // Push element x to the back of queue.
    public void push(int x) {
        addStack.add(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (popStack.size() > 0) {
            popStack.pop();
        }
        else {
            while (addStack.size() > 1) {
                popStack.add(addStack.pop());
            }
            addStack.pop();
        }
    }

    // Get the front element.
    public int peek() {
        if (popStack.size() > 0) return popStack.peek();
        while (addStack.size() > 0) {
            popStack.add(addStack.pop());
        }
        return popStack.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return (addStack.size() > 0 || popStack.size() > 0) ? false : true;
    }
}

/*
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Notes:
You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
Update (2015-06-11):
The class name of the Java function had been updated to MyStack instead of Stack.
*/
class MyStack {
    
    private Queue<Integer> firstQueue;
    private Queue<Integer> secondQueue;
    
    public MyStack() {
        firstQueue = new LinkedList<>();
        secondQueue = new LinkedList<>();
    }
    
    // Push element x onto stack.
    public void push(int x) {
        if (firstQueue.isEmpty()) {
            secondQueue.add(x);
        }
        else {
            firstQueue.add(x);
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        Queue<Integer> fullQueue = firstQueue.isEmpty() ? secondQueue : firstQueue;
        Queue<Integer> emptyQueue = firstQueue.isEmpty() ? firstQueue : secondQueue;
        
        while (fullQueue.size() > 1) {
            emptyQueue.add(fullQueue.remove());
        }
        fullQueue.remove();
    }

    // Get the top element.
    public int top() {
        Queue<Integer> fullQueue = firstQueue.isEmpty() ? secondQueue : firstQueue;
        Queue<Integer> emptyQueue = firstQueue.isEmpty() ? firstQueue : secondQueue;
        
        while (fullQueue.size() > 1) {
            emptyQueue.add(fullQueue.remove());
        }
        
        int result = fullQueue.remove();
        emptyQueue.add(result);
        return result;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return (firstQueue.isEmpty() && secondQueue.isEmpty()) ? true : false;
    }
}

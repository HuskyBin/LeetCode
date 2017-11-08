
/*
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.

*/
class MaxStack {

    /** initialize your data structure here. */
    private Stack<Integer> stack = new Stack<>();
    
    private Stack<Integer> maxStack = new Stack<>();
        
    public MaxStack() {
    }
    
    public void push(int x) {
        stack.push(x);
        pushToMaxStack(maxStack, x);
    }
    
    private void pushToMaxStack(Stack<Integer> maxStack, int val) {
        Stack<Integer> tempStack = new Stack<>();
        while (!maxStack.isEmpty() && maxStack.peek() > val) {
            tempStack.push(maxStack.pop());
        }
        maxStack.push(val);
        while (!tempStack.isEmpty()) {
            maxStack.push(tempStack.pop());
        }
    }
    
    public int pop() {
        int val = stack.pop();
        popVal(maxStack, val);
        return val;
    }
    
    private void popVal(Stack<Integer> stack, int val) {
        Stack<Integer> tempStack = new Stack<>();
        while (stack.peek() != val) {
            tempStack.push(stack.pop());
        }
        stack.pop();
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        int val = maxStack.pop();
        popVal(stack, val);
        return val;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */

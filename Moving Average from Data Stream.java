/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/
public class MovingAverage {

    /** Initialize your data structure here. */
    public int size = 0;
    public double sum = 0;
    public Queue<Integer> queue = null;
    public MovingAverage(int size) {
        this.size = size;
        queue = new LinkedList<Integer>();
    }
    
    public double next(int val) {
        if (queue.size() < size) {
            queue.add(val);
            sum += val;
        }
        else {
            sum -= queue.poll();
            queue.add(val);
            sum += val;
        }
        return sum / queue.size();
    }
}

/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2
*/
class MedianFinder {
    
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10, Collections.reverseOrder());
    
    // Adds a number into the data structure.
    public void addNum(int num) {
        if (maxHeap.size() == 0) {
            maxHeap.add(num);
            return;
        }
        int maxTop = maxHeap.peek();
        if (minHeap.size() == 0) {
            if (maxTop >= num) {
                minHeap.add(maxTop);
                maxHeap.poll();
                maxHeap.add(num);
            }
            else {
                minHeap.add(num);
            }
            return;
        }
        
        int minTop = minHeap.peek();
        
        if (num <= maxTop) {
            maxHeap.add(num);
        }
        else {
            minHeap.add(num);
        }
        
        if (minHeap.size() > maxHeap.size()) {
            int temp = minHeap.peek();
            minHeap.poll();
            maxHeap.add(temp);
        }
        else if (maxHeap.size() > minHeap.size() + 1) {
            int temp = maxHeap.peek();
            maxHeap.poll();
            minHeap.add(temp);
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (minHeap.size() == 0) {
            return maxHeap.peek();
        }
        if ((minHeap.size() + maxHeap.size()) % 2 == 0) {
            int minTop = minHeap.peek();
            int maxTop = maxHeap.peek();
            
            return (minTop + maxTop) / 2.0;
        }
        else {
            return (double)maxHeap.peek();
        }
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();

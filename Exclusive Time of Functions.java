/*
Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.

Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.

A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0 starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.

Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.

Example 1:
Input:
n = 2
logs = 
["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
Output:[3, 4]
Explanation:
Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1. 
Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time. 
So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
Note:
Input logs will be sorted by timestamp, NOT log id.
Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time of function 0.
Two functions won't start or end at the same time.
Functions could be called recursively, and will always end.
1 <= n <= 100
*/
// 遇到start， 先加1， 因为这1秒就是属于这个id的，然后看stack 顶端的id， 在加上没计算的时间，
／／ 遇到 end， 给栈顶端的id 加 time - preTime， 因为start 的那个1 已经加过了，所以不用再加
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        if (n <= 0 || logs == null || logs.size() == 0) {
            return null;
        }
        int[] count = new int[n];
        int preTime = -1;
        boolean preStart = false;
        Stack<Integer> stack = new Stack<>();
        for (String log : logs) {
            String[] str = log.split(":");
            int id = Integer.valueOf(str[0]);
            boolean isStart = str[1].equals("start");
            int time = Integer.valueOf(str[2]);
            if (isStart) {
                count[id] += 1;
                if (!stack.isEmpty()) {
                    count[stack.peek()] += time - preTime - 1;
                }
                stack.push(id);
            }
            else {
                count[stack.peek()] += time - preTime;
                stack.pop();
            }
            preTime = time;
        }
        return count;
    }
}

// better understand 
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        if (logs == null || n < 0 || logs.size() == 0) {
            return new int[0];
        }   
        int[] ids = new int[n];
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> startStack = new Stack<>();
        for (String log : logs) {
            String[] strs = log.split(":");
            if (strs[1].equals("start")) {
                stack.push(Integer.valueOf(strs[0]));
                startStack.push(Integer.valueOf(strs[2]));
            } else {
                int topId = stack.pop();
                int startTime = startStack.pop();
                int time = Integer.valueOf(strs[2]) - startTime + 1;
                ids[topId] += time;
                if (!stack.isEmpty()) {
                    ids[stack.peek()] -= time;
                }
            }
        }
        return ids;
    }
}

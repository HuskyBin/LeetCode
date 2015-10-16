/*
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]
*/
public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> resultList = new ArrayList<>();
        if (input == null) {
            return resultList;
        }
        
        for (int i = 0; i < input.length(); i++) {
            char curChar = input.charAt(i);
            if (curChar != '+' && curChar != '-' && curChar != '*') {
                continue;
            }
            
            List<Integer> partOne = diffWaysToCompute(input.substring(0, i));
            List<Integer> partTwo = diffWaysToCompute(input.substring(i + 1, input.length()));
            
            for (Integer numOne : partOne) {
                for (Integer numTwo : partTwo) {
                    if (curChar == '+') resultList.add(numOne + numTwo);
                    if (curChar == '-') resultList.add(numOne - numTwo);
                    if (curChar == '*') resultList.add(numOne * numTwo);
                }
            }
        }
        if (resultList.size() == 0) {
            resultList.add(Integer.valueOf(input));
        }
        return resultList;
    }
}



// Python concise solution:
class Solution:
    # @param {string} input
    # @return {integer[]}
    def diffWaysToCompute(self, input):
        return [a+b if c == '+' else a-b if c == '-' else a*b
            for i, c in enumerate(input) if c in '+-*'
            for a in self.diffWaysToCompute(input[:i])
            for b in self.diffWaysToCompute(input[i+1:])] or [int(input)]

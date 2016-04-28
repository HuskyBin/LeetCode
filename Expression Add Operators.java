/*
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
*/
public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> resultList = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return resultList;
        }
        addOperationCore(num, target, 0, 0, "", resultList);
        return resultList;
    }
    
    private void addOperationCore(String num, 
                              long target, long current, long lastOp, String expression, List<String> resultList) {
        if (num.length() == 0 && target == current) {
            resultList.add(expression);
            return;
        }
        
        for (int i = 0; i < num.length(); i++) {
            String subString = num.substring(0, i + 1);
            if (subString.length() > 1 && subString.charAt(0) == '0') {
                continue;
            }
            String rest = num.substring(i + 1);
            Long curValue = Long.valueOf(subString);
            if (expression.length() == 0) {
                addOperationCore(rest, target, curValue, curValue, subString, resultList);
            }
            else {
                addOperationCore(rest, target, curValue + current, curValue, expression + "+" + subString, resultList);
                addOperationCore(rest, target, current - curValue, -curValue, expression + "-" + subString, resultList);
                addOperationCore(rest, target, current - lastOp + lastOp * curValue, curValue * lastOp, expression + "*" + subString, resultList);
            }
        }
        
    }
}

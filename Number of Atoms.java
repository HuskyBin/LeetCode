/*
Given a chemical formula (given as a string), return the count of each atom.

An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.

Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.

A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.

Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

Example 1:
Input: 
formula = "H2O"
Output: "H2O"
Explanation: 
The count of elements are {'H': 2, 'O': 1}.
Example 2:
Input: 
formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: 
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
Example 3:
Input: 
formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: 
The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
Note:

All atom names consist of lowercase letters, except for the first character which is uppercase.
The length of formula will be in the range [1, 1000].
formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.
*/

class Solution {
    public String countOfAtoms(String formula) {
        if (formula == null || formula.length() == 0) {
            return formula;
        }
        Map<String, Integer> map = countCore(formula);

        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key);
            String value = map.get(key) > 1 ? String.valueOf(map.get(key)) : "";
            sb.append(value);
        }
        return sb.toString();
    }
    
    private Map<String, Integer> countCore(String formula) {
        Map<String, Integer> map = new HashMap<>();
        if (formula.isEmpty()) {
            return map;
        }
        int index = 0;
        while (index < formula.length()) {
            char curChar = formula.charAt(index);
            if (curChar == '(') {
                int count = 0;
                int fastIndex = index;
                for (; fastIndex < formula.length(); fastIndex++) {
                    if (formula.charAt(fastIndex) == '(') {
                        count++;
                    } else if (formula.charAt(fastIndex) == ')') {
                        count--;
                    }
                    if (count == 0) break;
                }
                String substring = formula.substring(index + 1, fastIndex);
                Map<String, Integer> subMap = countCore(substring);
                fastIndex++;
                int k = fastIndex;
                int number = 1;
                while (k < formula.length() && Character.isDigit(formula.charAt(k))) {
                    k++;
                }
                if (k > fastIndex) {
                    number = Integer.valueOf(formula.substring(fastIndex, k));
                }
                
                for (String key : subMap.keySet()) {
                    map.put(key, subMap.get(key) * number + map.getOrDefault(key, 0));
                }
                index = k;
            }
            else {
                int fastIndex = index + 1;
                while (fastIndex < formula.length() && Character.isLowerCase(formula.charAt(fastIndex))) {
                    fastIndex++;
                }
                int number = 1;
                int k = fastIndex;
                while (k < formula.length() && Character.isDigit(formula.charAt(k))) {
                    k++;
                }
                if (k > fastIndex) {
                    number = Integer.valueOf(formula.substring(fastIndex, k));
                }
                map.put(formula.substring(index, fastIndex), map.getOrDefault(formula.substring(index, fastIndex), 0) + number);
                index = k;
            }
        }
        return map;
    }
}

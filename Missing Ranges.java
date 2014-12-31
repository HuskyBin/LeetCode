/*
Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*/
import java.util.*;
public class MissingRanges {
    public static void main(String[] args) {
        MissingRanges obj = new MissingRanges();
        int[] A = {1,7,7,8,10,20, 30};
        List<String> resultList = obj.findMissingRanges(A, 0, 31);
        for (String s : resultList) {
            System.out.println(s);
        }

    }

    public List<String> findMissingRanges(int[] A, int lower, int upper) {
        List<String> resultList = new ArrayList<>();
        if (A == null || A.length == 0 || upper < lower) {
            return resultList;
        }
        StringBuilder sb = new StringBuilder();
        appendLower(lower, A[0], resultList, sb);
        int last = A[0];
        for (int i = 1; i < A.length; i++) {
            int num = A[i];
            appendNumber(last, num, resultList, sb);
            last = num;
        }
        appendUpper(A[A.length - 1], upper, resultList, sb);
        return resultList;
    }

    private void appendLower(int last, int num, List<String> resultList, StringBuilder sb) {
        if (last + 1 == num) {
            sb.append(last);
            resultList.add(sb.toString());
            sb.setLength(0);
        }
        else if (last + 1 < num) {
            sb.append(last);
            sb.append("->");
            sb.append(num - 1);
            resultList.add(sb.toString());
            sb.setLength(0);
        }
    }

     private void appendUpper(int last, int num, List<String> resultList, StringBuilder sb) {
        if (last + 1 == num) {
            sb.append(num);
            resultList.add(sb.toString());
            sb.setLength(0);
        }
        else if (last + 1 < num) {
            sb.append(last + 1);
            sb.append("->");
            sb.append(num);
            resultList.add(sb.toString());
            sb.setLength(0);
        }
    }



    private void appendNumber(int last, int num, List<String> resultList, StringBuilder sb) {
        if (last + 2 == num) {
            sb.append(last + 1);
            resultList.add(sb.toString());
            sb.setLength(0);
        }
        else if (last + 2 < num) {
            sb.append(last + 1);
            sb.append("->");
            sb.append(num - 1);
            resultList.add(sb.toString());
            sb.setLength(0);
        }
    }
}

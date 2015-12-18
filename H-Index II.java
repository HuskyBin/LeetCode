/*
H-Index II My Submissions Question
Total Accepted: 14026 Total Submissions: 44181 Difficulty: Medium
Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
*/
public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int start = 0;
        int end = citations.length - 1;
        
        while (start + 1 < end) {
            int middle = start + (end - start) / 2;
            if (citations.length - 1 - middle >= citations[middle] && citations.length - middle - 2 < citations[middle + 1]) {
                return citations.length - 1 - middle;
            }
            else if (citations.length - 1 - middle >= citations[middle]) {
                start = middle + 1;
            }
            else {
                end = middle - 1;
            }
        }
        while (end >= start) {
            if (citations.length - 1 - end >= citations[end]) {
                return citations.length - 1 - end;
            }
            end--;
        }
        return citations.length;
    }
}

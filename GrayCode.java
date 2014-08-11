public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> resultList = new ArrayList<>();
        if (n < 0) {
            return resultList;
        }
        int num = 0;
        resultList.add(num);
        if (n == 0) {
            return resultList;
        }
        createAllGrayCode(resultList, n, num, true);
        return resultList;
    }
    
    public void createAllGrayCode(List<Integer> resultList, int n, int number, boolean changeLastBit) {
        while (true) {
            int curInt = getNextGrayCode(number, n, changeLastBit);
            if (curInt == -1) {
                break;
            }
            resultList.add(curInt);
            number = curInt;
            if (changeLastBit == true) {
                changeLastBit = false;
            }
            else {
                changeLastBit = true;
            }
        }
    }
    
    public int getNextGrayCode(int number, int n, boolean changeLastBit) {
        if (changeLastBit == true) {
            number ^= 1;
            return number;
        }
        else {
           int lastOneIndex = getLastOneIndex(number, n);
           if (lastOneIndex >= n - 1) {
               return -1;
           }
           number ^= (1 << (lastOneIndex + 1));
           return number;
        }
    }
    
    public int getLastOneIndex(int number, int n) {
        for (int i = 0; i <= n - 1; i++) {
            if ((number & (1 << i)) != 0) {
                return i;
            } 
        }
        return n;
    }
}




//Version Two
public class Solution {
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (n <= 1) {
            for (int i = 0; i <= n; i++){
                result.add(i);
            }
            return result;
        }
        result = grayCode(n - 1);
        ArrayList<Integer> r1 = reverse(result);
        int x = 1 << (n-1);
        for (int i = 0; i < r1.size(); i++) {
            r1.set(i, r1.get(i) + x);
        }
        result.addAll(r1);
        return result;
    }
    
    public ArrayList<Integer> reverse (ArrayList<Integer> r) {
        ArrayList<Integer> rev = new ArrayList<Integer>();
        for (int i = r.size() - 1; i >= 0; i--) {
            rev.add(r.get(i));
        }
        return rev;
    }
}

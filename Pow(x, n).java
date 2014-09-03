/*
Implement pow(x, n).
*/
public class Solution {
    public double pow(double x, int n) {
        if (x == 0.0) {
            return 0;
        }
        boolean isNegative = false;
        if (n < 0) {
            n = -n;
            isNegative = true;
        }
        double result = powCore(x, n);
        
        if (isNegative == true) {
            return 1.0 / result;
        }
        return result;
    }
    
    private double powCore(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        if (n == 1) {
            return x;
        }
        double subResult = 0.0;
        if (n % 2 == 1) {
            subResult = powCore(x, n - 1) * x;
        }
        else {
            subResult = powCore(x, n / 2);
            subResult *= subResult;
        }
        return subResult;
    }
}

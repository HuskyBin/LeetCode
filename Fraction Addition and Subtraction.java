/*
Given a string representing an expression of fraction addition and subtraction, you need to return the calculation result in string format. The final result should be irreducible fraction. If your final result is an integer, say 2, you need to change it to the format of fraction that has denominator 1. So in this case, 2 should be converted to 2/1.

Example 1:
Input:"-1/2+1/2"
Output: "0/1"
Example 2:
Input:"-1/2+1/2+1/3"
Output: "1/3"
Example 3:
Input:"1/3-1/2"
Output: "-1/6"
Example 4:
Input:"5/3+1/3"
Output: "2/1"
Note:
The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
Each fraction (input and output) has format ±numerator/denominator. If the first input fraction or the output is positive, then '+' will be omitted.
The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will always be in the range [1,10]. If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.
The number of given fractions will be in the range [1,10].
The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.
*/
/*
辗转相除法是用来计算两个整数的最大公约数。假设两个整数为a和b，他们的公约数可以表示为gcd(a,b)。如果gcd(a,b) = c,则必然a = mc和b = nc。a除以b得商和余数，余数r可以表示为r = a - bk，k这里是系数。因为c为 a和b的最大公约数，所以c也一定是r的最大公约数，因为r = mc - nck = (m-nk)c。

因此gcd(a,b) = gcd(b,r)，相当于把较大的一个整数用一个较小的余数替换了，这样不断地迭代，直到余数为0，则找到最大公约数。

举例两个整数为1071和462：
第一步：1071 / 462 = 2 * 462 + 147
第二步：462 / 147 = 3 * 147 + 21
第三步：147 / 21 = 7 * 21 + 0

作者：NatureCurly
链接：http://www.jianshu.com/p/7876eb2dff89
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/
class Solution {
    public String fractionAddition(String expression) {
        String[] fracs = expression.split("(?=[-+])");
        String res = "0/1";
        for (String frac : fracs) {
            res = add(res, frac);
        }
        return res;
    }
    
    private String add(String frac1, String frac2) {
        int[] f1 = Stream.of(frac1.split("/")).mapToInt(Integer::parseInt).toArray();
        int[] f2 = Stream.of(frac2.split("/")).mapToInt(Integer::parseInt).toArray();
        int number = f1[0] * f2[1] + f1[1] * f2[0];
        int denom = f1[1] * f2[1];
        String sign = (number < 0) ? "-" : "";
        if (number < 0) {
            number *= -1;
        }
        int gcdNumber = gcd(number, denom);
        return sign + number / gcdNumber + "/" + denom / gcdNumber;
    }

    private int gcd(int x, int y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}

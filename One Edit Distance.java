/*
Given two strings S and T, determine if they are both one edit distance apart.
*/
public class OneEdit  {
    public static void main(String[] args) {
        OneEdit obj = new OneEdit();
        String s = "abc";
        String t = "tc";
        boolean result = obj.isOneEdit(s, t);
        System.out.println(result);
    }

    public boolean isOneEdit(String s, String t) {
        if (s == null || t == null || Math.abs(s.length() - t.length()) > 2) {
            return false;
        }

        return isOneEditHelper(s, t, 0, 0, 0);
    }

    private boolean isOneEditHelper(String s, String t, int sIndex, int tIndex, int distance) {
        if (sIndex == s.length() && tIndex == t.length()) {
            if (distance == 1) {
                return true;
            }
            return false;
        }
        if (distance >= 2) {
            return false;
        }
        if (sIndex == s.length() && tIndex == t.length() - 1) {
            if (distance == 0) {
                return true;
            }
            return false;
        }
        if (tIndex == t.length() && sIndex == s.length() - 1) {
            if (distance == 0) {
                return true;
            }
            return false;
        }
        if (tIndex >= t.length() || sIndex >= s.length()) {
            return false;
        }
        boolean result = true;
        if (s.charAt(sIndex) != t.charAt(tIndex)) {
            result = isOneEditHelper(s, t, sIndex + 1, tIndex, distance + 1);
            if (result == true) {
                return result;
            }
            result = isOneEditHelper(s, t, sIndex, tIndex + 1, distance + 1);
            if (result == true) {
                return result;
            }
            return isOneEditHelper(s, t, sIndex + 1, tIndex + 1, distance + 1);
        }
        return isOneEditHelper(s, t, sIndex + 1, tIndex + 1, distance);
    }
}

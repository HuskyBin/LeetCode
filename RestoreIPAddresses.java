public class Solution {
    
    public List<String> restoreIpAddresses(String s) {
        Set<String> resultList = new HashSet<>();
        if (s == null || s.length() == 0) {
            List<String> result = new ArrayList<>(resultList);
            return result;
        }
        StringBuilder sb = new StringBuilder();
        restoreIpAddressCore(s, resultList, sb, 0, 0);
        List<String> result = new ArrayList<>(resultList);
        return result;
    }
    
    public void restoreIpAddressCore(String s, 
                                     Set<String> resultList, 
                                     StringBuilder sb, 
                                     int index, 
                                     int num) {
        if (index == s.length() && num == 4) {
            String copyString = sb.toString();
            resultList.add(copyString.substring(0, copyString.length() - 1));
            return;
        }
        if (index == s.length() || num == 4) {
            return;
        }
        sb.append(s.charAt(index));
        sb.append('.');
        restoreIpAddressCore(s, resultList, sb, index + 1, num + 1);
        sb.setLength(sb.length() - 2);
        if (index < s.length() - 1 && s.charAt(index) != '0') {
            sb.append(s.charAt(index));           
            sb.append(s.charAt(index + 1));
            sb.append('.');
            restoreIpAddressCore(s, resultList, sb, index + 2, num + 1);
            sb.setLength(sb.length() - 3);
        }
        if (hasThreeValidNum(s, index)) {
            String validStr = getValidStr(s, index);
            sb.append(validStr);
            sb.append('.');
            restoreIpAddressCore(s, resultList, sb, index + 3, num + 1);
            sb.setLength(sb.length() - validStr.length() - 1);
        }
    }
    
    public String getValidStr(String s, int index) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(index));
        sb.append(s.charAt(index + 1));
        sb.append(s.charAt(index + 2));
        return sb.toString();
    }
    
    public boolean hasThreeValidNum(String s, int index) {
        if (index >= s.length() - 2) {
            return false;
        }
        if (s.charAt(index) == '0') {
            return false;
        }
        if (s.substring(index, index + 3).compareTo("255") <= 0) {
            return true;
        }
        return false;
    }
}

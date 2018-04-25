class Solution {
    public int[] shortestToChar(String S, char C) {
    	if (S == null) {
    		return new int[0];
    	}
        int[] result = new int[S.length()];
        int pre = 0 - S.length();
        int 
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == C) {
                pre = i;
            } else {
                result[i] = i - pre;
            }
        }
        pre = 2 * S.length();
        for (int i = S.length() - 1; i >= 0 ; i--) {
            if (S.charAt(i) == C) {
                pre = i;
            } else {
                result[i] = Math.min(result[i], pre - i);
            }
        }
        return result;
    	
    }

    
}

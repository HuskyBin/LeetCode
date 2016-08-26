   /*
    Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters from  all  the  magazines,  write  a  function  that  will  return  true  if  the  ransom   note  can  be  constructed  from  the  magazines ;  otherwise,  it  will  return  false.   

Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
   */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine == null || ransomNote == null || ransomNote.length() > magazine.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            if (map.containsKey(magazine.charAt(i))) {
                map.put(magazine.charAt(i), map.get(magazine.charAt(i)) + 1);
            }
            else {
                map.put(magazine.charAt(i), 1);
            }
        }
        
        for (int i = 0; i < ransomNote.length(); i++) {
            char ransomChar = ransomNote.charAt(i);
            if (!map.containsKey(ransomChar) || (map.get(ransomChar) == 0)) {
                return false;
            }
            map.put(ransomChar, map.get(ransomChar) - 1);
        }
        return true;
    }

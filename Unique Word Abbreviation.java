/*
An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> 
false

isUnique("cart") -> 
true

isUnique("cane") -> 
false

isUnique("make") -> 
true
*/
public class ValidWordAbbr {
    public Map<String, Set<String>> map;

    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for (String str : dictionary) {
            String abb = getAbb(str);
            if (!map.containsKey(abb)) {
                map.put(abb, new HashSet<String>());
            }
            map.get(abb).add(str);
        }
    }
    
    private String getAbb(String str) {
        if (str.length() < 3) {
            return str;
        }
        else {
            return str.substring(0, 1) + (str.length() - 2) + str.substring(str.length() - 1); 
        }
    }

    public boolean isUnique(String word) {
        String key = getAbb(word);
        if (!map.containsKey(key) || ((map.get(key).size() == 1) && map.get(key).contains(word))) {
            return true;
        }
        return false;
    }
}

/*
Implement a MapSum class with insert, and sum methods.

For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.

For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.

Example 1:
Input: insert("apple", 3), Output: Null
Input: sum("ap"), Output: 3
Input: insert("app", 2), Output: Null
Input: sum("ap"), Output: 5

*/
class MapSum {

   public Trie trie;

   public Map<String, Integer> map;
    
   public MapSum() {
       trie = new Trie();
       map = new HashMap<>();
    }
    
    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, delta);
        trie.insert(key, delta);
    }
    
    public int sum(String prefix) {
        return trie.findSum(prefix);
    
    }
    
    public static class Trie {

        public TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        private class TrieNode {
            public int value;
            public boolean isWord; 
            Map<Character, TrieNode> children;

            public TrieNode() {
                children = new HashMap<>();
            }
        }

        public void insert(String word, int val) {
            TrieNode pNode = root;

            for (char c : word.toCharArray()) {
                pNode.children.putIfAbsent(c, new TrieNode());
                pNode = pNode.children.get(c);
                pNode.value += val;
            }
            pNode.isWord = true;
        }

        public int findSum(String prefix) {
            TrieNode pNode = root;

            for (char c : prefix.toCharArray()) {
                pNode = pNode.children.get(c);
                if (pNode == null) {
                    return 0;
                }
            }
            return pNode.value;
        }   
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */

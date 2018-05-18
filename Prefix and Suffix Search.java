/*

Given many words, words[i] has weight i.

Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.

Examples:
Input:
WordFilter(["apple"])
WordFilter.f("a", "e") // returns 0
WordFilter.f("b", "") // returns -1
Note:
words has length in range [1, 15000].
For each test case, up to words.length queries WordFilter.f may be made.
words[i] has length in range [1, 10].
prefix, suffix have lengths in range [0, 10].
words[i] and prefix, suffix queries consist of lowercase letters only.
*/

class WordFilter {

    public Map<String, Integer> weightMap;
    
    Trie trie = new Trie();

    
    public WordFilter(String[] words) {
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i], i);
        }
        
    }
    

    
    public int f(String prefix, String suffix) {
        String word = suffix + "#" + prefix;
        return trie.findWords(word);
        
    }
}

class Trie {
    
    public TrieNode root;
    
    public Trie() {
        this.root = new TrieNode();
    }
    
    public void insert(String word, int weight) {
       List<String> reverSubStrs = generateReverSubStr(word);
        for (String str : reverSubStrs) {
            String newWord = str + "#" + word;
            TrieNode pNode = root;
            for (char c : newWord.toCharArray()) {
                pNode.children.putIfAbsent(c, new TrieNode());
                pNode = pNode.children.get(c);
                pNode.weight = weight;
            }
        }
    }
    
    private List<String> generateReverSubStr(String word) {
        List<String> resultList = new ArrayList<>();
        resultList.add("");
        for (int i = word.length() - 1; i >= 0; i--) {
            resultList.add(word.substring(i));
        }
        return resultList;
    }
    
    public int findWords(String word) {
        TrieNode pNode = root;
        for (char c : word.toCharArray()) {
            pNode = pNode.children.get(c);
            if (pNode == null) {
                return -1;
            }
        }
        return pNode.weight;
    }
    
    
    public static class TrieNode {
        public Map<Character, TrieNode> children;
        public int weight;
        
        public TrieNode() {
            children = new HashMap<>();
            weight = 0;
        }
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */

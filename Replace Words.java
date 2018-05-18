/*
In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

Example 1:
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
Note:
The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000
*/
class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        if (dict == null || sentence == null) {
            return "";
        }
        Trie trie = new Trie();
        for (String word : dict) {
            trie.insert(word);
        }
        String[] words = sentence.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(trie.find(word));
            sb.append(' ');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}


class Trie {
    
    
    public TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode pNode = root;
        for (char c : word.toCharArray()) {
            pNode.children.putIfAbsent(c, new TrieNode());
            pNode = pNode.children.get(c);
        }
        pNode.isRoot = true;
    }
    
    public String find(String word) {
        TrieNode pNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            pNode = pNode.children.get(c);
            if (pNode == null) {
                return word;
            }
            if (pNode.isRoot) {
                return word.substring(0, i + 1);
            }
        }
        return word;
    }
    
    
    private class TrieNode {
        public Map<Character, TrieNode> children;
        public boolean isRoot;
        public TrieNode() {
            children = new HashMap<>();
            isRoot = false;
        }
    }
}

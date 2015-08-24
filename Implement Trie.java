/*
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/
class TrieNode {
    // Initialize your data structure here.
     char content;
        int count;
        List<TrieNode> subNode;
        boolean isEnd;


        public TrieNode(char c) {
            content = c;
            count = 1;
            isEnd = false;
            subNode = new ArrayList<>();
        }

        public TrieNode findSubNode(char c) {
            if (subNode.size() == 0) {
                return null;
            }
            for (TrieNode node : subNode) {
                if (node.content == c) {
                    return node;
                }
            }
            return null;
        }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode('a');
    }

    // Inserts a word into the trie.
    public void insert(String word) {
         if (word == null || word.length() == 0) {
            return;
        }
        if (search(word) == true) {
            return;
        }

        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (current.findSubNode(c) == null) {
                TrieNode newNode = new TrieNode(c);
                current.subNode.add(newNode);
                current = newNode;
            }
            else {
                current.count += 1;
                current = current.findSubNode(c);
            }
        }
        current.isEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (current.findSubNode(c) == null) {
                return false;
            }
            else {
                current = current.findSubNode(c);
            }
        }
        if (current.isEnd == true) {
            return true;
        }
        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }

        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (current.findSubNode(c) == null) {
                return false;
            }
            else {
                current = current.findSubNode(c);
            }
        }
        return true;
    }
}

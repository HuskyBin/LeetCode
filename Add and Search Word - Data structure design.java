/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
*/

class TrieNode {
		char content;
		int count;
		boolean isEnd;
		List<TrieNode> subNode;

		public TrieNode(char c) {
			content = c;
			count = 1;
			isEnd = false;
			subNode = new ArrayList<>();
		}

		public List<TrieNode> findSubNodes(char c ) {
			if (c == '.') {
				return subNode;
			}
			for (TrieNode node : subNode) {
				if (node.content == c) {
					List<TrieNode> result = new ArrayList<>();
					result.add(node);
					return result;
				}
			}
			return null;
		}
	}

public class WordDictionary {

	public TrieNode root = new TrieNode('a');
	private Set<String> set = new HashSet<>();

    // Adds a word into the data structure.
    public void addWord(String word) {
        if (word == null || word.length() == 0) {
        	return;
        }
        if (set.contains(word) == true) {
        	return;
        }
        TrieNode pNode = root;
        for (char c : word.toCharArray()) {
        	if (pNode.findSubNodes(c) == null) {
        		TrieNode newNode = new TrieNode(c);
        		pNode.subNode.add(newNode);
        		pNode = newNode;
        	}
        	else {
        		pNode.count++;
        		TrieNode nextNode = pNode.findSubNodes(c).get(0);
        		pNode = nextNode;
        	}
        }
        pNode.isEnd = true;

    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
        	return false;
        }
        if (set.contains(word)) {
            return true;
        }

        boolean result = searchCore(root, word);
        return result;
    }

    private boolean searchCore(TrieNode pNode, String word) {
    	char firstChar = word.charAt(0);
    	List<TrieNode> nextNodes = pNode.findSubNodes(firstChar);
    	if (nextNodes == null) {
    		return false;
    	}
    	for (TrieNode node : nextNodes) {
    		if (word.length() == 1) {
    			return node.isEnd == true;
    		}
    		boolean subResult = searchCore(node, word.substring(1, word.length()));
    		if (subResult == true) {
    			return subResult;
    		}
    	}
    	return false;
    }
}

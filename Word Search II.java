/*
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/
public class Solution {


    public List<String> findWords(char[][] board, String[] words) {
    	Set<String> resultList = new HashSet<>();
        if (board == null || board[0].length == 0 || words == null || words.length == 0) {
        	return new ArrayList<String>(resultList);
        }

        Trie tree = new Trie();
        for (String word : words) {
        	tree.insert(word);
        }
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board[0].length; j++) {
        		boolean[][] visited = new boolean[board.length][board[0].length];
        		findWordsCore(board, tree, i, j, visited, resultList, "");
        	}
        }
        return new ArrayList<String>(resultList);
    }

    private void findWordsCore(char[][] board, Trie tree, int x, int y, boolean[][] visited, Set<String> resultList, String str) {
    	if (x < 0 || x == board.length || y < 0 || y == board[0].length) {
    		return;
    	}
    	if (visited[x][y] == true) {
    		return;
    	}
    	str = str + board[x][y];
    	if (tree.searchPrefix(str) == false) {
    		return;
    	}
    	if (tree.search(str) == true) {
    		resultList.add(str);
    	}
    	visited[x][y] = true;
    	findWordsCore(board, tree, x, y + 1, visited, resultList, str);
    	findWordsCore(board, tree, x, y - 1, visited, resultList, str);
    	findWordsCore(board, tree, x + 1, y, visited, resultList, str);
    	findWordsCore(board, tree, x - 1, y, visited, resultList, str);
    	visited[x][y] = false;
    }
}









class TrieNode {
	char value;
	List<TrieNode> subNode;
	boolean isEnd;

	public TrieNode(char c) {
		value = c;
		isEnd = false;
		subNode = new ArrayList<>();
	}

	public TrieNode() {
		subNode = new ArrayList<>();
		isEnd = false;
	}

	public TrieNode findSubNodes(char c) {
		for (TrieNode node : subNode) {
			if (node.value == c) {
				return node;
			}
		}
		return null;
	}
}


class Trie {
	private TrieNode root;
	public Trie() {
		root = new TrieNode();
	}

	public boolean search(String word) {
		if (word == null || word.length() == 0) {
			return false;
		}
		TrieNode pNode = root;
		for (char c : word.toCharArray()) {
			TrieNode nextNode = pNode.findSubNodes(c);
			if (nextNode == null) {
				return false;
			}
			else {
				pNode = nextNode;
			}
		}
		return pNode.isEnd == true;
	}

	public boolean searchPrefix(String word) {
		if (word == null || word.length() == 0) {
			return false;
		}
		TrieNode pNode = root;
		for (char c : word.toCharArray()) {
			TrieNode nextNode = pNode.findSubNodes(c);
			if (nextNode == null) {
				return false;
			}
			else {
				pNode = nextNode;
			}
		}
		return true;
	}

	public void insert(String word) {
		if (word == null || word.length() == 0) {
			return;
		}
		TrieNode pNode = root;
		for (char c : word.toCharArray()) {
			TrieNode nextNode = pNode.findSubNodes(c);
			if (nextNode == null) {
				TrieNode newNode = new TrieNode(c);
				pNode.subNode.add(newNode);
				pNode = newNode;
			}
			else {
				pNode = nextNode;
			}
		}
		pNode.isEnd = true;
	}
}




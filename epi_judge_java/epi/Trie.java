package epi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Trie {

	private final TrieNode root;

	public Trie() {
		this.root = new TrieNode();
	}

	public void insert(String word) {
		TrieNode current = root;

		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);

			TrieNode node = current.children.get(c);

			if (node == null) {
				node = new TrieNode();
				current.children.put(c, node);
			}

			current.topMatches.offer(word);
			if (current.topMatches.size() > 3) current.topMatches.poll();

			current = node;
		}

		current.endOfWord = true;
	}

	public List<String> search(String word) {
		List<String> matches = new ArrayList<>();
		TrieNode current = root;

		for (int i = 0; i < word.length(); i++) {
			TrieNode node = current.children.get(word.charAt(i));

			if (node == null) return matches;

			current = node;
		}

		Queue<String> pq = new PriorityQueue<>(current.topMatches);
		while (!pq.isEmpty()) matches.add(pq.poll());

		return matches;
	}

	public boolean hasWord(String word) {
		TrieNode current = root;

		for (int i = 0; i < word.length(); i++) {
			TrieNode node = current.children.get(word.charAt(i));

			if (node == null) return false;

			current = node;
		}

		return current.endOfWord;
	}


	public static void main(String[] args) {
		Trie trie = new Trie();

		trie.insert("amazon");
		trie.insert("amabon");
		trie.insert("amazbn");
		trie.insert("ama");
		trie.insert("amaz");
		trie.insert("gama");
		trie.insert("gamla");
		trie.insert("gamlak");
		trie.insert("kayhan");
		trie.insert("kayham");
		trie.insert("kayhal");
		trie.insert("kayhaz");

		assert(trie.hasWord("amazon"));
		assert(trie.hasWord("amaz"));
		assert(!trie.hasWord("kayha1n"));
		assert(trie.hasWord("gamlak"));
		assert(trie.hasWord("kayhan"));
		assert(!trie.hasWord("gaml"));

		System.out.println(trie.search("ama"));
		System.out.println(trie.search("kayh"));


		System.out.println("Done");
	}

	private class TrieNode {
		Map<Character, TrieNode> children;
		Queue<String> topMatches;
		boolean endOfWord;

		public TrieNode() {
			this.children = new HashMap<>();
			this.topMatches = new PriorityQueue<>((s1, s2) -> {
				return s1.toLowerCase().compareTo(s2.toLowerCase());
			});
			this.endOfWord = false;
		}
	}

}

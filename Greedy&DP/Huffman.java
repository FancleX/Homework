
import java.util.*;

public class Huffman {
	
	private String input;
	private Node huffmanTree; //the huffman tree
	private Map<Character, String> mapping; //maps characters to binary strings
	
	
	/**
	 * The Huffman constructor
	 * 
	 */
	public Huffman(String input) {
		
		this.input = input;
		mapping = new HashMap<>();
		
		//first, we create a map from the letters in our string to their frequencies
		Map<Character, Integer> freqMap = getFreqs(input);
		
		//we'll be using a priority queue to store each node with its frequency,
		//as we need to continually find and merge the nodes with smallest frequency
		PriorityQueue<Node> huffman = new PriorityQueue<>();
		
		/*
		 * 1) add all nodes to the priority queue
		 * 2) continually merge the two lowest-frequency nodes until only one tree remains in the queue
		 * 3) Use this tree to create a mapping from characters (the leaves)
		 *    to their binary strings (the path along the tree to that leaf)
		 */
		// add all nodes to the priority queue
		for (char c : freqMap.keySet()) {
			huffman.add(new Huffman.Node(c, freqMap.get(c), null, null));
		}

		// continually merge the two lowest-frequency nodes until only one tree remains in the queue
		while (!huffman.isEmpty()) {
			Node n1 = huffman.poll();
			Node n2 = huffman.poll();
			huffman.add(new Huffman.Node(null, n1.freq + n2.freq, n1, n2));
		}

		// Use this tree to create a mapping from characters (the leaves) to their binary strings (the path along the tree to that leaf)
		huffmanTree = huffman.poll();
		getBinary(huffmanTree, "");
		}

	// fill the map with nodes' properties
	private void getBinary(Node n, String s) {
		if (n.isLeaf()) {
			mapping.put(n.letter, s);
		}
		getBinary(n.left, "0");
		getBinary(n.right, "1");
	}

	
	/**
	 * Use the global mapping to convert your input string into a binary string
	 */
	public String encode() {
		String res = "";
		for (char c : input.toCharArray()) {
			res += mapping.get(c);
		}
		return res;
	}
	
	/**
	 * Use the huffmanTree to decrypt the encoding back into the original input
	 * 
	 * You should convert each prefix-free group of binary numbers in the
	 * encoding to a character
	 * 
	 * @param encoding - the encoded string that needs to be decrypted
	 * @return the original string (should be the same as "input")
	 */
	public String decode(String encoding) {
		String ans = "";
		int pt = 0;
		while (pt < encoding.length()) {
			Node n = huffmanTree;
			while (!n.isLeaf()) {
				if (encoding.charAt(pt) == '0') {
					n = n.left;
				} else {
					n = n.right;
				}
				pt++;
			}
			ans += n.letter;
		}

		return ans;
	}

	
	/**
	 * This function tells us how well the compression algorithm worked
	 * 
	 * note that a char is represented internal using 8 bits
	 * 
	 * ex. if the string "aabc" maps to "0 0 10 11", we would have
	 * a compression ratio of (6) / (8 * 4) = 0.1875
	 */
	public static double compressionRatio(String input) {
		Huffman h = new Huffman(input);
		String encoding = h.encode();
		int encodingLength = encoding.length();
		int originalLength = 8 * input.length();
		return encodingLength / (double) originalLength;
	}
	
	/**
	 * We've given you this function, which helps you create
	 * a frequency map from the input string
	 */
	private Map<Character, Integer> getFreqs(String input) {
		Map<Character, Integer> freqMap = new HashMap<>();
		for (char c : input.toCharArray()) {
			if (freqMap.containsKey(c)) {
				freqMap.put(c, freqMap.get(c) + 1);
			} else {
				freqMap.put(c, 1);
			}
		}
		return freqMap;
	}


	/**
	 * An inner Node class to build your huffman tree
	 * 
	 * Each node has:
	 * a frequency - the sum of the frequencies of all the node's leaves
	 * a letter - the character that this node represents (only for leaves)
	 * left and right children
	 */
	private class Node implements Comparable<Node> {
		private Character letter; //the letter of this node (only for leaves)
		private int freq; //frequency of this node
		private Node left; //add a 0 to you string
		private Node right; //add a 1 to your string
		
		public Node(Character letter, int freq, Node left, Node right) {
			this.letter = letter;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.freq - o.freq;
		}
	}

}

package entities;

import java.util.ArrayList;

import exceptions.InvalidCharacterException;

/**
 * 	Huffman Tree
 * 
 * 	@author Ryan Sakurai
 */
public class HuffmanTree {

	/**
	 * 	Class that extends Character into a HuffmanTree node
	 */
	static class Node {
		public final String subString;
		public String code;
		public int frequency;
		public Node parent;
		public Node leftChild;
		public Node rightChild;
		
		/**
		 * 	@param character : TextCharacter wrapped by the Node
		 */
		public Node(TextCharacter character) {
			this.subString = character.toString();
			this.code = character.getCode();
			this.frequency = character.getFrequency();
		}

		public Node(String subString) {
			this.subString = subString;
		}
	}
	
	private Node root;
	
	/**
	 * 	@param h : heap with all the characters from the text
	 */
	public HuffmanTree(MinHeap h) {
		while(h.getQuantity() > 1) {
			Node child1 = h.getMin();
			h.pop();
			
			Node child2 = h.getMin();
			h.pop();
			
			Node parent = new Node(child1.subString + child2.subString);
			parent.leftChild = child1;
			parent.rightChild = child2;
			parent.frequency = child1.frequency + child2.frequency;
			child2.parent = parent;
			child1.parent = parent;
	
			h.push(parent);
		}
		
		root = h.getMin();
		h.pop();
	}

	private boolean isLeaf(Node n) {
		return n.rightChild == null && n.leftChild == null;
	}
	
	private void getLeavesPrivate(ArrayList<TextCharacter> output, String codeSoFar, Node current, String newBit) throws InvalidCharacterException {
		codeSoFar += newBit;

		if(isLeaf(current)) {
			current.code = codeSoFar;
			output.add(new TextCharacter(current));
			return;
		}

		if(current.leftChild != null)
			getLeavesPrivate(output, codeSoFar, current.leftChild, "0");

		if(current.rightChild != null)
			getLeavesPrivate(output, codeSoFar, current.rightChild, "1");
	}

	/**
	 * 	@return All of the leaves (single letters), with their codes
	 */
	public ArrayList<TextCharacter> getLeaves() {
		ArrayList<TextCharacter> leaves = new ArrayList<>();
		try {
			getLeavesPrivate(leaves, "", this.root, "");
		}
		catch(InvalidCharacterException e) {
			System.out.println("Error not supposed to happen, check code if it does.");
		}
		return leaves;
	}
	
}

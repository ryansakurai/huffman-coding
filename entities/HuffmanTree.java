package entities;

import java.util.ArrayList;

/**
 * 	Huffman Tree
 * 
 * 	@author Ryan Sakurai
 */
public class HuffmanTree {

	/**
	 * 	Class that extends Character into a HuffmanTree node
	 */
	static class Node extends Character {
		private Node parent;
		private Node leftChild;
		private Node rightChild;
		
		/**
		 * 	Creates a Node around a Character
		 * 
		 * 	@param character : Character wrapped by the Node
		 */
		public Node(Character character) {
			super(character.toString());
			setCode(character.getCode());
			frequency = character.getFrequency();
		}

		/**
		 * @return Node's parent in the Huffman Tree
		 */
		public Node getParent() {
			return parent;
		}

		/**
		 * @param parent : Node's parent in the Huffman Tree
		 */
		public void setParent(Node parent) {
			this.parent = parent;
		}

		/**
		 * @return Node's left child in the Huffman Tree
		 */
		public Node getLeftChild() {
			return leftChild;
		}

		/**
		 * @param left : Node's left child in the Huffman Tree
		 */
		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}

		/**
		 * @return Node's right child in the Huffman Tree
		 */
		public Node getRightChild() {
			return rightChild;
		}

		/**
		 * @param right : Node's right child in the Huffman Tree
		 */
		public void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}
	}
	
	private Node root;
	
	/**
	 * 	@param h : heap with all the characters from the text
	 */
	public HuffmanTree(MinHeap h) {
		while(h.getQuantity() > 1) {
			Node child1 = h.getMinNode();
			h.pop();
			
			Node child2 = h.getMinNode();
			h.pop();
			
			Node parent = new Node(new Character(child1.toString() + child2.toString()));
			parent.setLeftChild(child1);
			child1.setParent(parent);
			parent.setRightChild(child2);
			child2.setParent(parent);

			for(int i=0; i < child1.getFrequency() + child2.getFrequency(); i++)
				parent.increaseFrequency();
	
			h.push(parent);
		}
		
		root = h.getMinNode();
		h.pop();
	}

	private boolean isLeaf(Node n) {
		return n.getRightChild() == null && n.getLeftChild() == null;
	}
	
	private void getLeavesPrivate(ArrayList<Character> output, String codeSoFar, Node current, String newBit) {
		codeSoFar += newBit;

		if(isLeaf(current)) {
			current.setCode(codeSoFar);
			output.add(current);
			return;
		}

		if(current.getLeftChild() != null)
			getLeavesPrivate(output, codeSoFar, current.getLeftChild(), "0");

		if(current.getRightChild() != null)
			getLeavesPrivate(output, codeSoFar, current.getRightChild(), "1");
	}

	/**
	 * 	@return All of the leaves (single letters), with their codes
	 */
	public ArrayList<Character> getLeaves() {
		ArrayList<Character> leaves = new ArrayList<>();
		getLeavesPrivate(leaves, "", this.root, "");
		return leaves;
	}
	
}

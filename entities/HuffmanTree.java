package entities;

import java.util.ArrayList;

/**
 * 	Huffman Tree
 * 
 * 	@author Ryan Sakurai
 */
public class HuffmanTree {
	
	private Node root;
	
	/**
	 * 	@param h : heap with all the characters from the text
	 */
	public HuffmanTree(MinHeap h) {
		// until there's only one Node left in the MinHeap
		while(h.getQuantity() > 1) {
			Node child1 = h.getMinNode();
			h.pop();
			
			Node child2 = h.getMinNode();
			h.pop();
			
			Node parent = new Node( new Character( child1.toString() + child2.toString() ) );
			parent.setLeft(child1);
			parent.setRight(child2);

			for(int i=0; i < child1.getFrequency() + child2.getFrequency(); i++)
				parent.increaseFrequency();
			
			child1.setParent(parent);
			child2.setParent(parent);
			
			h.push(parent);
		}
		
		root = h.getMinNode();
		h.pop();
	}
	
	/**
	 * 	@return All of the Characters inputed, with their codes
	 */
	public ArrayList<Character> getCharacters() {
		ArrayList<Character> leaves = new ArrayList<>();
		getLeavesPrivate(leaves, "", this.root, ""); //as it's the first call, no bit is passed
		return leaves;
	}
	
	/**
	 * 	Private recursive call of getCharacters
	 * 
	 * 	@param l : list where leaves will be stored
	 * 	@param code : code so far
	 * 	@param n : current node
	 * 	@param bit : bit to be added to the code
	 */
	private void getLeavesPrivate(ArrayList<Character> l, String code, Node n, String bit) {
		code += bit;
					
		//if it's a leaf, it'll be pushed to the list (the code is complete)
		if( n.getRight() == null && n.getLeft() == null ) {
			n.setCode(code);
			l.add(n);
		}
					
		//if not, the recursion continues
		//0 is passed to left children and 1 to right children
		else {
			if(n.getLeft() != null)
				getLeavesPrivate(l, code, n.getLeft(), "0");
					
			if(n.getRight() != null)
				getLeavesPrivate(l, code, n.getRight(), "1");
		}
	}
	
	

	/**
	 * 	Class that extends Character into a HuffmanTree node
	 */
	static class Node extends Character {
		private Node parent;
		private Node left;	//left child
		private Node right;	//right child
		
		/**
		 * 	Creates a Node around a Character
		 * 
		 * 	@param character : Kernel of the Node
		 */
		public Node( Character character ) {
			super( character.toString() );
			setCode( character.getCode() );
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
		public Node getLeft() {
			return left;
		}

		/**
		 * @param left : Node's left child in the Huffman Tree
		 */
		public void setLeft(Node left) {
			this.left = left;
		}

		/**
		 * @return Node's right child in the Huffman Tree
		 */
		public Node getRight() {
			return right;
		}

		/**
		 * @param right : Node's right child in the Huffman Tree
		 */
		public void setRight(Node right) {
			this.right = right;
		}
	}
	
}

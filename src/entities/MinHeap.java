package entities;

import java.util.ArrayList;

/**
 * 	Heap that stores Huffman Tree nodes
 * 
 * 	@author Ryan Sakurai
 */
public class MinHeap {

	private final ArrayList<HuffmanTree.Node> array = new ArrayList<>();
	
	/**
	 * 	@return Quantity of elements in the Heap
	 */
	public int getQuantity() {
		return array.size();
	}
	
	/**
	 * 	@return Least character from the Heap
	 */
	public Character getMin() {
		return (Character) array.get(0);
	}
	
	/**
	 * 	@return Least character from the Heap
	 */
	HuffmanTree.Node getMinNode() {
		return array.get(0);
	}
	
	/**
	 * 	@param c : element that'll be pushed
	 */
	public void push(Character c) {
		HuffmanTree.Node temp;

		if( c instanceof HuffmanTree.Node )
			temp = (HuffmanTree.Node) c;
		else
			temp = new HuffmanTree.Node(c);
		
		array.add(temp);

		//fix Heap
		//parent == (i-1)/2, left child == i*2+1, right child == i*2+2
		for( int index = array.size() - 1; (index-1)/2 >= 0 && array.get(index).getFrequency() < array.get( (index-1)/2 ).getFrequency(); index = (index-1)/2 )
			swap( index, (index-1)/2 );
	}
	
	/**
	 * 	Pops the least character from the Heap
	 */
	public void pop() {
		swap( 0, array.size()-1 );
		array.remove( array.size()-1 );
		
		//fixes the Heap
		//parent == (i-1)/2, left child == i*2+1, right child == i*2+2
		for( int index = 0; index*2 + 1 < array.size() && array.get(index).getFrequency() > array.get( index*2 + 1 ).getFrequency() || index*2 + 2 < array.size() && array.get(index).getFrequency() > array.get( index*2 + 2 ).getFrequency(); ) {
			
			//both children exist
			if(index*2 + 1 < array.size() && index*2 + 2 < array.size()) {
				
				//right child is the least
				if( array.get(index*2 + 1).getFrequency() < array.get(index*2 + 2).getFrequency() ) {
					swap(index , index*2 + 1);
					index = index*2 + 1;
				}
				
				//left child is the least
				else {
					swap(index , index*2 + 2);
					index = index*2 + 2;
				}
				
			}
			
			//only the left child exists
			else if(index*2 + 1 < array.size()) {
				swap(index , index*2 + 1);
				index = index*2 + 1;
			}
			
			//only the right child exists
			else {
				swap(index , index*2 + 2);
				index = index*2 + 2;
			}

		}
	}
	
	
	
	/**
	 * 	@param a : index of the first element
	 * 	@param b : index of the second element
	 */
	private void swap(int a, int b) {
		HuffmanTree.Node temp = array.get(a);
		array.set( a, array.get(b) );
		array.set( b, temp );
	}
	
}

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

	private class ItemPointer {
		private int idx;
		private int parentIdx;
		private int leftChildIdx;
		private int rightChildIdx;

		private void updateRelatives() {
			this.parentIdx = (idx-1)/2;
			this.leftChildIdx = idx*2 + 1;
			this.rightChildIdx = idx*2 + 2;
		}

		public ItemPointer(int idx) {
			this.idx = idx;
			updateRelatives();
		}

		public void setIdx(int idx) {
			this.idx = idx;
			updateRelatives();
		}

		public int getIdx() {
			return idx;
		}

		public int getParentIdx() {
			return parentIdx;
		}

		public int getLeftChildIdx() {
			return leftChildIdx;
		}

		public int getRightChildIdx() {
			return rightChildIdx;
		}
	}

	private boolean exists(int idx) {
		return idx >= 0 && idx < array.size();
	}

	private int compare(int idxA, int idxB) {
		return array.get(idxA).getFrequency() - array.get(idxB).getFrequency();
	}

	private void swap(int a, int b) {
		HuffmanTree.Node temp = array.get(a);
		array.set( a, array.get(b) );
		array.set( b, temp );
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
		for( int idx = array.size() - 1; (idx-1)/2 >= 0 && array.get(idx).getFrequency() < array.get( (idx-1)/2 ).getFrequency(); idx = (idx-1)/2 )
			swap( idx, (idx-1)/2 );
	}

	private void fixDown(int itemIdx) {
		ItemPointer current = new ItemPointer(itemIdx);

		while(exists(current.getLeftChildIdx()) && compare(current.getIdx(), current.getLeftChildIdx()) > 0 || exists(current.getRightChildIdx()) && compare(current.getIdx(), current.getRightChildIdx()) > 0) {

			if(exists(current.getLeftChildIdx()) && exists(current.getRightChildIdx())) {
				if(compare(current.getLeftChildIdx(), current.getRightChildIdx()) < 0) {
					swap(current.getIdx(), current.getLeftChildIdx());
					current.setIdx(current.getLeftChildIdx());
				}
				else {
					swap(current.getIdx(), current.getRightChildIdx());
					current.setIdx(current.getRightChildIdx());
				}
				
			}
			else if(exists(current.getLeftChildIdx())) {
				swap(current.getIdx(), current.getLeftChildIdx());
				current.setIdx(current.getLeftChildIdx());
			}
			else {
				swap(current.getIdx(), current.getRightChildIdx());
				current.setIdx(current.getRightChildIdx());
			}

		}
	}
	
	/**
	 * 	Pops the least character from the Heap
	 */
	public void pop() {
		swap(0, array.size() - 1);
		array.remove(array.size() - 1);
		fixDown(0);
	}
	
}

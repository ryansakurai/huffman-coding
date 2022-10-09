import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Comparator;
import entities.MinHeap;
import entities.HuffmanTree;
import entities.Character;

/**
 * 	< Huffman Coding >
 * 
 * 	This program reads a text until EOF and prints the frequency and Huffman Coding code of
 * 	all characters used in it. After that, it informs the size of the text using Huffman
 * 	Coding compared to the size using regular Java encoding (UTF-16).
 * 
 * 	Both the Heap and the Huffman Tree are implemented.
 * 
 * 	@author Ryan Sakurai
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		TreeSet<Character> textTree = new TreeSet<>( new TreeComparator() );

		System.out.printf("Input text:\n\n");
		
		InputStreamReader stream = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(stream);

		//reads text and stores the characters in the treeset of Character objects
		for(int tempChar; ( tempChar = reader.read() ) != -1; ) {
			Character tempNode = new Character( java.lang.Character.toString( (char) tempChar ) );
			
			Character tempFloor = textTree.floor( tempNode );
			
			//if the character is already in the treeset, +1 frequency is added
			if( tempFloor != null && tempFloor.toString().equals( tempNode.toString() ) ) {
				tempFloor.increaseFrequency();
			}
			
			//if not, the node will be added to the treeset
			else {
				tempNode.increaseFrequency();
				textTree.add( tempNode );
			}
		}
		
		stream.close();
		reader.close();
		
		//builds the heap with the elements from the treeset
		MinHeap huffmanTreeHeap = new MinHeap();
		while( !textTree.isEmpty() )
			huffmanTreeHeap.push( textTree.pollFirst() );

		//builds the Huffman tree using the Heap and extracts the leaves
		HuffmanTree huffmanTree = new HuffmanTree(huffmanTreeHeap);
		ArrayList<Character> huffmanTreeLeaves = huffmanTree.getCharacters();
		huffmanTreeLeaves.sort( new ListComparator() );
		
		int totalFrequency = 0;
		int qtBits = 0;
		
		System.out.println();
		
		//outputs code and frequency of each character and counts total frequency and quantity of bits
		for(int i=0; i < huffmanTreeLeaves.size(); i++) {
			switch( huffmanTreeLeaves.get(i).toString() ) {
				case "\n":
					System.out.printf( "%4s :\t%s - %,d occurances\n", "\"\\n\"", huffmanTreeLeaves.get(i).getCode(), huffmanTreeLeaves.get(i).getFrequency() );
					break;

				case "\r":
					System.out.printf( "%4s :\t%s - %,d occurances\n", "\"\\r\"", huffmanTreeLeaves.get(i).getCode(), huffmanTreeLeaves.get(i).getFrequency() );
					break;

				case "\t":
					System.out.printf( "%4s :\t%s - %,d occurances\n", "\"\\t\"", huffmanTreeLeaves.get(i).getCode(), huffmanTreeLeaves.get(i).getFrequency() );
					break;

				default:
					System.out.printf( "%4s :\t%s - %,d occurances\n", String.format("\"%s\"", huffmanTreeLeaves.get(i)), huffmanTreeLeaves.get(i).getCode(), huffmanTreeLeaves.get(i).getFrequency() );
					break;
			}
			
			qtBits += huffmanTreeLeaves.get(i).getFrequency() * huffmanTreeLeaves.get(i).getCode().length();
			totalFrequency += huffmanTreeLeaves.get(i).getFrequency();
		}
		
		System.out.printf( "\nSize using Huffman Coding:\t   %,d bits\n", qtBits);
		System.out.printf( "Size using regular Java encoding:  %,d bits\n\n", totalFrequency*16);
	}

	
	

	//comparator used in TreeSet
	private static class TreeComparator implements Comparator<Character> {
		@Override
		public int compare(Character a, Character b) {
			return a.toString().compareTo( b.toString() );
		}
	}
	
	//comparator used in ArrayList sorting
	private static class ListComparator implements Comparator<Character> {
		@Override
		public int compare(Character a, Character b) {
			return b.getFrequency() - a.getFrequency();
		}
	}
	
}

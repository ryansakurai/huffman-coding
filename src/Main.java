import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Comparator;

import entities.MinHeap;
import entities.HuffmanTree;
import entities.TextCharacter;
import exceptions.InvalidCharacterException;

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

	private static class TreeSetComparator implements Comparator<TextCharacter> {
		@Override
		public int compare(TextCharacter a, TextCharacter b) {
			return a.toString().compareTo(b.toString());
		}
	}

	private static TreeSet<TextCharacter> readText() {
		TreeSet<TextCharacter> textTree = new TreeSet<>(new TreeSetComparator());

		System.out.printf("Input text:\n\n");

		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			for(int tempChar; ( tempChar = reader.read() ) != -1; ) {
				TextCharacter character = new TextCharacter(Character.toString((char) tempChar));
				TextCharacter floorInTree = textTree.floor(character);
				if(floorInTree != null && floorInTree.toString().equals(character.toString()))
					floorInTree.increaseFrequency();
				else
					textTree.add(character);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(InvalidCharacterException e) {
			e.printStackTrace();
		}

		return textTree;
	}

	private static MinHeap buildHeap(TreeSet<TextCharacter> textTree) {
		MinHeap huffmanTreeHeap = new MinHeap();
		while(!textTree.isEmpty())
			huffmanTreeHeap.push(textTree.pollFirst());
		return huffmanTreeHeap;
	}
	
	private static class ListDecrescentComparator implements Comparator<TextCharacter> {
		@Override
		public int compare(TextCharacter a, TextCharacter b) {
			return b.getFrequency() - a.getFrequency();
		}
	}
	
	public static void main(String[] args) {
		TreeSet<TextCharacter> textTree = readText();
		MinHeap huffmanTreeHeap = buildHeap(textTree);
		HuffmanTree huffmanTree = new HuffmanTree(huffmanTreeHeap);
		ArrayList<TextCharacter> huffmanTreeLeaves = huffmanTree.getLeaves();
		huffmanTreeLeaves.sort(new ListDecrescentComparator());
		
		System.out.println();
		
		final int BITS_PER_UTF16_CHAR = 16;
		int totalFrequency = 0;
		int qtBits = 0;
		
		for(TextCharacter character: huffmanTreeLeaves) {
			qtBits += character.getFrequency() * character.getCode().length();
			totalFrequency += character.getFrequency();
			switch(character.toString()) {
				case "\n":
					System.out.printf("%4s :\t%s - %,d occurances\n", "\"\\n\"", character.getCode(), character.getFrequency());
					break;
				case "\r":
					System.out.printf("%4s :\t%s - %,d occurances\n", "\"\\r\"", character.getCode(), character.getFrequency());
					break;
				case "\t":
					System.out.printf("%4s :\t%s - %,d occurances\n", "\"\\t\"", character.getCode(), character.getFrequency());
					break;
				default:
					System.out.printf("%4s :\t%s - %,d occurances\n", String.format("\"%s\"", character), character.getCode(), character.getFrequency());
					break;
			}
		}
		System.out.printf("\nSize using Huffman Coding:\t   %,d bits\n", qtBits);
		System.out.printf("Size using regular Java encoding:  %,d bits\n\n", totalFrequency * BITS_PER_UTF16_CHAR);
	}

}

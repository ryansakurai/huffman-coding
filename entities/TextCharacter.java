package entities;

import exceptions.InvalidCharacterException;

/**
 * 	Class that stores a character, its code and its frequency in
 * 	the text
 * 
 * 	@author Ryan Sakurai	
 */
public class TextCharacter {
	
	private final String character;
	private String code;
	private int frequency;
	
	/**
	 * 	@param string : character(s) that'll be wrapped by the class
	 * 	@throws InvalidCharacterException - if 'character' isn't a single character
	 */
	public TextCharacter(String character) throws InvalidCharacterException {
		if(character.length() != 1)
			throw new InvalidCharacterException();

		this.character = character;
	}

	TextCharacter(HuffmanTree.Node node) throws InvalidCharacterException {
		if(node.subString.length() != 1)
			throw new InvalidCharacterException();

		this.character = node.subString;
		this.code = node.code;
		this.frequency = node.frequency;
	}

	/**
	 * 	@return character's Huffman code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 	@param code : character's Huffman code
	 */
	void setCode(String code) {
		this.code = code;
	}

	/**
	 * 	@return character's frequency in the text
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * 	Increases frequency by one
	 */
	public void increaseFrequency() {
		frequency++;
	}

	/**
	 *  @return Character wrapped by the class
	 */
	@Override
	public String toString() {
		return character;
	}
	
}

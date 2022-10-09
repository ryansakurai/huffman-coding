package entities;

/**
 * 	Class that stores a character, its code and its frequency in
 * 	the text
 * 
 * 	@author Ryan Sakurai	
 */
public class Character {
	
	private final String string;
	private String code;
	protected int frequency;
	
	/**
	 * 	@param string : character(s) that'll be referred by the class
	 */
	public Character(String string) {
		this.string = string;
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
	public void setCode(String code) {
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
	 *  @return Character referred by the class
	 */
	@Override
	public String toString() {
		return string;
	}
	
}

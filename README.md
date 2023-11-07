## Huffman Coding

Huffman Coding is a technique commonly used for lossless data compression. It consists in atributing smaller codes to characters that appear more frequently in a text and bigger ones to characters that appear less frequently, which means it uses variable-lenght codes. It also uses prefix codes, so the fact that no code is the prefix of another code assures there's no ambiguity when decoding data.

This program reads a text until EOF, generates the Huffman Tree, using an auxiliar priority queue (Heap), and prints the frequency and Huffman Coding code of all characters used in it. After that, it informs the size of the text using Huffman Coding compared to the size using regular Java encoding (UTF-16).

Both the Heap and the Huffman Tree are implemented.

![image](readme-assets/execution.png)

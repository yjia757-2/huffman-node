// Yiran Jia
// 5/31/18
// CSE143
// TA: JASON WAATAJA 
// Assignment 8: Encoding and Decoding - HuffmanTree
//
// This program helps encoding and decoding a text file. It has two parts. The methods in
// the first part helps encoding by firstly passing an array of characters frequences,
// and secodnly building a binary tree based on the characters values and frequencies, and 
// thirdly writing the binary tree with values and frequencies into an output file. The methods
// in the second part helps decoding by firstly passing a scanner which reads the 
// input file, and secondly building a tree based on the characters values and the 
// corresponding codes the scanner reads, and thirdly writing the characters values to the 
// given output stream by reading the input bits and traversing the binayr tree it constructs.  

import java.io.*;
import java.util.*;

public class HuffmanTree {
   private HuffmanNode ourTree;
   
   // post: constructs a binary tree for the characters whose frequencies are greater than 0.
   // By passing the given array, it builds a binary tree of character values and frequencies.
   public HuffmanTree(int[] count) { 
      Queue<HuffmanNode> pQueue = new PriorityQueue<HuffmanNode>(); 
      for (int i = 0; i < count.length; i++) { 
         if (count[i] > 0) {
            pQueue.add(new HuffmanNode(i, count[i])); 
         }
      }
      pQueue.add(new HuffmanNode(count.length, 1)); 
      while (pQueue.size() > 1) {
         HuffmanNode sOne = pQueue.remove(); 
         HuffmanNode sTwo = pQueue.remove(); 
         pQueue.add(new HuffmanNode(sOne.freq + sTwo.freq, sOne, sTwo)); 
      }
      ourTree = pQueue.remove(); 
   }
    
   // post: constructs a binary tree by passing a Scanner that is linked with a code file. 
   // The binary tree it builds does not contain character frequencies 
   public HuffmanTree(Scanner input) {  
      ourTree = new HuffmanNode(); 
      while (input.hasNextLine()) { 
         int n = Integer.parseInt(input.nextLine()); 
         String code = input.nextLine();  
         ourTree = build(code, ourTree, n);
      }  
   }
   
   // post: writes the binary tree to the given output stream in standard format
   public void write(PrintStream output) {
      writeHelper(output, ourTree, "");
   }
    
   // post: writes the binary tree to the given output stream in standard format. 
   // By passing the given binary tree, it writes the character value in one
   // line above the character code, which is built upon the given string path,
   // to the given output stream. 
   private void writeHelper(PrintStream output, HuffmanNode root, String path) {
      if (root != null) {
         if (root.zero == null) { 
            output.println(root.value);
            output.println(path);
         } else {
            String pathZero = path + 0;
            writeHelper(output, root.zero, pathZero);
            String pathOne = path + 1;
            writeHelper(output, root.one, pathOne);
         }
      }
   }
   
   // post: returns a HuffmanNode which contains the given integer character value by 
   // passing the given string code of the character. The HuffmanNode is built based on 
   // the given HuffmanNode root
   private HuffmanNode build(String code, HuffmanNode root, int n) {
      if (code.length() == 0) {  
         root = new HuffmanNode(n, -1);   
      } else { 
         if (root == null) {
            root = new HuffmanNode();
         }
         if (code.charAt(0) == '0') { 
            root.zero = build(code.substring(1), root.zero, n);  
         } else { 
            root.one = build(code.substring(1), root.one, n); 
         }
      } 
      return root;
   }
   
   // pre: assumes the input stream contains a legal encoding of characters
   // post: reads individual bits from the given input stream and writes the corresponding 
   // characters to the given output stream. It stops reading when it encounters 
   // a character whose value is equal to the given eof parameter. It writes 
   // corresponding characters except the one has the eof parameter. 
   public void decode(BitInputStream input, PrintStream output, int eof) { 
      decodeHelper(input, output, eof, ourTree); 
   }
   
   // pre: assumes the input stream contains a legal encoding of characters
   // post: read individual bits from the given input stream, passes the given 
   // HuffmanNode root base on what it reads, and writes the corresponding 
   // character values to the given output stream. It stops reading when it encounters 
   // a character whose value is equal ot the given eof parameter. It writes 
   // corresponding characters except the one has the eof parameter.
   private void decodeHelper (BitInputStream input, PrintStream output, int eof, HuffmanNode root) {
       while (root.value != eof) {
         if (root.zero == null) {
            output.write(root.value);
            root = ourTree;
         } else {
            if (input.readBit() == 0) {  
               root = root.zero;
            } else {
               root = root.one;
            }
         }
      }
   }
}
 
  


      
  
  
 
   
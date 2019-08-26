// Yiran Jia
// 5/31/18
// CSE143
// TA: JASON WAATAJA 
// Assignment 8: Encoding and Decoding - HuffmanNodeNode
//
// Class for storing a single node of a binary tree of character value and frequency

import java.util.*;

public class HuffmanNode implements Comparable<HuffmanNode> {
   public int value;
   public int freq;
   public HuffmanNode zero;
   public HuffmanNode one;

   // constructs an empty leaf node 
   public HuffmanNode() {
      this(0, -1, null, null);
   }
   
   // constructs a leaf node contains character value and frequency 
   public HuffmanNode(int value, int freq) {
      this(value, freq, null, null);
   }
   
   // constructs a branch node only contains frequency  
   public HuffmanNode(int freq, HuffmanNode zero, HuffmanNode one) {
      this(0, freq, zero, one);
   }
   
   // constructs a branch node contains character value and frequency
   public HuffmanNode(int value, int freq, HuffmanNode zero, HuffmanNode one) { 
      this.value = value;
      this.freq = freq;
      this.zero = zero;
      this.one = one; 
   }
   
   // returns a negative if this frequency is less than the other, 0 if this 
   // frequency is equal to the other, a positive if this frequency is greater
   // than the other
   public int compareTo(HuffmanNode other) {
      return freq - other.freq;
   }
}

   

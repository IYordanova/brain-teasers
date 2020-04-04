package chapter_5.Question5_1;

import CareerCupLibrary.AssortedMethods;

public class Question {

	public static int updateBits(int n, int m, int i, int j) {
		int max = ~0; /* All 1�s */
		System.out.println("max: " + Integer.toBinaryString(max));
		// 1�s through position j, then 0�s
		int left = max - ((1 << j) - 1);
		System.out.println("left: " + Integer.toBinaryString(left));
		// 1�s after position i
	  	int right = ((1 << i) - 1);
		System.out.println("right: " + Integer.toBinaryString(right));
		// 1�s, with 0s between i and j
		int mask = left | right;
		System.out.println("mask: " + Integer.toBinaryString(mask));
		// Clear i through j, then put m in there 
		int a =  (n & mask) | (m << i);
		System.out.println("final: " + Integer.toBinaryString(a));
		return a;
	}
	
	public static void main(String[] args) {
		int a = 103217;
		System.out.println(AssortedMethods.toFullBinaryString(a));
		int b = 13;
		System.out.println(AssortedMethods.toFullBinaryString(b));		
		int c = updateBits(a, b, 4, 7);
		System.out.println(AssortedMethods.toFullBinaryString(c));
	}

}

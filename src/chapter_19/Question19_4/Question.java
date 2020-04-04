package chapter_19.Question19_4;

public class Question {

	public static int getMax(int a, int b) {
		int c = a - b;
		int ki = c >> 31;
		int k = ki & 0x1;
		int max = a - k * c;
		return max;
	}
	
	public static void main(String[] args) {
		int a = 103217;
		int b = 242239;
		System.out.println("max(" + a + ", " + b + ") = " + getMax(a, b));
	}

}

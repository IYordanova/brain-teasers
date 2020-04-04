package chapter_19.Question19_6;

import CareerCupLibrary.AssortedMethods;

public class Question {

	static String[] wordarr1 = {"","One ", "Two ", "Three ", "Four ",
			"Five ", "Six ", "Seven ", "Eight ","Nine "};
	static String[] wordarr11 ={"", "Eleven ", "Twelve ", "Thirteen ",
			"Fourteen ", "Fifteen ", "Sixteen ",
			"Seventeen ", "Eighteen ", "Nineteen "};
	static String[] wordarr10 = {"","Ten ", "Twenty ", "Thirty ", "Forty ",
			"Fifty ", "Sixty ", "Seventy ", "Eighty ",
			"Ninety "};
	static String[] wordarr100 = {"", "Hundred ", "Thousand "};

	public static String numToString(int num) {
		StringBuilder sb = new StringBuilder();
		
		// Count number of digits in num.
		int len = 1;
		while (Math.pow(10, len) < num) {
			len++;
		}

		int tmp;

		if (num == 0) {
			sb.append("Zero");
		} else {
			if (len > 3 && len % 2 == 0) {
				len++;
			}
			do {
				// Number greater than 999
				if (len > 3) {
					tmp = (num / (int)Math.pow(10,(double)len-2));
					// If tmp is 2 digit number and not a multiple of 10
					if (tmp / 10 == 1 && tmp%10 != 0) {
						sb.append(wordarr11[tmp % 10]) ;
					} else {
						sb.append(wordarr10[tmp / 10]);
						sb.append(wordarr1[tmp % 10]);
					}
					if (tmp > 0) {
						sb.append(wordarr100[len / 2]);
					}
					num = num % (int)(Math.pow((double)10,(double)len-2));
					len = len-2;

				} else { // Number is less than 1000
					tmp = num / 100;
					if (tmp != 0) {
						sb.append(wordarr1[tmp]);
						sb.append(wordarr100[len / 2]);
					}
					tmp = num % 100 ;
					if(tmp / 10 == 1 && tmp % 10 != 0) {
						sb.append(wordarr11[tmp % 10]) ;
					} else {
						sb.append(wordarr10[tmp / 10]);
						sb.append(wordarr1[tmp % 10]);
					}
					len = 0;
				}
			} while(len > 0);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			int value = AssortedMethods.randomIntInRange(0, 10000);
			String s = numToString(value);
			System.out.println(value + ": " + s);
		}
	}
}

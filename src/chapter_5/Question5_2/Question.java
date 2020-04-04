package chapter_5.Question5_2;

public class Question {
	public static String printBinary(String n) {
		int intPart = Integer.parseInt(n.substring(0, n.indexOf('.')));
		double decPart = Double.parseDouble(n.substring(n.indexOf('.')));
		String int_string = "";
		while (intPart > 0) {
			int r = intPart % 2;
			intPart >>= 1;
			int_string = r + int_string;
		}
		/* Convert the decimal part */
		StringBuilder dec_string = new StringBuilder();
		while (decPart > 0) {
			if (dec_string.length() > 32) {
				return "ERROR";
			}
			if (decPart == 1) {
				dec_string.append((int)decPart);
				break;
			}
			double r = decPart * 2;
			if (r >= 1) {
				dec_string.append(1);
				decPart = r - 1;
			} else {
				dec_string.append(0);
				decPart = r;
			}
		}
		return int_string + "." + dec_string.toString();
	}
	
	public static void main(String[] args) {
		String n = "19.21";
		String bs = printBinary(n);
		System.out.println(bs);
	}
}

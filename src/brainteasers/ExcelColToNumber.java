package brainteasers;

public class ExcelColToNumber {


    private static int titleToNumber(String A) {
        char[] chars = A.toCharArray();
        int sum = 0;
        int j = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            double pow = Math.pow(26, i);
            sum += pow * (chars[j] - 64);
            j++;
        }
        return sum;
    }

    private static String numberToTitle(int col) {
        StringBuilder res = new StringBuilder();

        while (col > 0) {
            int index = (col - 1) % 26;
            res.append((char) (index + 'A'));
            col = (col - 1) / 26;
        }

        return res.reverse().toString();
    }


    public static void main(String[] args) {
        assert titleToNumber("AB") == 28;
        assert titleToNumber("AZA") == 1353;

        assert numberToTitle(28).equals("AB");
        assert numberToTitle(1353).equals("AZA");
    }
}

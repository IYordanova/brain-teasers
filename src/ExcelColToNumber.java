import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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


    public static void main(String[] args) {
        System.out.println(titleToNumber("AB"));
        System.out.println(titleToNumber("AZA"));
    }
}

package brainteasers.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GreyBitsCodes {

    public static ArrayList<Integer> grayCode(int a) {
        if (a <= 0) {
            return new ArrayList<>();
        }

        ArrayList<String> arr = new ArrayList<>(Arrays.asList("0", "1"));

        int j;
        for(int i = 2; i < (1 << a); i = i << 1) {
            for (j = i - 1; j >= 0; j--) {
                arr.add(arr.get(j));
            }
            for (j = 0; j < i; j++) {
                arr.set(j, "0" + arr.get(j));
            }
            for (j = i; j < 2 * i; j++) {
                arr.set(j, "1" + arr.get(j));
            }
        }

       return arr.stream()
                .map(str -> Integer.parseInt(str, 2))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public static void main(String[] args) {
        System.out.println(grayCode(1));
        System.out.println(grayCode(2));
        System.out.println(grayCode(3));
        System.out.println(grayCode(4));
    }


}

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MaxProductInArray {

    private static String solution(int[] xs) {
        if (xs.length == 1) {
            return String.valueOf(xs[0]);
        }
        List<BigInteger> negative = Arrays.stream(xs)
                .filter(i -> i < 0)
                .mapToObj(i -> new BigInteger(String.valueOf(i)))
                .sorted()
                .collect(Collectors.toList());

        Optional<BigInteger> positiveProduct = Arrays.stream(xs)
                .filter(i -> i > 0)
                .mapToObj(i -> new BigInteger(String.valueOf(i)))
                .reduce(BigInteger::multiply);

        Optional<BigInteger> negativeProduct = negative.size() % 2 == 0
                ? negative.stream()
                .reduce(BigInteger::multiply)
                : negative.stream()
                .limit(negative.size() - 1)
                .reduce(BigInteger::multiply);

        if (negativeProduct.isEmpty() && positiveProduct.isEmpty()) {
            return  "0";
        }

        return String.valueOf(negativeProduct.orElse(BigInteger.ONE).multiply(positiveProduct.orElse(BigInteger.ONE)));
    }

    public static void main(String[] args) {
        String s_0 = solution(new int[]{2});
        System.out.println(s_0.equals("2") + " - " + s_0);
//
//        s_0 = solution(new int[]{0});
//        System.out.println(s_0.equals("0") + " - " + s_0);
//
//        s_0 = solution(new int[]{-2, -2});
//        System.out.println(s_0.equals("4") + " - " + s_0);
//
//        s_0 = solution(new int[]{2, -2});
//        System.out.println(s_0.equals("2") + " - " + s_0);
//
//        s_0 = solution(new int[]{-2, 2});
//        System.out.println(s_0.equals("2") + " - " + s_0);
//
//        s_0 = solution(new int[]{0, 0, 0, 0, 0});
//        System.out.println(s_0.equals("0") + " - " + s_0);
//
//        s_0 = solution(new int[]{2, 0, 2, 2, 0});
//        System.out.println(s_0.equals("8") + " - " + s_0);
//
//        s_0 = solution(new int[]{-2, -3, 4, -5});
//        System.out.println(s_0.equals("60") + " - " + s_0);
//
//        s_0 = solution(new int[]{2, -3, 5});
//        System.out.println(s_0.equals("10") + " - " + s_0);
//
//        s_0 = solution(new int[]{2, -3, -2, 5});
//        System.out.println(s_0.equals("60") + " - " + s_0);
//
//        s_0 = solution(new int[]{2, -3, -2, -6, 0, 5});
//        System.out.println(s_0.equals("180") + " - " + s_0);
//
//        s_0 = solution(new int[]{-2, -3, -2, -6, 0, -5});
//        System.out.println(s_0.equals("180") + " - " + s_0);
//
//        s_0 = solution(new int[]{-2, -3, -2, 0, -5});
//        System.out.println(s_0.equals("60") + " - " + s_0);
//
//        s_0 = solution(new int[]{2, 3, 2, 0, 5});
//        System.out.println(s_0.equals("60") + " - " + s_0);
//
//
//        s_0 = solution(new int[]{2, -3, 1, 0, -5});
//        System.out.println(s_0.equals("30") + " - " + s_0);
//
//
//        s_0 = solution(new int[]{2, 0, -3, 0, -13, -1, -5, 12, 4, 6, 0});
//        System.out.println(s_0.equals("112320") + " - " + s_0);
//
//        s_0 = solution(new int[]{2, 0, -3, 0, -13, -1, -5, 12, 4, 6, 0});
//        System.out.println(s_0.equals("112320") + " - " + s_0);
//
//        s_0 = solution(new int[]{
//                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
//                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
//                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
//                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
//                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000
//        });
//        System.out.println(s_0.equals("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000") + " - " + s_0);
//
//        String sentence = solution(new int[]{-260, -376, 400, -987, -1000, -1000});
//        System.out.println(sentence.equals("6") + " - " + sentence);
//


        s_0 = solution(new int[]{
                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000
        });
        System.out.println(s_0.equals("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000") + " - " + s_0);
    }


}


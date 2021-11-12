import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class PowerOfTwo2 {


    private static boolean isPowerOfTwo(BigInteger n) {
        return n.and(n.subtract(BigInteger.ONE)).equals(BigInteger.ZERO);
    }

    private static CompletableFuture<Map.Entry<BigInteger, Integer>> moveUpToPowerOfTwo(BigInteger n) {
        return CompletableFuture.supplyAsync(() -> {
            BigInteger current = n;
            int opCounter = 0;
            while (!isPowerOfTwo(current)) {
                current = current.add(BigInteger.ONE);
                opCounter++;
            }
            return Map.entry(current, opCounter);
        });
    }

    private static CompletableFuture<Map.Entry<BigInteger, Integer>> moveDownToPowerOfTwo(BigInteger n) {
        return CompletableFuture.supplyAsync(() -> {
            BigInteger current = n;
            int opCounter = 0;
            while (!isPowerOfTwo(current)) {
                current = current.subtract(BigInteger.ONE);
                opCounter++;
            }
            return Map.entry(current, opCounter);
        });
    }


    public static int solution(String x) {
        if (x.equals("1")) {
            return 0;
        }

        if (x.equals("2")) {
            return 1;
        }

        CompletableFuture<Map.Entry<BigInteger, Integer>> moveUpFuture = moveUpToPowerOfTwo(new BigInteger(x));
        CompletableFuture<Map.Entry<BigInteger, Integer>> moveDownFuture = moveDownToPowerOfTwo(new BigInteger(x));
        CompletableFuture.allOf(moveUpFuture, moveDownFuture).join();

        Map.Entry<BigInteger, Integer> moveUpResult;
        Map.Entry<BigInteger, Integer> moveDownResult;
        try {
            moveUpResult = moveUpFuture.get();
            moveDownResult = moveDownFuture.get();
        } catch (Exception e) {
            return -1;
        }

        CompletableFuture<Map.Entry<BigInteger, Integer>> selectedFuture;

        if (moveUpResult.getValue() > moveDownResult.getValue()) {
            selectedFuture = moveDownFuture;
        } else if (moveUpResult.getValue() < moveDownResult.getValue()) {
            selectedFuture = moveUpFuture;
        } else {
            selectedFuture = moveUpResult.getKey().compareTo(moveDownResult.getKey()) > 0 ? moveDownFuture : moveUpFuture;
        }

        Map.Entry<BigInteger, Integer> selectedResult;
        try {
            selectedResult = selectedFuture.get();
        } catch (Exception e) {
            return -1;
        }
        BigInteger current = selectedResult.getKey();
        int opCounter = selectedResult.getValue();

        BigInteger two = new BigInteger("2");
        while (!current.equals(BigInteger.ONE)) {
            current = current.divide(two);
            opCounter++;
        }

        return opCounter;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(solution("4") + ", should be 2");
        System.out.println(solution("15") + ", should be 5");
        System.out.println(System.currentTimeMillis() - start);
    }
}
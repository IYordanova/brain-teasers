import java.math.BigInteger;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class PalletSortToOne {

    private static boolean isDivisibleByTwo(BigInteger n) {
        return n.and(BigInteger.ONE).equals(BigInteger.ZERO);
    }

    private static final BigInteger LIMIT = new BigInteger(IntStream.range(0, 309)
            .mapToObj(i -> "9")
            .collect(Collectors.joining("")));

    public static int solution(String x) {
        if (x.equals("0") || x.equals("1")) {
            return 0;
        }

        if (x.equals("2")) {
            return 1;
        }

        BigInteger two = new BigInteger("2");
        Set<Map.Entry<BigInteger, Integer>> pellets = Set.of(Map.entry(new BigInteger(x), 0));
        while (pellets.stream().noneMatch(p -> p.getKey().equals(BigInteger.ONE))) {
            pellets = pellets.stream()
                    .flatMap(e -> {
                        if (isDivisibleByTwo(e.getKey())) {
                            return Stream.of(Map.entry(e.getKey().divide(two), e.getValue() + 1));
                        } else {
                            BigInteger add = e.getKey().add(BigInteger.ONE);
                            if (add.compareTo(LIMIT) > 0) {
                                return Stream.of(Map.entry(e.getKey().subtract(BigInteger.ONE), e.getValue() + 1));
                            }
                            return Stream.of(
                                    Map.entry(add, e.getValue() + 1),
                                    Map.entry(e.getKey().subtract(BigInteger.ONE), e.getValue() + 1));
                        }
                    })
                    .collect(Collectors.toSet());
//            System.out.println(pellets);
        }

        return pellets.iterator().next().getValue();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(solution("0") + ", should be 0");
        System.out.println(solution("1") + ", should be 0");
        System.out.println(solution("4") + ", should be 2");
        System.out.println(solution("15") + ", should be 5");
        System.out.println(solution("40") + ", should be 6");
        System.out.println(solution("193") + ", should be 9");
        System.out.println(solution("4111") + ", should be 14");
        System.out.println(System.currentTimeMillis() - start);
    }
}
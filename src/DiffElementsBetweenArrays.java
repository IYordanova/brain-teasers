import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DiffElementsBetweenArrays {

    public static void main(String [] args) {
        System.out.println(withStreams(
                new int[]{14, 27, 1, 4, 2, 50, 3, 1},
                new int[]{2, 4, -4, 3, 1, 1, 14, 27, 50}));
        System.out.println(withStreams(
                new int[]{13, 5, 6, 2, 5},
                new int[]{5, 2, 5, 13}));
    }

    private static int withStreams(int[] x, int[] y) {
        List<Integer> uniqueX = Arrays.stream(x)
                .distinct()
                .boxed()
                .collect(Collectors.toList());
        List<Integer> uniqueY = Arrays.stream(y)
                .distinct()
                 .boxed()
                .collect(Collectors.toList());
        if (uniqueX.size() > uniqueY.size()) {
            uniqueX.removeAll(uniqueY);
            return uniqueX.get(0);
        } else {
            uniqueY.removeAll(uniqueX);
            return uniqueY.get(0);
        }
    }
}
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxGapBetweenConscutiveNumsInArray {

    private static int maximumGap(final List<Integer> A) {
        if(A.size() < 2) {
            return 0;
        }

        Collections.sort(A);

        int diff = Integer.MIN_VALUE;

        for (int i = 0; i < A.size() - 1; i++) {
             int j = i + 1;
             diff = Integer.max(diff, A.get(j) - A.get(i));
        }
		return diff;
    }


    public static void main(String[] args) {
        System.out.println(maximumGap(Arrays.asList(1, 10, 5)));
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RepeatedAndMissingInList {
    private static ArrayList<Integer> repeatedNumber(final List<Integer> A) {
        int arrSize = A.size();

        Collections.sort(A);

        Integer repeated = null;
        Integer missing = null;

        if(A.get(0) != 1) {
            missing = 1;
        }
        if(A.get(arrSize - 1) != arrSize) {
            missing = arrSize;
        }
        for (int i = 1; i < arrSize; i++) {
            Integer previous = A.get(i - 1);
            Integer current = A.get(i);
            if (current.equals(previous)) {
                repeated = current;
            }
            if (current - previous > 1) {
                missing = current - 1;
            }
            if (repeated != null && missing != null) {
                break;
            }
         }
        return new ArrayList<>(Arrays.asList(repeated, missing));
    }


    public static void main(String[] args) {
        System.out.println(repeatedNumber(Arrays.asList(3, 1, 2, 5, 3)));
        System.out.println(repeatedNumber(Arrays.asList(2, 2)));
        System.out.println(repeatedNumber(Arrays.asList(1, 1)));
        System.out.println(repeatedNumber(Arrays.asList(1, 1, 3)));
        System.out.println(repeatedNumber(Arrays.asList(1, 2, 3, 3, 4)));
    }


}

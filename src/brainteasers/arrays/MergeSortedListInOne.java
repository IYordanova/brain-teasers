package brainteasers.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MergeSortedListInOne {


    private static List<Integer> mergeSorted(List<Integer> A, List<Integer> B) {
        if (A.isEmpty()) {
            A.addAll(B);
            return A;
        }
        if (B.isEmpty()) {
            return A;
        }

        int j = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : A) {
            while (j < B.size()) {
                int num2 = B.get(j);
                if (num2 < num) {
                    list.add(num2);
                    j++;
                } else {
                    break;
                }
            }
            list.add(num);
        }

        if(j < B.size()) {
            for (;j < B.size(); j++) {
                list.add(B.get(j));
            }
        }
        A.clear();
        A.addAll(list);
        return A;
    }


    public static void main(String[] args) {
        System.out.println(mergeSorted(Arrays.asList(1, 3, 4, 5, 7, 8, 11), Arrays.asList(2, 6, 9, 10)));
        System.out.println(mergeSorted(Arrays.asList(3, 4, 5, 7, 8, 11), Collections.singletonList(1)));
        System.out.println(mergeSorted(Arrays.asList(1, 2, 3), Collections.singletonList(4)));
        System.out.println(mergeSorted(Arrays.asList(1, 2, 3, 4), Arrays.asList(5, 123, 456, 788)));
        System.out.println(mergeSorted(Collections.emptyList(), Arrays.asList(5, 123, 456, 788)));
        System.out.println(mergeSorted(Arrays.asList(1, 2, 3, 4), Collections.emptyList()));
        System.out.println(mergeSorted(Arrays.asList(-4, 3), Arrays.asList(-2, -2)));
    }


}

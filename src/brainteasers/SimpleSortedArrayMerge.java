package brainteasers;

import java.util.ArrayList;
import java.util.List;

public class SimpleSortedArrayMerge {

    private static class Triple<A, B, C> {
        public final A first;
        public final B second;
        public final C third;

        public Triple(A first, B second, C third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    private static Triple<Integer, Integer, Integer> pickMinAndAdvance(int [] array1, int [] array2, int pos1, int pos2) {
        int result;
        if (pos1 < array1.length && pos2 < array2.length) {
            if (array1[pos1] < array2[pos2]) {
                result = array1[pos1];
                pos1++;
            } else {
                result = array2[pos2];
                pos2++;
            }
        } else if (pos1 >= array1.length) {
            result = array2[pos2];
            pos2 += 1;
        } else {
            result = array1[pos1];
            pos1 += 1;
        }

        return new Triple(result, pos1, pos2);
    }


    private static List<Integer> merge(int[] array1, int [] array2) {
        int pos1 = 0;
        int pos2 = 0;
        List<Integer> result = new ArrayList<>();
        while (pos1 < array1.length || pos2 < array2.length) {
            Triple<Integer, Integer, Integer> triple = pickMinAndAdvance(array1, array2, pos1, pos2);
            result.add(triple.first);
            pos1 = triple.second;
            pos2 = triple.third;
        }
        return result;
    }




    public static void main(String[] args) {
        System.out.println(merge(new int[]{1, 2, 3, 4}, new int[] {2, 3, 4, 5, 6}));

    }

}

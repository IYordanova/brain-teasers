package tevalcourse.merge_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinHeapTester {

    public static void main(String[] args) {
        ArrayList<Integer> ints1  = new ArrayList<>(Arrays.asList(1, 3));
        ArrayList<Integer> ints2  = new ArrayList<>(Arrays.asList(2, 4, 5));
        ArrayList<Integer> ints3  = new ArrayList<>(Arrays.asList(2, 3, 3, 4));
        ArrayList<ArrayList<Integer>> all  = new ArrayList<>(Arrays.asList(ints1, ints2, ints3));
        System.out.println(merge(all));

        ArrayList<ArrayList<Integer>> all2  = new ArrayList<>(Arrays.asList(
                generateArrayList(50),
                generateArrayList(79),
                generateArrayList(96)
        ));
        System.out.println(merge(all2));
    }

    private static Random r = new Random(System.nanoTime());

    private static ArrayList<Integer> generateArrayList(int size) {
        return IntStream.range(0, size)
                .boxed()
                .map(num -> r.nextInt(1000))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    private static ArrayList<Integer> merge(ArrayList<ArrayList<Integer>> arrays){
        MinHeap heap = new MinHeap();
        arrays.stream()
                .flatMap(Collection::stream)
                .forEach(heap::add);
        return heap.popAll();
    }
}

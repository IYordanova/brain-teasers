package brainteasers.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicates {


    private static int removeDuplicates(List<Integer> a) {
        List<Integer> listWithoutDuplicates = a.stream()
                .distinct()
                .collect(Collectors.toList());
        a.clear();
        a.addAll(listWithoutDuplicates);
        return a.size();
    }


    public static void main(String[] args) {
        System.out.println(removeDuplicates(Arrays.asList(1, 3, 4, 4, 7, 8, 11)));
        System.out.println(removeDuplicates(Arrays.asList(3, 4, 5, 7, 8, 11)));
    }


}

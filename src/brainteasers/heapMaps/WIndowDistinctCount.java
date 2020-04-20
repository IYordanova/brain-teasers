package brainteasers.heapMaps;

import java.util.*;

public class WIndowDistinctCount {

    private static ArrayList<Integer> dNums(List<Integer> A, int B) {
        ArrayList<Integer> result = new ArrayList<>();
        int size = A.size();
        if (B > size) {
            return result;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < B; i++) {
            Integer number = A.get(i);
            map.put(number, map.getOrDefault(number, 0) + 1);
        }

        result.add(map.size());

        for (int i = B; i < size; i++) {
            Integer startWindow = A.get(i - B);
            map.put(startWindow, map.get(startWindow) - 1);
            if (map.get(startWindow) == 0) {
                map.remove(startWindow);
            }

            Integer number = A.get(i);
            map.put(number, map.getOrDefault(number, 0) + 1);

            result.add(map.size());
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(dNums(Arrays.asList(1, 2, 1, 3, 4, 3), 3));
        System.out.println(dNums(Arrays.asList(1, 2, 1, 3, 4, 3), 2));
    }

}

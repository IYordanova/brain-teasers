package brainteasers.hashing;

import java.util.*;

public class SumOfTwoNumbers {

    public static ArrayList<Integer> twoSum(final List<Integer> A, int B) {
        ArrayList<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int size = A.size();
        for (int i = 0; i < size; i++) {
            int diff = B - A.get(i);
            if (map.containsKey(diff)) {
                ans.add(map.get(diff) + 1);
                ans.add(i + 1);
                break;
            } else if (!map.containsKey(A.get(i))) {
                map.put(A.get(i), i);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(twoSum(Arrays.asList(2, 7, 11, 15), 9));
        System.out.println(twoSum(Arrays.asList(-1, 12, 1, 32), 11));
        System.out.println(twoSum(Arrays.asList(8, 34, 3, 24), 0));
    }


}

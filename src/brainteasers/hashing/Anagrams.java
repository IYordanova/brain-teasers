package brainteasers.hashing;

import java.util.*;

public class Anagrams {

    public static ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
        Map<String, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            String sSorted = getSortedString(A.get(i));
            ArrayList<Integer> temp = (!map.containsKey(sSorted)) ? new ArrayList<>() : map.get(sSorted);
            temp.add(i + 1);
            map.put(sSorted, temp);
        }
        return new ArrayList<>(map.values());
    }

    private static String getSortedString(String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        return String.valueOf(c);
    }

    public static void main(String[] args) {
        System.out.println(anagrams(Arrays.asList("cat", "dog", "god", "tca")));
    }

}

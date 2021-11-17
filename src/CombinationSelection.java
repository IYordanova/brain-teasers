import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

public class CombinationSelection {

    private static void combineHelper(int total, int targetGroup, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> temp, int start) {
        if (temp.size() > targetGroup) {
            return;
        }

        if (temp.size() == targetGroup && !ans.contains(temp)) {
            ans.add(new ArrayList<>(temp));
        }

        for (int i = start; i < total; i++) {
            if (temp.contains(i)) {
                continue;
            }
            temp.add(i);
            combineHelper(total, targetGroup, ans, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    private static ArrayList<ArrayList<Integer>> combine(int total, int targetGroup) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (total < targetGroup) {
            return ans;
        }
        ArrayList<Integer> temp = new ArrayList<>();
        combineHelper(total, targetGroup, ans, temp, 0);
        return ans;
    }

    public static int[][] solution(int num_buns, int num_required) {
        if (num_required == 1) {
            return new int[num_buns][1];
        }

        if (num_buns == num_required) {
            int[][] res = new int[num_buns][1];
            for (int i = 0; i < num_buns; i++) {
                res[i][0] = i;
            }
            return res;
        }

        ArrayList<ArrayList<Integer>> requiredKeyCombinations = combine(num_buns, num_required);
        int total = requiredKeyCombinations.size() * num_required;
        int duplicates = (num_buns - num_required) + 1;

        ArrayList<ArrayList<Integer>> duplicateKeyCombinations = duplicates == num_required
                ? requiredKeyCombinations : combine(num_buns, duplicates);
        ArrayList<ArrayList<Integer>> res = IntStream.range(0, num_buns)
                .mapToObj(i -> new ArrayList<Integer>())
                .collect(toCollection(ArrayList::new));

        for (int i = 0; i < (total / duplicates); i++) {
            for (Integer j : duplicateKeyCombinations.get(i)) {
                res.get(j).add(i);
            }
        }

        return res.stream()
                .map(x -> x.stream().mapToInt(i -> i).toArray())
                .toArray(int[][]::new);
    }


    public static void main(String[] args) {
//        System.out.println(Arrays.deepToString(solution(2, 2)) + " ==  [[0], [1]] ?");
//        System.out.println(Arrays.deepToString(solution(4, 4)) + " ==  [[0], [1], [2], [3]] ?");
//        System.out.println(Arrays.deepToString(solution(3, 1)) + " ==  [[0], [0], [0]] ?");
//        System.out.println(Arrays.deepToString(solution(2, 1)) + " ==  [[0], [0]] ?");
//        System.out.println(Arrays.deepToString(solution(3, 2)) + " ==  [[0, 1], [0, 2], [1, 2]] ?");
        System.out.println(Arrays.deepToString(solution(5, 3)) +
                " ==  [[0, 1, 2, 3, 4, 5], " +
                "[0, 1, 2, 6, 7, 8], " +
                "[0, 3, 4, 6, 7, 9], " +
                "[1, 3, 5, 6, 8, 9], " +
                "[2, 4, 5, 7, 8, 9]] ?");
    }
}

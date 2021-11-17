package brainteasers.backtracking;

import java.util.ArrayList;

public class Combinations {
    public static ArrayList<ArrayList<Integer>> combine(int A, int B) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (A < B) {
            return ans;
        }
        ArrayList<Integer> temp = new ArrayList<>();
        combineHelper(A, B, ans, temp,0);
        return ans;
    }

    private static void combineHelper(int a, int b, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> temp, int start) {
        if (temp.size() > b) {
            return;
        }

        if (temp.size() == b && !ans.contains(temp)) {
            ans.add(new ArrayList<>(temp));
        }

        for (int i=start; i<=a; i++) {
            if (temp.contains(i)) {
                continue;
            }
            temp.add(i);
            combineHelper(a, b, ans, temp, i+1);
            temp.remove(temp.size()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combine(4, 2));
        System.out.println(combine(1, 1));
        System.out.println(combine(2, 1));
        System.out.println(combine(3, 2));

        System.out.println(combine(10, 3));
        System.out.println(combine(5, 3));
    }


}

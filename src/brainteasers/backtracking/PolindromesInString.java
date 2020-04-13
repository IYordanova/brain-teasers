package brainteasers.backtracking;

import java.util.ArrayList;

public class PolindromesInString {

    public static ArrayList<ArrayList<String>> partition(String a) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        generateSubArrays(result, new ArrayList<>(), a, 0);
        return result;
    }

    private static void generateSubArrays(ArrayList<ArrayList<String>> result, ArrayList<String> temp, String a, int index) {
        if (index == a.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < a.length(); i++) {
            String sb = a.substring(index, i + 1);
            if (isPalindrome(sb)) {
                temp.add(sb);
                generateSubArrays(result, temp, a, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }

    public static void main(String[] args) {
        System.out.println(partition("aab"));
        System.out.println(partition("a"));
        System.out.println(partition("acca"));
        System.out.println(partition("efe"));
        System.out.println(partition("aaaaccccaaaa"));
    }


}

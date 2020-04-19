package brainteasers.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NumSubStrFromListInString {

    private static ArrayList<Integer> findSubstring(String A, final List<String> B) {
        ArrayList<Integer> result = new ArrayList<>();
        if (B == null || A == null || A.length() == 0 || B.size() == 0) {
            return result;
        }

        int length = A.length();
        int strLength = B.get(0).length();
        int num = B.size() * strLength;

        HashMap<String, Integer> map = new HashMap<>();
        for (String s : B) {
            map.put(s, map.containsKey(s) ? map.get(s) + 1 : 1);
        }

        for (int i = 0; i <= length - num; i++) {
            HashMap<String, Integer> tempMap = (HashMap<String, Integer>) map.clone();
            for (int j = 0; j < B.size(); j++) {
                String str = A.substring(i + j * strLength, i + (j + 1) * strLength);
                if (!tempMap.containsKey(str)) {
                    break;
                } else if (tempMap.get(str) > 1) {
                    tempMap.put(str, tempMap.get(str) - 1);
                } else if (tempMap.get(str) == 1) {
                    tempMap.remove(str);
                }
            }
            if (tempMap.isEmpty()) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("foo", "bar");
        System.out.println(findSubstring("barfoothefoobarman", words));

        List<String> words2 = Arrays.asList("aaa", "aaa", "aaa", "aaa", "aaa");
        System.out.println(findSubstring("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", words2));

        List<String> words3 = Arrays.asList("cabccbbbc", "abbccabbc", "bbbcbbbaa", "acbaabcab", "ccacabccb", "bbacaabca", "acacbaacb", "aabbcccab", "ccccbbcaa", "baaccaabc");
        System.out.println(findSubstring("acaaacbcbccbaabaccabcbbcaaccbbbbcbabaacbbcbccababb", words3));
    }

}

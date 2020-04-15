package brainteasers.hashing;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringNoDuplicates {

    public static int lengthOfLongestSubstring(String A) {
        int size = A.length();
        if (size == 1) {
            return 1;
        }
        int max = 0;
        int i = 0;
        int j = 0;
        Set<Character> chars = new HashSet<>();
        while (i < size-1) {
            char ch = A.charAt(i);
            if (!chars.add(ch)) {
                max = Math.max(max, j - i + 1);
            } else {
                j = i + 1;
                ch = A.charAt(j);
                while (!chars.contains(ch)) {
                    j++;
                    if(j == size) {
                        break;
                    }
                    chars.add(ch);
                    ch = A.charAt(j);
                }
                max = Math.max(max, j - i);
                chars.clear();
            }
            i++;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("asdfsafgthys"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("dadbc"));
    }

}

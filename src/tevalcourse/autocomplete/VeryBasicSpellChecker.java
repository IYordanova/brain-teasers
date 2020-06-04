package tevalcourse.autocomplete;

import java.util.Set;
import java.util.HashSet;

public class VeryBasicSpellChecker {

    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();


    Set<String> typos(String input) {
        Set<String> toReturn = new HashSet<>();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (char ch : ALPHABET) {
                toReturn.add(replaceChar(chars, ch, i));
            }
        }
        return toReturn;
    }

    private String replaceChar(char[] chars, char ch, int i) {
        char tmp = chars[i];
        chars[i] = ch;
        String result = new String(chars);
        chars[i] = tmp;
        return result;
    }

}

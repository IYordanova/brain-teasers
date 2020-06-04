package tevalcourse.seachautocomplete;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Map.entry;


/*
   Types of checks:
    1. additional char
        A. at the start        + (here)
        B. at the end          + (here)
        C. in the middle       ---- too much for very basic
    2. missing char
        A. at the start        + (here)
        B. at the end          + (at trie)
        C. in the middle       ---- too much for very basic
    3. swapped chars - order   + (here)
    4. swapped chars - typo    + (here)
 */
public class VeryBasicSpellChecker {

    private static final List<Character> ALPHABET = "abcdefghijklmnopqrstuvwxyz".chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());

    private static final Map<Character, List<Character>> QWERTY_TYPOS = Map.ofEntries(
            entry('q', Arrays.asList('a', 's', 'w')),
            entry('w', Arrays.asList('q', 'a', 's', 'd', 'e')),
            entry('e', Arrays.asList('w', 's', 'd', 'f', 'r')),
            entry('r', Arrays.asList('e', 'd', 'f', 'g', 't')),
            entry('t', Arrays.asList('r', 'f', 'g', 'h', 'y')),
            entry('y', Arrays.asList('t', 'g', 'h', 'j', 'u')),
            entry('u', Arrays.asList('y', 'h', 'j', 'k', 'i')),
            entry('i', Arrays.asList('u', 'j', 'k', 'l', 'o')),
            entry('o', Arrays.asList('i', 'k', 'l', 'p')),
            entry('p', Arrays.asList('o', 'l')),
            entry('a', Arrays.asList('q', 'w', 's', 'z')),
            entry('s', Arrays.asList('a', 'w', 'd', 'z', 'x')),
            entry('d', Arrays.asList('s', 'x', 'c', 'f', 'r', 'e')),
            entry('f', Arrays.asList('d', 'c', 'v', 'g', 't', 'r')),
            entry('g', Arrays.asList('f', 'v', 'b', 'h', 'y', 't')),
            entry('h', Arrays.asList('g', 'b', 'n', 'j', 'u', 'y')),
            entry('j', Arrays.asList('h', 'n', 'm', 'k', 'i', 'u')),
            entry('k', Arrays.asList('j', 'm', 'l', 'o', 'i')),
            entry('l', Arrays.asList('k', 'o', 'p')),
            entry('z', Arrays.asList('a', 's', 'x')),
            entry('x', Arrays.asList('z', 's', 'd', 'c')),
            entry('c', Arrays.asList('x', 'd', 'f', 'v')),
            entry('v', Arrays.asList('c', 'f', 'g', 'b')),
            entry('b', Arrays.asList('v', 'g', 'h', 'n')),
            entry('n', Arrays.asList('b', 'h', 'j', 'm')),
            entry('m', Arrays.asList('n', 'j', 'k'))
    );

    Set<String> missingCharAtStart(String input) {
        return ALPHABET.stream()
                .map(ch -> ch + input)
                .collect(Collectors.toSet());
    }

    Set<String> additionalCharAtStart(String input) {
        return Collections.singleton(input.substring(1));
    }

    Set<String> additionalCharAtEnd(String input) {
        return Collections.singleton(input.substring(0, input.length() - 1));
    }

    Set<String> swappedSeqChars(String input) {
        Set<String> toReturn = new HashSet<>();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            swap(chars, i);
            toReturn.add(new String(chars));
            swap(chars, i);
        }
        return toReturn;
    }

    private void swap(char[] chars, int i) {
        char tmp = chars[i];
        chars[i] = chars[i + 1];
        chars[i + 1] = tmp;
    }

    Set<String> typos(String input) {
        Set<String> toReturn = new HashSet<>();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            int finalI = i;
            toReturn.addAll(
                    QWERTY_TYPOS.get(chars[i])
                            .stream()
                            .map(ch -> replaceChar(chars, ch, finalI))
                            .collect(Collectors.toSet()));
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

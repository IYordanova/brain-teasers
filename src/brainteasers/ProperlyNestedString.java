package brainteasers;

import java.util.*;

public class ProperlyNestedString {

    private static final List<Character> opening = Arrays.asList('{', '[', '(');
    private static final List<Character> closing = Arrays.asList('}', ']', ')');
    private static final Map<Character, Character> matching = new HashMap<>();

    static {
        matching.put(')', '(');
        matching.put(']', '[');
        matching.put('}', '{');
    }

    private static int isProperlyNested(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.trim().length() == 0) {
           return 1;
        }
        Stack<Character> charStack = new Stack<>();
        for(char ch : input.toCharArray()) {
            if (opening.contains(ch)) {
                charStack.push(ch);
            } else if (closing.contains(ch)){
                if (charStack.isEmpty()) {
                   return 0;
                }
                char openingChar = charStack.pop();
                if(openingChar != matching.get(ch)) {
                    return 0;
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        System.out.println(isProperlyNested("{[()()]}"));
        System.out.println(isProperlyNested("([)()]"));
        System.out.println(isProperlyNested("([])[()]"));
    }

}
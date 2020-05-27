package brainteasers.exprEval;

import brainteasers.exprEval.operators.Operators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    private final static List<String> braces = List.of("(", ")");
    private final static String SYMBOL_PATTERN = "[^A-Za-z0-9.]";

    public final static String DECIMAL_PATTERN = "-*[0-9.]+";


    public List<Object> parseLexemes(String input) {
        List<Object> result = new ArrayList<>();
        List<Character> filteredChars = input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !Character.isWhitespace(c))
                .collect(Collectors.toList());
        int i = 0;
        while (i < filteredChars.size()) {
            StringBuilder next = new StringBuilder();
            i = parse(filteredChars, next, i);
            result.add(next.toString());
        }
        return result;
    }

    private int parse(List<Character> chars, StringBuilder value, int i) {
        if (i >= chars.size()) {
            return i;
        }
        Character c = chars.get(i);
        String key = Character.toString(c);

        if (key.matches(SYMBOL_PATTERN)) {
            String strValue = value.toString();
            if (!strValue.isEmpty() && (strValue.matches(DECIMAL_PATTERN) || Operators.isKnownOperator(strValue))) {
                return i;
            }
            if (Operators.isKnownOperator(key) || braces.contains(key)) {
                if (key.equals("-") && (i == 0 || chars.get(i - 1).equals('('))) {
                    value.append(key);
                    return parse(chars, value, i + 1); // negative numbers
                } else if (key.equals(")") && !strValue.isEmpty()) {
                    return i;
                } else {
                    value.append(key);
                    return i + 1;
                }
            } else {
                throw new RuntimeException();
            }
        } else if (Character.isDigit(c) || c == '.' || Character.isLetter(c)) {
            value.append(c);
            return parse(chars, value, i + 1);
        }
        throw new RuntimeException("a");
    }
}

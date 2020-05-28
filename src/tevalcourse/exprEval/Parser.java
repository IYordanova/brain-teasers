package tevalcourse.exprEval;

import tevalcourse.exprEval.operators.Operators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public final static String OPENING_BRACE = "(";
    public final static String CLOSING_BRACE = ")";
    public final static String DECIMAL_PATTERN = "-*[0-9.]+";

    private final static String SYMBOL_PATTERN = "[^A-Za-z0-9.]";
    private final static List<String> braces = List.of(OPENING_BRACE, CLOSING_BRACE);


    public List<Object> parseLexemes(String input) {
        List<Object> result = new ArrayList<>();
        List<Character> filteredChars = input.replaceAll(",", "\\.").chars()
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

        if (isNegativeOrBigNumber(chars, i, key) || isPotentialFpn(c) || isPotentialOpStarter(c)) {
            value.append(c);
            return parse(chars, value, i + 1);
        }
        if (isPotentialOp(c, key)) {
            String strValue = value.toString();
            if (isEndOfFpn(strValue)) {
                return i;
            }
            if (Operators.isKnownOperator(key) || braces.contains(key)) {
                 if (key.equals(CLOSING_BRACE) && !strValue.isEmpty()) {
                    return i;
                } else {
                    value.append(key);
                    return i + 1;
                }
            } else {
                throw new IllegalArgumentException("Unknown operator encountered: " + key);
            }
        }
        throw new IllegalArgumentException("Unknown case encountered: " + key);
    }

    private boolean isEndOfFpn(String strValue) {
        return !strValue.isEmpty() && (strValue.matches(DECIMAL_PATTERN) || Operators.isKnownOperator(strValue));
    }

    private boolean isPotentialOp(Character c, String key) {
        return key.matches(SYMBOL_PATTERN) || c == 'E';
    }

    private boolean isPotentialOpStarter(Character c) {
        return Character.isLetter(c);
    }

    private boolean isPotentialFpn(Character c) {
        return Character.isDigit(c) || c == '.';
    }

    private boolean isNegativeOrBigNumber(List<Character> chars, int i, String key) {
        return key.equals("-") && (
                i == 0
                || chars.get(i - 1) == '('
                || chars.get(i - 1) == 'E');
    }

}

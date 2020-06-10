package tevalcourse.expression_evaluation;

import tevalcourse.expression_evaluation.operators.Operators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public final static String DECIMAL_PATTERN = "-*[0-9.]+";


    public List<Object> parseLexemes(String input) {
        List<Object> result = new ArrayList<>();
        List<Character> filteredChars = input.replaceAll(",", "\\.")
                .chars()
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
        String strValue = value.toString();

        if (isPotentialFpn(c) || isPotentialFuncOp(c)) {
            value.append(c);
            return parse(chars, value, i + 1);
        }
        if (isEndOfOpOrArg(strValue)) {
            return i;
        }
        if (Operators.isExecutableOperator(key) || Operators.isBraceOperator(key)) {
            if (Operators.isClosingBraceOperator(key) && !strValue.isEmpty()) {
                return i;
            } else {
                value.append(key);
                return i + 1;
            }
        } else {
            throw new IllegalArgumentException("Unknown operator encountered: " + key);
        }
    }

    private boolean isEndOfOpOrArg(String strValue) {
        return !strValue.isEmpty() && (strValue.matches(DECIMAL_PATTERN) || Operators.isExecutableOperator(strValue));
    }

    private boolean isPotentialFuncOp(Character c) {
        return Character.isLetter(c);
    }

    private boolean isPotentialFpn(Character c) {
        return Character.isDigit(c) || c == '.';
    }

}

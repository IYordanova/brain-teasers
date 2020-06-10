package tevalcourse.expression_evaluation.operators;

import java.util.Map;
import java.util.Optional;

public class Operators {

    private static final String OPENING_BRACE = "(";
    private static final String CLOSING_BRACE = ")";

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";
    private static final String POW = "^";
    private static final String E = "E";

    private static final String SQRT = "sqrt";
    private static final String ABS = "abs";
    private static final String SIN = "sin";
    private static final String COS = "cos";
    private static final String TAN = "tan";
    private static final String COT = "cot";

    private final static Map<String, Operator> binaryOperators = Map.of(
            PLUS, new BinaryOperator(PLUS, 1, Double::sum),
            MINUS, new BinaryOperator(MINUS, 1, (a, b) -> a - b),
            MULTIPLY, new BinaryOperator(MULTIPLY, 2, (a, b) -> a * b),
            DIVIDE, new BinaryOperator(DIVIDE, 2, (a, b) -> (a) / b),
            POW, new BinaryOperator(POW,4, (a,b) -> {
                if(a < 0) {
                    throw new IllegalArgumentException("as per the problem description..now");
                }
                return Math.pow(a, b);
            }),
            E, new BinaryOperator(E, 4, (a, b) -> a * Math.pow(10, b))
    );

    private final static Map<String, Operator> unaryOperators = Map.of(
            MINUS, new UnaryOperator(MINUS, 3, a -> -a),
            SQRT, new UnaryOperator(SQRT, 4, Math::sqrt),
            ABS, new UnaryOperator(ABS, 4, Math::abs),
            SIN, new UnaryOperator(SIN, 4, a -> Math.sin(Math.toRadians(a))),
            COS, new UnaryOperator(COS, 4, a -> Math.cos(Math.toRadians(a))),
            TAN, new UnaryOperator(TAN, 4, a -> Math.tan(Math.toRadians(a))),
            COT, new UnaryOperator(COT, 4, a -> 1.0 / Math.tan(Math.toRadians(a)))
    );

    private final static Map<String, Operator> braceOperators = Map.of(
            OPENING_BRACE, new BraceOperator(OPENING_BRACE),
            CLOSING_BRACE, new BraceOperator(CLOSING_BRACE)
    );

    public static boolean isExecutableOperator(String op) {
        if(binaryOperators.containsKey(op) || unaryOperators.containsKey(op)) {
            return get(op).isExecutable();
        } else {
            return false;
        }
    }

    public static boolean isBraceOperator(String op) {
        return braceOperators.containsKey(op);
    }

    public static boolean isOpeningBraceOperator(String op) {
        return OPENING_BRACE.equals(op);
    }

    public static boolean isOpeningBraceOperator(Operator op) {
        return braceOperators.get(OPENING_BRACE).equals(op);
    }

    public static boolean isClosingBraceOperator(String op) {
        return CLOSING_BRACE.equals(op);
    }

    public static Operator get(String op, String prevLex) {
        if(MINUS.equals(op)) {
            if(prevLex == null
                    || MINUS.equals(prevLex)
                    || PLUS.equals(prevLex)
                    || MULTIPLY.equals(prevLex)
                    || DIVIDE.equals(prevLex)
                    || OPENING_BRACE.equals(prevLex)) {
                return unaryOperators.get(MINUS);
            }
            return binaryOperators.get(MINUS);
        }
        return get(op);
    }

    private static Operator get(String op) {
        return Optional.ofNullable(binaryOperators.get(op))
                .or(() -> Optional.ofNullable(unaryOperators.get(op)))
                .or(() -> Optional.ofNullable(braceOperators.get(op)))
                .orElseThrow(() -> new UnsupportedOperationException("Unknown operator " + op));
    }

}

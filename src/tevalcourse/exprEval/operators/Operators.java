package tevalcourse.exprEval.operators;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Operators {

    private static final String OPENING_BRACE = "(";
    private static final String CLOSING_BRACE = ")";

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private final static String MULTIPLY = "*";
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
            POW, new BinaryOperator(POW,3, Math::pow),
            E, new BinaryOperator(E, 3, (a, b) -> a * Math.pow(10, b))
    );

    private final static Map<String, Operator> unaryOperators = Map.of(
            MINUS, new UnaryOperator(MINUS, 4, a -> -a),
            SQRT, new UnaryOperator(SQRT, 3, Math::sqrt),
            ABS, new UnaryOperator(ABS, 3, Math::abs),
            SIN, new UnaryOperator(SIN, 3, a -> Math.sin(Math.toRadians(a))),
            COS, new UnaryOperator(COS, 3, a -> Math.cos(Math.toRadians(a))),
            TAN, new UnaryOperator(TAN, 3, a -> Math.tan(Math.toRadians(a))),
            COT, new UnaryOperator(COT, 3, a -> 1.0 / Math.tan(Math.toRadians(a)))
    );

    private final static Map<String, Operator> braceOperators = Map.of(
            OPENING_BRACE, new BraceOperator(OPENING_BRACE),
            CLOSING_BRACE, new BraceOperator(CLOSING_BRACE)
    );

    private final static Set<String> opNames = Stream.concat(
            binaryOperators.keySet().stream(),
            unaryOperators.keySet().stream()
    ).collect(Collectors.toSet());

    public static boolean isExecutableOperator(String op) {
        return opNames.contains(op);
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

    public static boolean isClosingBraceOperator(Operator op) {
        return braceOperators.get(CLOSING_BRACE).equals(op);
    }

    private static boolean shouldDelay(String op, String nextOp, String prevOp) {
        return ((MULTIPLY.equals(op) || DIVIDE.equals(op)) && MINUS.equals(nextOp))
                || (MINUS.equals(op) && (MULTIPLY.equals(prevOp) || DIVIDE.equals(prevOp)));
    }

    public static Operator getCorrected(String op, String nextOp, String prevOp) {
        if(shouldDelay(op, nextOp, prevOp) && MINUS.equals(op)) {
            return unaryOperators.get(MINUS);
        }
        return get(op);
    }


    public static Operator get(String op) {
        return Optional.ofNullable(binaryOperators.get(op))
                .or(() -> Optional.ofNullable(unaryOperators.get(op)))
                .or(() -> Optional.ofNullable(braceOperators.get(op)))
                .orElseThrow(() -> new UnsupportedOperationException("Unknown operator " + op));
    }

    public static boolean isBinary(Operator op) {
        return op instanceof BinaryOperator;
    }
}

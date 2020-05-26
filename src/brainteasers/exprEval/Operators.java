package brainteasers.exprEval;

import java.util.Map;

public class Operators {

    private final static Map<String, Operator> binaryOperators = Map.of(
            "+", new BinaryOperator(1, Double::sum),
            "-", new BinaryOperator(1, (a, b) -> a - b),
            "*", new BinaryOperator(2, (a, b) -> a * b),
            "/", new BinaryOperator(2, (a, b) -> (a) / b),
            "^", new BinaryOperator(3, Math::pow)
    );

    private final static Map<String, Operator> unaryOperators = Map.of(
            "abs", new UnaryOperator(4, Math::abs),
            "sqrt", new UnaryOperator(4, Math::sqrt),
            "sin", new UnaryOperator(3, a -> Math.sin(Math.toRadians(a))),
            "cos", new UnaryOperator(3, a -> Math.cos(Math.toRadians(a))),
            "tan", new UnaryOperator(3, a -> Math.tan(Math.toRadians(a))),
            "cot", new UnaryOperator(3, a -> 1.0 / Math.tan(Math.toRadians(a)))
    );

    public static boolean isKnownOperator(String op) {
        return binaryOperators.containsKey(op) || unaryOperators.containsKey(op);
    }

    public static int getPriority(String op) {
        Operator binaryOp = binaryOperators.get(op);
        return binaryOp == null ? unaryOperators.get(op).getPriority() : binaryOp.getPriority();
    }

    public static Operator get(String op) {
        Operator binaryOp = binaryOperators.get(op);
        return binaryOp == null ? unaryOperators.get(op) : binaryOp;
    }

    public static boolean isBinary(String op) {
        return binaryOperators.get(op) != null;
    }
}

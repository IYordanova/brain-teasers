package brainteasers.exprEval.operators;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Operators {

    private final static Map<String, Operator> binaryOperators = Map.of(
            "+", new BinaryOperator(1, Double::sum),
            "-", new BinaryOperator(1, (a, b) -> a - b),
            "*", new BinaryOperator(2, (a, b) -> a * b),
            "/", new BinaryOperator(2, (a, b) -> (a) / b),
            "^", new BinaryOperator(3, Math::pow),
            "E", new BinaryOperator(3, (a, b) -> a * Math.pow(10, b))
    );

    private final static Map<String, Operator> unaryOperators = Map.of(
            "sqrt", new UnaryOperator(3, Math::sqrt),
            "abs", new UnaryOperator(3, Math::abs),
            "sin", new UnaryOperator(3, a -> Math.sin(Math.toRadians(a))),
            "cos", new UnaryOperator(3, a -> Math.cos(Math.toRadians(a))),
            "tan", new UnaryOperator(3, a -> Math.tan(Math.toRadians(a))),
            "cot", new UnaryOperator(3, a -> 1.0 / Math.tan(Math.toRadians(a)))
    );

    private final static Set<String> opNames = Stream.concat(
            binaryOperators.keySet().stream(),
            unaryOperators.keySet().stream()
    ).collect(Collectors.toSet());

    public static boolean isKnownOperator(String op) {
        return opNames.contains(op);
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

package brainteasers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ExpressionEvaluation {

    private static class Operator {
        public final int priority;
        public final BiFunction<Double, Double, Double> executor;

        public Operator(int priority, BiFunction<Double, Double, Double> executor) {
            this.priority = priority;
            this.executor = executor;
        }
    }

    private final static Map<String, Operator> binaryOperators = Map.of(
            "+", new Operator(1, Double::sum),
            "-", new Operator(1, (a, b) -> a - b),
            "*", new Operator(2, (a, b) -> a * b),
            "/", new Operator(2, (a, b) -> (a) / b),
            "^", new Operator(3, Math::pow)
    );

    private final static Map<String, Operator> unaryOperators = Map.of(
            "sin", new Operator(3, (a, b) -> Math.sin(Math.toRadians(a))),
            "cos", new Operator(3, (a, b) -> Math.cos(Math.toRadians(a))),
            "tan", new Operator(3, (a, b) -> Math.tan(Math.toRadians(a))),
            "cot", new Operator(3, (a, b) -> 1.0 / Math.tan(Math.toRadians(a)))
    );


    private final static List<String> braces = List.of("(", ")");
    private final static String symbolPattern = "[^A-Za-z0-9]";
    private final static String decimalPattern = "[0-9.]+";


    private static boolean isKnownOperator(String op) {
        return binaryOperators.containsKey(op) || unaryOperators.containsKey(op);
    }

    private static int getPriority(String op) {
        Operator binaryOp = binaryOperators.get(op);
        return binaryOp == null ? unaryOperators.get(op).priority : binaryOp.priority;
    }

    private static Operator get(String op) {
        Operator binaryOp = binaryOperators.get(op);
        return binaryOp == null ? unaryOperators.get(op) : binaryOp;
    }

    private static boolean isBinary(String op) {
        return binaryOperators.get(op) != null;
    }

    private static int parse(List<Character> chars, StringBuilder value, int i) {
        if(i >= chars.size()) {
           return i;
        }
        Character c = chars.get(i);
        String key = Character.toString(c);

        if (key.matches(symbolPattern)) {
            String strValue = value.toString();
            if (!strValue.isBlank() && (strValue.matches(decimalPattern) || isKnownOperator(strValue))) {
                return i;
            }
            if (isKnownOperator(key) || braces.contains(key)) {
                value.append(key);
                return i + 1;
            } else {
                throw new RuntimeException("Cannot calculate expression, unsupported operations used");
            }
        } else if (Character.isDigit(c) || c == '.') {
            value.append(c);
            return parse(chars, value, i + 1);
        } else if (Character.isLetter(c)) {
            value.append(c);
            return parse(chars, value, i + 1);
        }
        throw new RuntimeException("a");
    }

    private static List<Object> parseLexemes(String input) {
        List<Object> result = new ArrayList<>();
        List<Character> filteredChars = input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !Character.isWhitespace(c))
                .collect(Collectors.toList());
        int i = 0;
        while(i < filteredChars.size()) {
            StringBuilder next = new StringBuilder();
            i = parse(filteredChars, next, i);
            result.add(next.toString());
        }
        return result;
    }

    private static void executeOperations(Stack<String> ops, Stack<Double> args, Supplier<Boolean> condition) {
        while (!ops.isEmpty() && condition.get()) {
            String op = ops.pop();
            Double b = args.pop();
            Double a = isBinary(op) ? args.pop() : b;
            args.push(get(op).executor.apply(a, b));
        }
    }

    private static String evaluateExpression(String input) {
        List<Object> lexemes = parseLexemes(input);

        Stack<String> ops = new Stack<>();
        Stack<Double> args = new Stack<>();

        lexemes.forEach(lex -> {
            if (lex.toString().matches(decimalPattern)) {
                args.push(Double.parseDouble(lex.toString()));
            } else if (lex.equals("(")) {
                ops.push((String) lex);
            } else if (lex.equals(")")) {
                executeOperations(ops, args, () -> !ops.peek().equals("("));
                ops.pop();
            } else if (isKnownOperator(lex.toString())) {
                int priority = getPriority(lex.toString());
                executeOperations(ops, args, () -> !ops.peek().equals("(") && getPriority(ops.peek()) >= priority);
                ops.push((String)lex);
            }
        });
        executeOperations(ops, args, () -> true);
        return args.size() == 1 ? args.pop().toString().replaceAll("\\.[0]+", "") : null;
    }


    public static void main(String[] args) {
        System.out.println(evaluateExpression("1 + 2 * (1 + 3)"));        // 9
        System.out.println(evaluateExpression("(1 + 3 * (2 + 5)) * 6"));  // 132
        System.out.println(evaluateExpression("(10+38* (12+ 5)) * 6"));   // 3936

        System.out.println(evaluateExpression("sin(90)"));  // 1
        System.out.println(evaluateExpression("sin(45)"));  // 0.7071067811865475
        System.out.println(evaluateExpression("tan(225)")); // 0.9999999999999997
        System.out.println(evaluateExpression("cot(120)")); // -0.5773502691896254

        System.out.println(evaluateExpression("2^3")); // 8

        System.out.println(evaluateExpression("2+2*2")); // 6

        System.out.println(evaluateExpression("2 * 2^3 + (12 - 7)")); // 21

        System.out.println(evaluateExpression("2 * 2^3 + (12 - 7) * cos(90)")); // 16
    }

}

package brainteasers.exprEval;

import java.util.List;
import java.util.Stack;
import java.util.function.Supplier;

public class Evaluator {

    private final Parser parser = new Parser();

    private void executeOperations(Stack<String> ops, Stack<Double> args, Supplier<Boolean> condition) {
        while (!ops.isEmpty() && condition.get()) {
            String op = ops.pop();
            Double b = args.pop();
            Double result;
            if (Operators.isBinary(op)) {
//                Double a;
//                if (op.equals("-") && (ops.peek().equals("(") || args.isEmpty())) {
//                    a = 0.0;
//                } else if(args.isEmpty()) {
//                    throw new RuntimeException("Invalid mathematical expression.");
//                } else {
//                    a = args.pop();
//                }
                if(args.isEmpty()) {
                    throw new RuntimeException("Invalid mathematical expression.");
                }
                Double a = args.pop();
                result = ((BinaryOperator)Operators.get(op)).executor.apply(a, b);
            } else {
                result = ((UnaryOperator)Operators.get(op)).executor.apply(b);
            }
            args.push(result);
        }
    }

    public String evaluateExpression(String input) {
        List<Object> lexemes = parser.parseLexemes(input);

        Stack<String> ops = new Stack<>();
        Stack<Double> args = new Stack<>();

        lexemes.forEach(lex -> {
            if (lex.toString().matches(Parser.DECIMAL_PATTERN)) {
                args.push(Double.parseDouble(lex.toString()));
            } else if (lex.equals("(")) {
                ops.push((String) lex);
            } else if (lex.equals(")")) {
                executeOperations(ops, args, () -> !ops.peek().equals("("));
                ops.pop();
            } else if (Operators.isKnownOperator(lex.toString())) {
                int priority = Operators.getPriority(lex.toString());
                executeOperations(ops, args, () -> !ops.peek().equals("(") && Operators.getPriority(ops.peek()) >= priority);
                ops.push((String)lex);
            }
        });
        executeOperations(ops, args, () -> true);
        return args.size() == 1 ? args.pop().toString().replaceAll("\\.[0]+", "") : null;
    }
}

package brainteasers.exprEval;

import brainteasers.exprEval.operators.Operators;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Stack;
import java.util.function.Supplier;

public class Evaluator {

    private final Parser parser = new Parser();
    private final DecimalFormat df = new DecimalFormat("0.00");
    private final static String ERROR_MESSAGE = "Invalid mathematical expression.";

    public String evaluate(String input) {
        try {
            List<Object> lexemes = parser.parseLexemes(input);

            Stack<String> ops = new Stack<>();
            Stack<Double> args = new Stack<>();

            lexemes.forEach(lex -> processLex(ops, args, lex));

            executeOperations(ops, args, () -> true);
            if (args.size() == 1) {
                Double result = args.pop();
                if (!result.isInfinite() && !result.isNaN()) {
                    return  df.format(result);
                }
            }
        } catch (Exception e) {
            return ERROR_MESSAGE;
        }

        return ERROR_MESSAGE;
    }

    private void executeOperations(Stack<String> ops, Stack<Double> args, Supplier<Boolean> condition) {
        while (!ops.isEmpty() && condition.get()) {
            String op = ops.pop();
            Double b = args.pop();
            Double result;
            if (Operators.isBinary(op)) {
                if (args.isEmpty()) {
                    throw new RuntimeException();
                }
                Double a = args.pop();
                result = Operators.get(op).execute(a, b);
            } else {
                result = Operators.get(op).execute(b);
            }
            args.push(result);
        }
    }

    private void processLex(Stack<String> ops, Stack<Double> args, Object lex) {
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
            ops.push((String) lex);
        }
    }
}

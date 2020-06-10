package tevalcourse.expression_evaluation;

import tevalcourse.expression_evaluation.operators.Operator;
import tevalcourse.expression_evaluation.operators.Operators;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Supplier;

public class Evaluator {

    private final Parser parser = new Parser();
    private final DecimalFormat df = new DecimalFormat("0.00");
    private final static String ERROR_MESSAGE = "Invalid mathematical expression.";


    public String evaluate(String input) {
        if (input == null || input.trim().equals("null")) {
            return ERROR_MESSAGE;
        }

        try {List<Object> lexemes = parser.parseLexemes(input);
            Double result = processLex(new Stack<>(), new Stack<>(), lexemes, null);
            if (!result.isInfinite() && !result.isNaN()) {
                return df.format(result);
            }
        } catch (UnsupportedOperationException | IllegalStateException | IllegalArgumentException e) {
            return ERROR_MESSAGE;
        }

        return ERROR_MESSAGE;
    }

    private void executeOperations(Stack<Operator> ops, Stack<Double> args, Supplier<Boolean> condition) {
        while (!ops.isEmpty() && condition.get()) {
            Operator op = ops.pop();
            if(args.isEmpty()) {
                throw new IllegalStateException("Missing arguments for operator " + op);
            }
            Double b = args.pop();
            Double result;
            if (op.isBinary()) {
                if (args.isEmpty()) {
                    throw new UnsupportedOperationException("Missing second arg for a binary operation:" + op);
                }
                Double a = args.pop();
                result = op.execute(a, b);
            } else {
                result = op.execute(b);
            }
            args.push(result);
        }
    }

    private Object head(List<Object> list) {
        return list.get(0);
    }

    private List<Object> tail(List<Object> list) {
        return list.subList(1, list.size());
    }

    private Double processLex(Stack<Operator> ops, Stack<Double> args, List<Object> lexemes, Object prevLex) {
        Object head = head(lexemes);
        String lexStr = head.toString();
        if (lexStr.matches(Parser.DECIMAL_PATTERN)) {
            args.push(Double.parseDouble(lexStr));
        } else {
            String prev = Optional.ofNullable(prevLex).map(Object::toString).orElse(null);
            if (Operators.isOpeningBraceOperator(lexStr)) {
                ops.push(Operators.get(lexStr, prev));
            } else if (Operators.isClosingBraceOperator(lexStr)) {
                executeOperations(ops, args, () -> !Operators.isOpeningBraceOperator(ops.peek()));
                if(ops.isEmpty()) {
                    throw new IllegalStateException("Invalid expression");
                }
                ops.pop();
            } else if (Operators.isExecutableOperator(lexStr)) {
                Operator op = Operators.get(lexStr, prev);
                if (!op.isUnary() || !"-".equals(lexStr) || !"-".equals(prev)) {
                    int priority = op.getPriority();
                    executeOperations(ops, args, () -> !Operators.isOpeningBraceOperator(ops.peek()) && ops.peek().getPriority() >= priority);
                }
                ops.push(op);
            }
        }
        if (lexemes.size() != 1) {
            return processLex(ops, args, tail(lexemes), head);
        } else {
            executeOperations(ops, args, () -> true);
            if (args.size() == 1) {
               return args.pop();
            }
            throw new IllegalStateException("Invalid expression");
        }
    }
}


package tevalcourse.exprEval;

import tevalcourse.exprEval.operators.Operator;
import tevalcourse.exprEval.operators.Operators;

import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Evaluator {

    private final Parser parser = new Parser();
    private final DecimalFormat df = new DecimalFormat("0.00");
    private final static String ERROR_MESSAGE = "Invalid mathematical expression.";


    public String evaluate(String input) {
        if (input == null || input.trim().equals("null")) {
            return ERROR_MESSAGE;
        }
        List<Object> lexemes;

        try {
            lexemes = parser.parseLexemes(input);
        } catch (IllegalArgumentException e) {
            return ERROR_MESSAGE;
        }

        try {
            Stack<Operator> ops = new Stack<>();
            Stack<Double> args = new Stack<>();
            IntStream.range(0, lexemes.size()).forEach(index -> processLex(
                        ops, args,
                        lexemes.get(index),
                        index == lexemes.size() - 1 ? null : lexemes.get(index + 1),
                        index == 0 ? null : lexemes.get(index - 1)));
            executeOperations(ops, args, () -> true);
            if (args.size() == 1) {
                Double result = args.pop();
                if (!result.isInfinite() && !result.isNaN()) {
                    return df.format(result);
                }
            }
        } catch (UnsupportedOperationException | EmptyStackException | IllegalArgumentException e) {
            return ERROR_MESSAGE;
        }

        return ERROR_MESSAGE;
    }

    private void executeOperations(Stack<Operator> ops, Stack<Double> args, Supplier<Boolean> condition) {
        while (!ops.isEmpty() && condition.get()) {
            Operator op = ops.pop();
            Double b = args.pop();
            Double result;
            if (Operators.isBinary(op)) {
                if (args.isEmpty()) {
                    throw new UnsupportedOperationException("Missing second arg for a binary operation.");
                }
                Double a = args.pop();
                result = op.execute(a, b);
            } else {
                result = op.execute(b);
            }
            args.push(result);
        }
    }

    private void processLex(Stack<Operator> ops, Stack<Double> args, Object lex, Object nextLex, Object prevLex) {
        String lexStr = lex.toString();
        if (lexStr.matches(Parser.DECIMAL_PATTERN)) {
            args.push(Double.parseDouble(lexStr));
        } else if (Operators.isOpeningBraceOperator(lexStr)) {
            ops.push(Operators.get(lexStr));
        } else if (Operators.isClosingBraceOperator(lexStr)) {
            executeOperations(ops, args, () -> !Operators.isOpeningBraceOperator(ops.peek()));
            ops.pop();
        } else if (Operators.isExecutableOperator(lexStr)) {
            Operator op = Operators.get(lexStr);
            Operator correctedOp = Operators.getCorrected(
                    lexStr,
                    Optional.ofNullable(nextLex).map(Object::toString).orElse(""),
                    Optional.ofNullable(prevLex).map(Object::toString).orElse(""));
            if(op.equals(correctedOp)) {
                int priority = op.getPriority();
                executeOperations(ops, args, () -> !Operators.isOpeningBraceOperator(ops.peek()) && ops.peek().getPriority() >= priority);
                ops.push(op);
            } else {
                ops.push(correctedOp);
            }
        }
    }
}

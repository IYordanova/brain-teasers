package tevalcourse.math_expression;

import java.util.function.BiFunction;


public class EvaluateMathExpression {

    enum Operator {
        PLUS("+", (a, b) -> (double) (a + b)),
        MINUS("-", (a, b) -> (double) (a - b)),
        MULTIPLY("*", (a, b) -> ((double) a) * b),
        DIVIDE("/", (a, b) -> {
            if (b == 0) {
                throw new IllegalArgumentException(String.format("Can't divide by %d", b));
            }
            return ((double) a) / b;
        });

        private final String sign;
        private final BiFunction<Integer, Integer, Double> function;

        Operator(String sign, BiFunction<Integer, Integer, Double> function) {
            this.sign = sign;
            this.function = function;
        }

        public Double apply(int a, int b) {
            return function.apply(a, b);
        }

        public static Operator of(String sign) {
            if (PLUS.sign.equals(sign)) {
                return PLUS;
            }
            if (MINUS.sign.equals(sign)) {
                return MINUS;
            }
            if (MULTIPLY.sign.equals(sign)) {
                return MULTIPLY;
            }
            if (DIVIDE.sign.equals(sign)) {
                return DIVIDE;
            }
            throw new NumberFormatException("Can't convert character to operator: +, -, /, *");
        }
    }

    private static boolean isInt(String potentialInt) {
        return potentialInt.matches("\\d+");
    }

    private static int parseNumber(String potentialNumber) {
        if (!isInt(potentialNumber)) {
            throw new NumberFormatException(String.format("Can't convert character to digit: %s", potentialNumber));
        }
        int number = Integer.parseInt(potentialNumber);
        if (number < 0) {
            throw new NumberFormatException(String.format("Can't convert character to digit: %s", potentialNumber));
        }
        return number;
    }

    /**
     * Evaluates a simple mathematical expression (2 operands and 1 operator).
     * This method only supports operations on positive integers and the following operators "+", "-", "*", "/".
     * @param expression - the expression to evaluate, the operands and operator must be split by at least 1 space.
     * @return the result of the evaluated expression
     * @throws NumberFormatException if one operand is not an integers, if one operand is a negative integers or
     * if an unsupported operator is used
     * @throws IllegalArgumentException if the expression involves a division by zero
     */
    public static double evaluateMathExpression(String expression) {
        String[] components = expression.split("\\s+");
        if (components.length != 3) {
            throw new IllegalArgumentException("Must have 3 tokens separated by spaces");
        }

        int numberA = parseNumber(components[0]);
        int numberB = parseNumber(components[2]);
        Operator operator = Operator.of(components[1]);

        return operator.apply(numberA, numberB);
    }

}

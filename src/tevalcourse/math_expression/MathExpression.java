package tevalcourse.math_expression;

import java.util.function.BiFunction;


public class MathExpression {

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

        public static Operator parse(String sign) {
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

    public static double evaluateMathExpression(String s) {
        String[] components = s.split("\\s+");
        if (components.length != 3) {
            throw new IllegalArgumentException("Must have 3 tokens separated by spaces");
        }

        int numberA1 = parseNumber(components[0]);
        int numberB1 = parseNumber(components[2]);
        Operator operator = Operator.parse(components[1]);

        return operator.apply(numberA1, numberB1);
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();
//        scanner.close();
        try {
            double result = evaluateMathExpression("123456 *  123456");
            System.out.println(result);
            result = evaluateMathExpression("123456 / 0");
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException " + e.getMessage());
        }
    }
}

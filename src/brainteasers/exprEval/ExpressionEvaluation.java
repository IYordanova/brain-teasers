package brainteasers.exprEval;

public class ExpressionEvaluation {


    public static void main(String[] args) {
        Evaluator evaluator = new Evaluator();

        // System.out.println(evaluator.evaluateExpression("9+-"));  // Exception
        // System.out.println(evaluator.evaluateExpression("9+"));  // Exception
        // System.out.println(evaluator.evaluateExpression("12£"));  // Exception

        System.out.println(evaluator.evaluateExpression("21"));
        System.out.println(evaluator.evaluateExpression("-323"));
        System.out.println(evaluator.evaluateExpression("-323 + 3"));
        System.out.println(evaluator.evaluateExpression("-323 + (-2)"));

        System.out.println(evaluator.evaluateExpression("1 + 2 * (1 + 3)"));        // 9
        System.out.println(evaluator.evaluateExpression("(1 + 3 * (2 + 5)) * 6"));  // 132
        System.out.println(evaluator.evaluateExpression("(10+38* (12+ 5)) * 6"));   // 3936

        System.out.println(evaluator.evaluateExpression("sin(90)"));  // 1
        System.out.println(evaluator.evaluateExpression("sin(45)"));  // 0.7071067811865475
        System.out.println(evaluator.evaluateExpression("tan(225)")); // 0.9999999999999997
        System.out.println(evaluator.evaluateExpression("cot(120)")); // -0.5773502691896254

        System.out.println(evaluator.evaluateExpression("2^3")); // 8

        System.out.println(evaluator.evaluateExpression("2+2*2")); // 6

        System.out.println(evaluator.evaluateExpression("2 * 2^3 + (12 - 7)")); // 21

        System.out.println(evaluator.evaluateExpression("2 * 2^3 + (12 - 7) * cos(90)")); // 16

        System.out.println(evaluator.evaluateExpression("2 * sqrt(9) + 19")); // 25

        System.out.println(evaluator.evaluateExpression("9 + abs(-11) * 2 + (1 + 2) * 3")); // 40

        System.out.println(evaluator.evaluateExpression("9 + abs(-11) * 2 + (1 + (-2)) * 3")); // 28
    }

}

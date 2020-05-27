package brainteasers.exprEval;

public class ExpressionEvaluation {


    public static void main(String[] args) {
        Evaluator evaluator = new Evaluator();

//        System.out.println(evaluator.evaluate(null));
//        System.out.println(evaluator.evaluate("-"));
//        System.out.println(evaluator.evaluate("djkfsdf"));
//        System.out.println(evaluator.evaluate("(+2) + 3"));
//        System.out.println(evaluator.evaluate("9+-"));
//        System.out.println(evaluator.evaluate("9+"));
//        System.out.println(evaluator.evaluate("12£"));
//        System.out.println(evaluator.evaluate("12%2"));
//        System.out.println(evaluator.evaluate("1/0"));
//        System.out.println(evaluator.evaluate("sqrt(-1)"));
//        System.out.println(evaluator.evaluate("(12 +3)* 2 - 6(19 -2)"));
//        System.out.println(evaluator.evaluate("(12 +3)* 2 - 6*(19 -2"));
//        System.out.println(evaluator.evaluate("(12 +3)* 2 - 6*19 -2)"));
//
//        System.out.println(evaluator.evaluate("21"));
//        System.out.println(evaluator.evaluate("-323"));
//        System.out.println(evaluator.evaluate("-323 + 3"));
//        System.out.println(evaluator.evaluate("-323 + (-2)"));
//
//        System.out.println(evaluator.evaluate("1 + 2 * (1 + 3)"));        // 9
//        System.out.println(evaluator.evaluate("(1 + 3 * (2 + 5)) * 6"));  // 132
//        System.out.println(evaluator.evaluate("(10+38* (12+ 5)) * 6"));   // 3936
//
//        System.out.println(evaluator.evaluate("sin(90)"));  // 1
//        System.out.println(evaluator.evaluate("sin(45)"));  // 0.7071067811865475
//        System.out.println(evaluator.evaluate("tan(225)")); // 0.9999999999999997
//        System.out.println(evaluator.evaluate("cot(120)")); // -0.5773502691896254
//
//        System.out.println(evaluator.evaluate("2^3")); // 8
//        System.out.println(evaluator.evaluate("2+2*2")); // 6
//
//        System.out.println(evaluator.evaluate("2 * 2^3 + (12 - 7)")); // 21
//        System.out.println(evaluator.evaluate("2 * 2^3 + (12 - 7) * cos(90)")); // 16
//
//        System.out.println(evaluator.evaluate("((2 + 2^3*(2-(-1)))*2 + 2)")); // 54
//        System.out.println(evaluator.evaluate("((2 + 2^3*(2-1))*2 + 2) + (sin(45)-abs(-2)*3)")); // 16.71
//
//        System.out.println(evaluator.evaluate("2 * sqrt(9) + 19")); // 25
//
//        System.out.println(evaluator.evaluate("9 + abs(-11) * 2 + (1 + 2) * 3")); // 40
//        System.out.println(evaluator.evaluate("9 + abs(-11) * 2 + (1 + (-2)) * 3")); // 28
//
//        System.out.println(evaluator.evaluate("(-2)^3"));
//        System.out.println(evaluator.evaluate("(2)^(-3)"));

//        System.out.println(evaluator.evaluate("sqrt(abs(-146 + 2))"));
//        System.out.println(evaluator.evaluate("sqrt(cos(120))"));

        System.out.println(evaluator.evaluate("abs(2.2-(3*2))"));
    }

}

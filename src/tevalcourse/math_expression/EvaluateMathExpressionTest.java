package tevalcourse.math_expression;


public class EvaluateMathExpressionTest {

    public static void main(String[] args) {
        testMissingSpaces("2*3");
        testMissingSpaces("2 *3");
        testMissingSpaces("2* 3");

        testAdditionalSpaces("2 *   3", 6);
        testAdditionalSpaces("10    * 13", 130);
        testAdditionalSpaces("22    *      3", 66);

        testInvalidNumber("2.2 * 3", "2.2");
        testInvalidNumber("2 * 3.3", "3.3");
        testInvalidNumber("2. * 3", "2.");
        testInvalidNumber("2 * 3.", "3.");

        testInvalidNumber("a * 3", "a");
        testInvalidNumber("2 * b", "b");

        testInvalidNumber("-2 * 3", "-2");
        testInvalidNumber("2 * -3", "-3");

        testInvalidOperator("13 % 4");
        testInvalidOperator("13 ^ 4");

        testDivideByZero("10 / 0");

        testExpression("2 + 2341", 2343);
        testExpression("216 - 3", 213);
        testExpression("21 * 3", 63);
        testExpression("21 / 3", 7);
    }

    private static void testMissingSpaces(String expression) {
        boolean exceptionThrown = false;
        try {
           EvaluateMathExpression.evaluateMathExpression(expression);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
            assert e.getMessage().equals("Must have 3 tokens separated by spaces");
        }
        assert exceptionThrown;
    }

    private static void testAdditionalSpaces(String expression, double expectedResult) {
        testExpression(expression, expectedResult);
    }

    private static void testInvalidNumber(String expression, String invalidNumber) {
        boolean exceptionThrown = false;
        try {
            EvaluateMathExpression.evaluateMathExpression(expression);
        } catch (NumberFormatException e) {
            exceptionThrown = true;
            assert e.getMessage().equals(String.format("Can't convert character to digit: %s", invalidNumber));
        }
        assert exceptionThrown;
    }

    private static void testInvalidOperator(String expression) {
        boolean exceptionThrown = false;
        try {
            EvaluateMathExpression.evaluateMathExpression(expression);
        } catch (NumberFormatException e) {
            exceptionThrown = true;
            assert e.getMessage().equals("Can't convert character to operator: +, -, /, *");
        }
        assert exceptionThrown;
    }

    private static void testDivideByZero(String expression) {
        boolean exceptionThrown = false;
        try {
            EvaluateMathExpression.evaluateMathExpression(expression);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
            assert e.getMessage().equals("Can't divide by 0");
        }
        assert exceptionThrown;
    }

    private static void testExpression(String expression, double expectedResult) {
        double result = EvaluateMathExpression.evaluateMathExpression(expression);
        assert result == expectedResult;
    }
}
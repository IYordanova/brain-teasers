package tevalcourse.exprEval;

import java.util.HashMap;
import java.util.Map;

public class ExpressionEvaluation {


    public static void main(String[] args) {
        Evaluator evaluator = new Evaluator();
        String errorMessage = "Invalid mathematical expression.";

        Map<String, String> tests = new HashMap<>() {{
            put(null, errorMessage);
            put("-", errorMessage);
            put("djkfsdf", errorMessage);
            put("(+2) + 3", errorMessage);
            put("9+-", errorMessage);
            put("9+", errorMessage);
            put("12£", errorMessage);
            put("12%2", errorMessage);
            put("1/0", errorMessage);
            put("sqrt(-1)", errorMessage);
            put("(12 +3)* 2 - 6(19 -2)", errorMessage);
            put("(12 +3)* 2 - 6*(19 -2", errorMessage);
            put("(12 +3)* 2 - 6*19 -2)", errorMessage);
            put("sqrt(cos(120))", errorMessage);
            put("12-(2+3(98 - (7+7))", errorMessage);
            put("12-", errorMessage);
            put("12--1", errorMessage);
            put("12-(2+3))", errorMessage);
            put("12-(2+(3", errorMessage);
            put("12-2+3))", errorMessage);
            put("12-2,00", "10.00");

//            put(Double.MAX_VALUE + " + 1.01", "1.01");
//            put(Double.MIN_VALUE + " - 1.01", "-1.01");
//            put(Float.MAX_VALUE + " + 1.01", "1.01");
//            put(Float.MIN_VALUE + " - 1.01", "-1.01");
//
            put("21", "21.00");
            put("-323.923", "-323.92");
            put("-323 + (-2)", "-325.00");
            put("1 + 2 * (1 + 3)", "9.00");
            put("(1 + 3.0 * (2 + 5)) * 6", "132.00");
            put("(10+38* (12+ 5)) * 6.00", "3936.00");

            put("sin(90)", "1.00");
            put("sin(45)", "0.71");
            put("tan(225)", "1.00");
            put("cot(120)", "-0.58");

            put("2^3", "8.00");
            put("2.23^3", "11.09");
            put("2^3.45", "10.93");
            put("2+2*2", "6.00");
            put("(-2)^3", "-8.00");

            put("2 * 2^3 + (12 - 7)", "21.00");
            put("2 * 2^3 + (12.23 - 7.23) * cos(90)", "16.00");

            put("((2 + 2^3*(2.560325-(-1.56)))*2 + 2)", "71.93");
            put("((2 + 2^3*(2-1))*2 + 2) + (sin(45)-abs(-2)*3)", "16.71");

            put("2 * sqrt(9) + 19", "25.00");
            put("9 + abs(-11) * 2 + (1.2 + 2.562341) * 3", "42.29");
            put("(2)^(-3)", "0.12");
            put("9 + abs(-11) * 2 + (1 + (-2)) * 3", "28.00");
            put("sqrt(abs(-146 + 2))", "12.00");
            put("abs(2.2-(3*2))", "3.80");

            put("-12.324 + 123414.4442*1+(23.5-23.112532141421*(213.2 +23123.44214))", "-415943.27");
            put("12-(2+3)()", "7.00"); // ????
            put("1/-(2/3)", "-1.50");
            put("1/-(2/3+((12-8)+ (12-21)*2))", "0.07");
            put("1*-(2/3.123+((12-8.213)+ (12.01-21.23322)*2.12))", "15.13");
        }};


        tests.forEach((key, value) -> {
            String actual = evaluator.evaluate(key);
            if (!actual.equals(value)) {
                System.out.println(String.format("Expected %s, got %s for %s",
                        value, actual, key));
            }
        });
    }

}

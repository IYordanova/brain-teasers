package brainteasers.exprEval;

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
            put(Double.MAX_VALUE + " + 1.01", errorMessage);

            put("21", "21.00");
            put("-323", "-323.00");
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
            put("sqrt(cos(120))", "Invalid mathematical expression");
            put("abs(2.2-(3*2))", "3.80");

            put("-12.324 + 123414.4442*1+(23.5-23.112532141421*(213.2 +23123.44214))", "-415943.27");
        }};


        tests.entrySet().stream()
            .filter(entry -> {
                String actual = evaluator.evaluate(entry.getKey());
                if (!actual.equals(entry.getValue())) {
                    System.out.println(String.format("Expected %s, but got %s for expression %s",
                            entry.getValue(), actual, entry.getKey()));
                    return false;
                }
                return true;
            })
            .findFirst()
            .ifPresent(s -> System.out.println("Success!"));
    }

}

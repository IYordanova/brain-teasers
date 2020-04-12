package brainteasers.stacksandqueues;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class EvaluateExpression {

    private static int evalRPN(List<String> A) {
        Stack<Integer> st = new Stack<>();
        for (String s : A) {
            if ("*/-+".contains(s)) {
                int a = st.pop();
                int b = st.pop();
                switch (s) {
                    case "+":
                        st.push(b + a);
                        break;
                    case "-":
                        st.push(b - a);
                        break;
                    case "*":
                        st.push(b * a);
                        break;
                    default:
                        st.push(b / a);
                        break;
                }
            } else {
                st.push(Integer.parseInt(s));
            }
        }

        return st.peek();
    }


    public static void main(String[] args) {
        System.out.println(evalRPN(Arrays.asList("2", "1", "+", "3", "*")));
        System.out.println(evalRPN(Arrays.asList("4", "13", "5", "/", "+")));
    }


}

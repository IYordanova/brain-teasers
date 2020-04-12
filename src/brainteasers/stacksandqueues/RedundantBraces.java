package brainteasers.stacksandqueues;

import java.util.Stack;

public class RedundantBraces {

    private static int braces(String A) {
        Stack<Character> stack = new Stack<>();
        for (char c : A.toCharArray()) {
            if (c == ')') {
                char top = stack.peek();
                stack.pop();

                if (top == '(') { // nothing in the braces
                    return 1;
                } else {
                    int count = 0;
                    while (top != '(') {
                        top = stack.peek();
                        stack.pop();
                        count++;
                    }
                    if (count == 1) { // one char in the braces
                        return 1;
                    }
                }
            } else {
                stack.add(c);
            }
        }

        return 0;
    }


    public static void main(String[] args) {
        System.out.println(braces("((a + b))"));
        System.out.println(braces("(a + (a + b))"));
    }

}

package brainteasers.stacksandqueues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PrevSmallerInList {

    static ArrayList<Integer> prevSmaller(List<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (Integer a : A) {
            while (!stack.isEmpty() && stack.peek() >= a) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans.add(-1);
            } else {
                ans.add(stack.peek());
            }

            stack.push(a);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(prevSmaller(Arrays.asList(4, 5, 2, 10, 8)));
        System.out.println(prevSmaller(Arrays.asList(3, 2, 1)));
    }


}

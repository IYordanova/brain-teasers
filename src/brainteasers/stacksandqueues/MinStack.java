package brainteasers.stacksandqueues;

import java.util.Stack;

public class MinStack {

    static class CustomStack {
        private Stack<Integer> minStack;
        private Stack<Integer> stack;

        public CustomStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty() || x <= minStack.peek()) {
                minStack.push(x);
            }
        }

        public void pop() {
            if (!stack.isEmpty()) {
                int num = stack.pop();
                if (num == minStack.peek()) {
                    minStack.pop();
                }
            }
        }

        public int top() {
            if (stack.isEmpty()) {
                return -1;
            }
            return stack.peek();
        }

        public int getMin() {
            if (minStack.isEmpty()) {
                return -1;
            }
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        CustomStack customStack = new CustomStack();
        customStack.push(10);
        customStack.push(9);
        System.out.println(customStack.getMin());
        customStack.push(8);
        System.out.println(customStack.getMin());
        customStack.push(7);
        System.out.println(customStack.getMin());
        customStack.push(6);
        System.out.println(customStack.getMin());

        customStack.pop();
        System.out.println(customStack.getMin());
        customStack.pop();
        System.out.println(customStack.getMin());
        customStack.pop();
        System.out.println(customStack.getMin());
        customStack.pop();
        System.out.println(customStack.getMin());
        customStack.pop();
        System.out.println(customStack.getMin());
    }


}

package brainteasers.exprEval;

import java.util.function.BiFunction;

public class BinaryOperator implements Operator {
    private final int priority;
    public final BiFunction<Double, Double, Double> executor;

    public BinaryOperator(int priority, BiFunction<Double, Double, Double> executor) {
        this.priority = priority;
        this.executor = executor;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}

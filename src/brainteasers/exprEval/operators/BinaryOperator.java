package brainteasers.exprEval.operators;

import java.util.function.BiFunction;

public class BinaryOperator implements Operator {
    private final int priority;
    private final BiFunction<Double, Double, Double> executor;

    BinaryOperator(int priority, BiFunction<Double, Double, Double> executor) {
        this.priority = priority;
        this.executor = executor;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Double execute(Double a, Double b) {
        return executor.apply(a, b);
    }
}

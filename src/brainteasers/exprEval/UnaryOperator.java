package brainteasers.exprEval;

import java.util.function.Function;

public class UnaryOperator implements Operator {
    private final int priority;
    public final Function<Double, Double> executor;

    public UnaryOperator(int priority, Function<Double, Double> executor) {
        this.priority = priority;
        this.executor = executor;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}

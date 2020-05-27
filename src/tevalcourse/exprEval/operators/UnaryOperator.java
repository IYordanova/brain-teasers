package tevalcourse.exprEval.operators;

import java.util.function.Function;

public class UnaryOperator implements Operator {
    private final int priority;
    private final Function<Double, Double> executor;

    UnaryOperator(int priority, Function<Double, Double> executor) {
        this.priority = priority;
        this.executor = executor;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Double execute(Double a){
        return executor.apply(a);
    }
}

package tevalcourse.exprEval.operators;

import java.util.Objects;
import java.util.function.BiFunction;

public class BinaryOperator implements Operator {
    private final String name;
    private final int priority;
    private final BiFunction<Double, Double, Double> executor;

    BinaryOperator(String name, int priority, BiFunction<Double, Double, Double> executor) {
        this.name = name;
        this.priority = priority;
        this.executor = executor;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public boolean isUnary() {
        return false;
    }

    @Override
    public boolean isBinary() {
        return true;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Double execute(Double... a) {
        if (a.length < 2) {
            throw new IllegalArgumentException("Wrong number of arguments for operator " + name);
        }
        return executor.apply(a[0], a[1]);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryOperator that = (BinaryOperator) o;
        return priority == that.priority &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, priority);
    }
}

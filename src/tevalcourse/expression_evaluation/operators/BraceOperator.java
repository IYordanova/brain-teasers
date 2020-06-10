package tevalcourse.expression_evaluation.operators;

import java.util.Objects;

public class BraceOperator implements Operator {
    private final String name;

    BraceOperator(String name) {
        this.name = name;
    }

    @Override
    public boolean isExecutable() {
        return false;
    }

    @Override
    public boolean isUnary() {
        return false;
    }

    @Override
    public boolean isBinary() {
        return false;
    }

    @Override
    public int getPriority() {
        return -1;
    }

    @Override
    public Double execute(Double... a) {
        throw new IllegalArgumentException("Non-executable operator " + name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BraceOperator that = (BraceOperator) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

package tevalcourse.expression_evaluation.operators;

public interface Operator {

    boolean isExecutable();

    boolean isUnary();

    boolean isBinary();

    int getPriority();

    Double execute(Double... a);

}

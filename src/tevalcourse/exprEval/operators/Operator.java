package tevalcourse.exprEval.operators;

public interface Operator {

    boolean isExecutable();

    int getPriority();

    Double execute(Double... a);

}

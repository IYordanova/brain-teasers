package tevalcourse.exprEval.operators;

public interface Operator {

    int getPriority();

    default Double execute(Double a){
        throw new RuntimeException("Incorrect number of arguments");
    }

    default Double execute(Double a, Double b) {
        throw new RuntimeException("Incorrect number of arguments");
    }

}

package brainteasers.math;

public class SqrtFormulaImplNoLib {

    private static int sqrt(int A) {
        if (A == 0 || A == 1) {
            return A;
        }
        double t;
        double squareRoot = A / 2;
        do {
            t = squareRoot;
            squareRoot = (t + (A / t)) / 2;
        } while (t != squareRoot);

        return (int) squareRoot;
    }


    public static void main(String[] args) {
        System.out.println(sqrt(8));
        System.out.println(sqrt(11));
        System.out.println(sqrt(9));
        System.out.println(sqrt(0));
    }


}

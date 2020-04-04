package brainteasers.math;

public class GreatestCommonDivisor {

    private static int findGCD(int A, int B) {
        if(B == 0){
            return A;
        }
        return findGCD(B, A%B);
    }


    public static void main(String[] args) {
        System.out.println(findGCD(6, 9));
        System.out.println(findGCD(55, 121));
        System.out.println(findGCD(2, 0));
        System.out.println(findGCD(4, 4));
    }
}

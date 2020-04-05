package brainteasers.math;

public class PowImplementation {


    private static int pow(int x, int n, int d) {
        if(d == 0) {
            return 0;
        }

        long ans = 1;
        if (x == 0 && n == 0) {
            return d != 1 ? 1 :0;
        }
        if (x == 0) {
            return 0;
        }

        long a = x;
        int b = n;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = (ans * a);
            }
            if (ans < 0) {
                ans = d - (-1 * x) % d;
            } else {
                ans = ans % d;
            }
            b = b >> 1;
            a = (a * a) % d;
        }

        return (int) ans;
    }


    public static void main(String[] args) {
        System.out.println(pow(2, 3, 3));
        System.out.println(pow(2, 3, 1));
        System.out.println(pow(2, 3, 0));
        System.out.println(pow(2, 3, 9));
        System.out.println(pow(-1, 1, 20));
        System.out.println(pow(71045970, 41535484, 64735492));
    }


}

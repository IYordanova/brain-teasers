package tevalcourse.second_largest;

import java.util.Scanner;

public class SecondLargest {
    public static void secondLargest(int[] lst) {
        if (lst.length < 2) {
            throw new IllegalArgumentException("Input list size must be at least 2.");
        }
        int n1 = lst[0];
        int n2 = 0;
        for (int n : lst) {
            if (n >= n1) {
                n2 = n1;
                n1 = n;
            } else {
                if (n > n2) {
                    n2 = n;
                }
            }
        }
        System.out.println(n2);
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int[] num = null;
        if (n > 0)
            num = new int[n];

        int i = 0;

        while (i < n)
            num[i++] = cin.nextInt();

        cin.close();

        secondLargest(num);
    }
}

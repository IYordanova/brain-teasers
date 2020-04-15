package brainteasers.bitmanipulation;

import java.util.Collections;

public class ReverseBits {

    public static long reverse(long a) {
        StringBuilder sb = new StringBuilder(Long.toBinaryString(a));
        sb.insert(0, String.join("", Collections.nCopies(32 - sb.length(), "0")));
        String s = sb.reverse().toString();
        return Long.parseLong(s, 2);
    }

    public static void main(String[] args) {
        System.out.println(reverse(0));
        System.out.println(reverse(3));
        System.out.println(reverse(2));
    }


}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CycleInIdGeneration {

    private static String sortAsc(String n) {
        char[] nDigits = n.toCharArray();
        Arrays.sort(nDigits);
        return new String(nDigits);
    }

    private static String sortDesc(String n) {
        return new StringBuilder(sortAsc(n))
                .reverse()
                .toString();
    }

    private static int generate(String n, int b, List<String> acc) {
        int x = Integer.parseInt(sortDesc(n), b);
        int y = Integer.parseInt(sortAsc(n), b);
        StringBuilder z = new StringBuilder(Integer.toString(x - y, b));

        while (z.length() < n.length()) {
            z.insert(0, "0");
        }

        String newN = z.toString();
        int indexOfNewN = acc.indexOf(newN);
        if (indexOfNewN > -1) {
            System.out.println(acc);
            return acc.size() - indexOfNewN;
        }

        acc.add(newN);
        return generate(newN, b, acc);
    }

    private static int solve(String n, int b) {
        ArrayList<String> acc = new ArrayList<>();
        acc.add(n);
        return generate(n, b, acc);
    }

    public static void main(String[] args) {
        System.out.println(solve("1211", 10));
        System.out.println(solve("210022", 3));
    }

}
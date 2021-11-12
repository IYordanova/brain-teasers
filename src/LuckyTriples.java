public class LuckyTriples {

    private static boolean isDivisibleBy(int a, int b) {
        return a % b == 0;
    }

    public static int solution(int[] l) {
        int length = l.length;
        if (length <= 2) {
            return 0;
        }
        int numTriples = 0;
        for (int i = 0; i < length; i++) {
            int x = l[i];
            for (int j = i + 1; j < length; j++) {
                int y = l[j];
                if (!isDivisibleBy(y, x)) {
                    continue;
                }
                for (int k = j + 1; k < length; k++) {
                    int z = l[k];
                    if (isDivisibleBy(z, y)) {
                        numTriples++;
//                        System.out.println(l[i] + " " + y + " " + z);
                    }
                }
            }
        }
        return numTriples;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 4, 5, 6}) + " ==  3");
        System.out.println(solution(new int[]{1, 1, 1}) + " == 1 ?");
    }
}

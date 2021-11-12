import java.util.stream.Stream;

public class GateChecksum {

    public static void main(String[] args) {
        System.out.println(Stream.of(17,18,19,20)
                .reduce((a, b) -> a ^ b)
                .get());

        System.out.println(Stream.of(17,18,19,20,0)
                .reduce((a, b) -> a ^ b)
                .get());
        System.out.println(solve(0, 3));
        System.out.println(solve(17, 4));

        // one worker only
        System.out.println(solve(61, 1));
        // up to the limit
        System.out.println(solve(1990000000, 20));
    }


    private static int solve(int start, int length) {
        if (length == 1) {
            return start;
        }
        return 1;// getSelectedWorkerIds(start, length);
    }
}
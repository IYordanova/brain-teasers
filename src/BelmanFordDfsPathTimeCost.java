import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;


public class BelmanFordDfsPathTimeCost {
    private static ArrayList<Integer> bunnyIds;
    private static boolean[] visited;
    private static int[][] timeCosts;

    private static void dfs(int current, int timeCost, ArrayList<Integer> currentBunnyIds) {
        int n = timeCosts.length;
        if ((current == n - 1 && timeCost < 0) || (bunnyIds.size() == n - 2)) {
            return;
        }
        if (timeCost >= 0 && current == n - 1) {
            if (currentBunnyIds.size() > bunnyIds.size()) {
                bunnyIds = new ArrayList<>(currentBunnyIds);
            }
            return;
        }
        if (visited[current]) {
            return;
        }
        visited[current] = true;
        currentBunnyIds.add(current - 1);
        for (int v = 1; v < n; ++v) {
            if (v == current) {
                continue;
            }
            dfs(v, timeCost - timeCosts[current][v], currentBunnyIds);
        }
        currentBunnyIds.remove(currentBunnyIds.size() - 1);
        visited[current] = false;
    }

    private static boolean hasNegativeCycle(int[][] times, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (times[i][k] + times[k][j] < times[i][j]) {
                        times[i][j] = times[i][k] + times[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (times[i][i] < 0) {
                return true;
            }
        }
        return false;
    }

    public static int[] solution(int[][] times, int times_limit) {
        int n = times.length;
        if (n <= 2 || (n != times[0].length)) {
            return new int[]{};
        }
        if (hasNegativeCycle(times, n)) {
            return IntStream.range(0, n - 2).boxed().mapToInt(i -> i).toArray();
        }

        timeCosts = times;
        bunnyIds = new ArrayList<>();
        visited = new boolean[n];
        visited[0] = true;

        for (int i = 1; i < n - 1; i++) {
            dfs(i, times_limit - times[0][i], new ArrayList<>());
        }
        return bunnyIds.stream().mapToInt(i->i).sorted().toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[][]{
                {0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 0}}, 3)) + " ==  [0, 1] ?");

        System.out.println(Arrays.toString(solution(new int[][]{
                {0, 2, 2, 2, -1},
                {9, 0, 2, 2, -1},
                {9, 3, 0, 2, -1},
                {9, 3, 2, 0, -1},
                {9, 3, 2, 2, 0}}, 1)) + " == [1, 2] ?");
    }
}
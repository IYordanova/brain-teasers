import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class NebulaCellAutomata {

    private static final Map<State, Integer> mapTrue = Map.of(
            new State("10", "00", "10", "00"), 1,
            new State("00", "10", "01", "00"), 1,
            new State("01", "00", "00", "10"), 1,
            new State("00", "01", "00", "01"), 1
    );
    private static final Map<State, Integer> mapFalse = new HashMap<>() {{
        // 0 ones
        put(new State("00", "00", "00", "00"), 1);
        // 2 ones per quadrant
        put(new State("11", "00", "10", "10"), 1);
        put(new State("00", "11", "01", "01"), 1);
        put(new State("10", "10", "11", "00"), 1);
        put(new State("01", "01", "00", "11"), 1);
        put(new State("10", "01", "10", "01"), 1);
        put(new State("01", "10", "01", "10"), 1);
        // 3 ones per quadrant
        put(new State("01", "11", "01", "11"), 1);
        put(new State("10", "11", "11", "01"), 1);
        put(new State("11", "10", "11", "10"), 1);
        put(new State("11", "01", "10", "11"), 1);
        // 4 ones
        put(new State("11", "11", "11", "11"), 1);
    }};

    private static class State {
        final String left;
        final String right;
        final String up;
        final String down;

        public State(String left, String right, String up, String down) {
            this.left = left;
            this.right = right;
            this.up = up;
            this.down = down;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Objects.equals(left, state.left)
                    && Objects.equals(right, state.right)
                    && Objects.equals(up, state.up)
                    && Objects.equals(down, state.down);
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right, up, down);
        }
    }

    private static Map<State, Integer> mergeMaps(
            Map<State, Integer> map1,
            Map<State, Integer> map2,
            boolean mergeRows,
            boolean noLeft,
            boolean noRight,
            boolean noUp,
            boolean noDown
    ) {
        BiPredicate<State, State> filter = mergeRows ? (a, b) -> a.down.equals(b.up) : (a, b) -> a.right.equals(b.left);
        BiFunction<State, State, State> supplier = mergeRows ? (a, b) -> new State(
                noLeft ? "" : a.left + b.left.substring(1),
                noRight ? "" : a.right + b.right.substring(1),
                noUp ? "" : a.up,
                noDown ? "" : b.down
        ) : (a, b) -> new State(
                noLeft ? "" : a.left,
                noRight ? "" : b.right,
                noUp ? "" : a.up + b.up.substring(1),
                noDown ? "" : a.down + b.down.substring(1)
        );
        return map1.keySet()
                .stream()
                .flatMap(a -> map2.keySet()
                        .stream()
                        .filter(b -> filter.test(a, b))
                        .map(b -> Map.entry(supplier.apply(a, b), map1.get(a) * map2.get(b)))
                ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
    }

    private static Map<State, Integer> getPossibleStates(boolean[][] state, int rowStart, int rowEnd, int colStart, int colEnd) {
        if (rowEnd > rowStart || colEnd > colStart) {
            boolean mergeRows = (rowEnd - rowStart > colEnd - colStart);
            int rowHalf = (rowEnd + rowStart) / 2;
            int colHalf = (colStart + colEnd) / 2;
            Map<State, Integer> map1;
            Map<State, Integer> map2;
            if (mergeRows) {
                map1 = getPossibleStates(state, rowStart, rowHalf, colStart, colEnd);
                map2 = getPossibleStates(state, rowHalf + 1, rowEnd, colStart, colEnd);
            } else {
                map1 = getPossibleStates(state, rowStart, rowEnd, colStart, colHalf);
                map2 = getPossibleStates(state, rowStart, rowEnd, colHalf + 1, colEnd);
            }
            return mergeMaps(
                    map1,
                    map2,
                    mergeRows,
                    colStart == 0,
                    colEnd == state[0].length - 1,
                    rowStart == 0,
                    rowEnd == state.length - 1);
        }
        if (state[rowStart][colStart]) {
            return mapTrue;
        }
        return mapFalse;
    }

    public static int solution(boolean[][] g) {
        return getPossibleStates(g, 0, g.length - 1, 0, g[0].length - 1)
                .values()
                .stream()
                .reduce(0, Integer::sum);
    }


    public static void main(String[] args) {
        System.out.println(solution(new boolean[][]{
                {true, false, true},
                {false, true, false},
                {true, false, true}
        }) + " == 4 ?");
        System.out.println(solution(new boolean[][]{
                {true, true, false, true, false, true, false, true, true, false},
                {true, true, false, false, false, false, true, true, true, false},
                {true, true, false, false, false, false, false, false, false, true},
                {false, true, false, false, false, false, true, true, false, false}
        }) + " == 11567 ?");
        System.out.println(solution(new boolean[][]{
                {true, false, true, false, false, true, true, true},
                {true, false, true, false, false, false, true, false},
                {true, true, true, false, false, false, true, false},
                {true, false, true, false, false, false, true, false},
                {true, false, true, false, false, true, true, true}
        }) + " == 254 ?");
    }
}

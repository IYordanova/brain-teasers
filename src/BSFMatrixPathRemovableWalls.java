import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;


public class BSFMatrixPathRemovableWalls {

    private static class State {
        final int x;
        final int y;
        final boolean wallRemoved;
        final State parent;

        public State(int x, int y, boolean wallRemoved, State parent) {
            this.x = x;
            this.y = y;
            this.wallRemoved = wallRemoved;
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            State state = (State) o;
            return x == state.x && y == state.y && wallRemoved == state.wallRemoved;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, wallRemoved);
        }

    }

    private static final Queue<State> nextPossibleStates = new LinkedList<>();
    private static final HashSet<State> visitedStates = new HashSet<>();

    private static void tryMove(State p, int x, int y, int[][] stationMap) {
        if (isOutsideTheMap(x, y, stationMap)) {
            return;
        }
        State newState;
        if (stationMap[x][y] == 0 || isFinalState(x, y, stationMap)) {
            newState = new State(x, y, p.wallRemoved, p);
        } else if (!p.wallRemoved) {
            newState = new State(x, y, true, p);
        } else {
            return;
        }
        if (visitedStates.add(newState)) {
            nextPossibleStates.add(newState);
        }
    }

    private static boolean isOutsideTheMap(int x, int y, int[][] arr) {
        return x < 0 || y < 0 || x >= arr.length || y >= arr[x].length;
    }

    private static boolean isFinalState(int x, int y, int[][] arr) {
        return  x == arr.length - 1 && y == arr[0].length - 1;
    }

    private static State getPathBFS(int[][] stationMap) {
        nextPossibleStates.add(new State(0, 0, false, null));

        while (!nextPossibleStates.isEmpty()) {
            State currentState = nextPossibleStates.remove();

            if (isFinalState(currentState.x, currentState.y, stationMap)) {
                return currentState;
            }

            tryMove(currentState, currentState.x + 1, currentState.y, stationMap);
            tryMove(currentState, currentState.x - 1, currentState.y, stationMap);
            tryMove(currentState, currentState.x, currentState.y + 1, stationMap);
            tryMove(currentState, currentState.x, currentState.y - 1, stationMap);
        }

        return null;
    }

    public static int solution(int[][] map) {
        State finalState = getPathBFS(map);
        if (finalState == null) {
            return  -1;
        }

        int pathLength = 1;
        while (finalState.parent != null) {
            finalState = finalState.parent;
            pathLength++;
        }
        return pathLength;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{
                {0, 1, 1, 0},
                {0, 0, 0, 1},
                {1, 1, 0, 0},
                {1, 1, 1, 0}
        }) + " == 7 ?");
        System.out.println(solution(new int[][]{
                {0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0}
        }) + " == 11 ?");
    }
}

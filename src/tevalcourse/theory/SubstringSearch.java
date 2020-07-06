package tevalcourse.theory;


/*
    Text and pattern
     - brute force go over the text, compare until mismatch in the pattern


    Knuth-Morris-Pratt algorithm:
    - based ont DFA (deterministic finite state automation)
        - finite number of states
        - exactly one transition for each char in the alphabet
        - accept if sequence leads to halt state
    - linear time - only access char in the text and pattern once when building the dfa table


    Boyer-Moore algorithm:
    - compare the pattern from right to left
        - if mismatch and in the text - shift to that letter
        - if mismatch and not in text skip M (len of pattern)
    - time N/M

    Rabin-Karp algorithm:
    - compute hash of pattern chars 0 to M-1
    - for each i, compute hash of text chars i to M+i-1
    - if pattern hash == text substring hash, check for match

 */
public class SubstringSearch {

    // Knuth-Morris-Pratt
    private final int[][] dfa;
    private final int M;
    private final int R;

    // Boyer-Moore
    private final int[] right;
    private final String pattern;

    // Rabin-Karp
    private final static int Q = 997; // long random prime number
    private final long patHash;
    private final long RM;

    public SubstringSearch(String pattern) {
        this.R = 256;
        this.M = pattern.length();
        this.pattern = pattern;
        this.dfa = buildDfa(pattern);
        this.right = buildRight(pattern);
        this.patHash = hash(pattern, M);
        this.RM = calcRm();
    }

    private int[][] buildDfa(String pattern) {
        int[][] dfa = new int[R][M];
        dfa[pattern.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];          // copy mismatching cases
            }
            char ch = pattern.charAt(j);
            dfa[ch][j] = j + 1;  // set match case
            X = dfa[ch][X];      // update restart state
        }
        return dfa;
    }

    public int knutMorrisPrattSearch(String text) {
        int i, j, N = text.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[text.charAt(i)][j];
        }
        if (j == M) {
            return i - M;
        } else {
            return N;
        }
    }


    private int[] buildRight(String pattern) {
        int[] right = new int[R];
        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }
        for (int j = 0; j < M; j++) {
            right[pattern.charAt(j)] = j;
        }
        return right;
    }

    public int boyerMooreSearch(String text) {
        int N = text.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pattern.charAt(j) != text.charAt(i + j)) {
                    skip = Math.max(1, j - right[text.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) {
                return i; // match
            }
        }
        return N;
    }


    // Horner's method
    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j)) % Q;
        }
        return h;
    }

    private long calcRm() {
        // R^(M-1) % Q
        long rm = 1;
        for (int i = 1; i <= M - 1; i++) {
            rm = (R * rm) % Q;
        }
        return rm;
    }

    public int rabinKarpSearch(String text) {
        int n = text.length();
        long txtHash = hash(text, M);
        if (patHash == txtHash) {
            return 0;
        }
        for (int i = M; i < n; i++) {
            txtHash = (txtHash + Q - RM * text.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + text.charAt(i)) % Q;
            if (patHash == txtHash) {
                return i - M + 1;  // Monte Carlo version - take Q so high it's almost impossible to have 2 strings
                // with same hash, so do not check for equality
                // Las Vegas version - checks for equality of the strings as well
            }
        }
        return n;
    }

}

package tevalcourse.theory.regularexpressions;


import edu.princeton.cs.algs4.*;

/*
   RegExpr - a concise way of describing a set of strings
   DFA - machine to determine if a string is in a given set
   NFA(non-deterministic finite machine) - has epsilon transition -
    transition between states without reading char
   - M+1 states - the last one is accept state
   - keep regexes in re[]
   - epsilon transitions - digraph G
   - application
        - grep - generalized regular expression print
 */
public class NFA {

    private final char[] re;
    private final Digraph digraph;
    private final int M;

    public NFA(String regex) {
        this.M = regex.length();
        this.re = regex.toCharArray();
        this.digraph = buildEpsilonTransitionDigraph();
    }

    public boolean recognizes(String txt) {
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(digraph, 0); // states reachable from the start
        for (int i = 0; i < digraph.V(); i++) {
            if (dfs.marked(i)) {
                pc.add(i);
            }
        }

        for (int i = 0; i < txt.length(); i++) {
            Bag<Integer> match = new Bag<>();
            for (int v : pc) {
                if (v == M) {
                    continue;
                }
                if ((re[v] == txt.charAt(i)) || re[v] == '.') {  // all matching transitions
                    match.add(v + 1);
                }
            }

            dfs = new DirectedDFS(digraph, match);
            pc = new Bag<>();
            for (int v = 0; v < digraph.V(); v++) {   // epsilon transitions
                if (dfs.marked(v)) {
                    pc.add(v);
                }
            }
        }

        for (int v : pc) {
            if (v == M) {
                return true;
            }
        }
        return false;
    }

    public Digraph buildEpsilonTransitionDigraph() {
        Digraph digraph = new Digraph(M + 1);
        Stack<Integer> ops = new Stack<>();
        for (int i = 0; i < M; i++) {
            int lp = 1;
            if (re[i] == '(' || re[i] == '|') {  // left parenthesis and |
                ops.push(i);
            } else if (re[i] == ')') {    // right parenthesis
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();
                    digraph.addEdge(lp, or + 1);
                    digraph.addEdge(or, i);
                } else {
                    lp = or;
                }
            }
            if (i < M - 1 && re[i + 1] == '*') {  // closure (*)
                digraph.addEdge(lp, i + 1);
                digraph.addEdge(i + 1, lp);
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == ')') {  // metasymbols
                digraph.addEdge(i, i + 1);
            }
        }
        return digraph;
    }

    public static void main(String[] args) {
        // GREP
        String regex = "(.*" + args[0] + ".*)";
        NFA nfa = new NFA(regex);
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            if (nfa.recognizes(line)) {
                StdOut.println(line);
            }
        }
    }
}

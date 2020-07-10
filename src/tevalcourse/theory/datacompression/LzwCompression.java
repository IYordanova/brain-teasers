package tevalcourse.theory.datacompression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.TST;

/*
   - uses an adaptive model - read the text char at a time and "learn" as it goes
   - create a symbol table with string keys
   - init the table with single chars keys
   - find longest string s in ST that is a prefix for the unscanned part
   - write th codeword associated with s
   - add s+c (c is next char) to the ST
 */
public class LzwCompression {

    private static final int R = 26; // alphabet/ chars
    private static final int W = 8; // size of the output per char
    private static final int L = 8; // ?

    public static void compress() {
        String input = BinaryStdIn.readString();

        TST<Integer> st = new TST<>();
        for (int i = 0; i < R; i++) {
            st.put("" + (char) i, i);
        }
        int code = R + 1;

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);
            BinaryStdOut.write(st.get(s), W);
            int t = s.length();
            if (t < input.length() && code < L) {
                st.put(input.substring(0, t + 1), code++);
            }
            input = input.substring(t);
        }

        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }

    public static void expand() {

    }
}

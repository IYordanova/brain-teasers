package tevalcourse.theory.datacompression.hw;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int R = 256;

    public static void transform() {
        String text = BinaryStdIn.readString();

        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(text);
        int index, first, prev;
        first = -1;

        StringBuilder transformedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            index = circularSuffixArray.index(i);
            if (index == 0){
                first = i;
            }
            prev = index - 1;
            if (prev < 0) {
                prev += text.length();
            }
            transformedText.append(text.charAt(prev));
        }
        if (first < 0) {
            throw new java.lang.IllegalArgumentException();
        }

        BinaryStdOut.write(first);
        BinaryStdOut.write(transformedText.toString());
        BinaryStdOut.close();
    }

    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        int[] count = new int[R + 1];

        String text = BinaryStdIn.readString();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            count[c + 1]++;
        }

        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        char[] aux = new char[text.length()];
        int[] next = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            aux[count[c]] = c;
            next[count[c]] = i;
            count[c]++;
        }

        int index = first;
        for (int i = 0; i < text.length(); i++) {
            BinaryStdOut.write(aux[index]);
            index = next[index];
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if ("-".equals(args[0])) {
            BurrowsWheeler.transform();
        } else if ("+".equals(args[0])) {
            BurrowsWheeler.inverseTransform();
        }
    }
}

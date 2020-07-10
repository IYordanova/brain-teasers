package tevalcourse.theory.datacompression.hw;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.ArrayList;

public class MoveToFront {

    private static final int R = 256;

    public static void encode() {
        int[] pos = new int[R];
        int[] asciiChars = new int[R];

        for (int i = 0; i < R; i++) {
            asciiChars[i] = i;
            pos[i] = i;
        }

        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            BinaryStdOut.write((char) pos[ch]);

            for (int j = pos[ch]; j > 0; j--) {
                pos[asciiChars[j - 1]]++;
                asciiChars[j] = asciiChars[j - 1];
            }

            asciiChars[0] = ch;
            pos[ch] = 0;
        }
    }

    public static void decode() {
        ArrayList<Character> ascii = new ArrayList<>(R);
        for (int i = 0; i < R; i++) {
            ascii.add((char) (R - i - 1));
        }

        while (!BinaryStdIn.isEmpty()) {
            int curr = BinaryStdIn.readChar();
            char c = ascii.remove(R - curr - 1);
            BinaryStdOut.write(c);
            ascii.add(c);
        }
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) {
            decode();
        } else {
            encode();
        }
    }

}

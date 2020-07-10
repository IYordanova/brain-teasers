package tevalcourse.theory.datacompression.hw;


public class CircularSuffixArray {

    private static final int CUTOFF = 12;

    private final char[] textChars;
    private final int[] index;
    private final int N;

    public CircularSuffixArray(String text) {
        this.N = text.length();
        this.textChars = text.toCharArray();
        this.index = new int[N];

        for (int i = 0; i < N; i++) {
            index[i] = i;
        }
        sort(0, N - 1, 0);
    }

    private void sort(int lo, int hi, int d) {
        if (lo + d >= 2 * N || hi + d >= 2 * N) {
            return;
        }
        if (hi <= lo + CUTOFF) {
            insertion(lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        char v = textChars[(index[lo] + d) % N];
        int i = lo + 1;
        while (i <= gt) {
            int t = textChars[(index[i] + d) % N];
            if (t < v) exch(lt++, i++);
            else if (t > v) exch(i, gt--);
            else i++;
        }

        sort(lo, lt - 1, d);
        sort(lt, gt, d + 1);
        sort(gt + 1, hi, d);
    }

    private void insertion(int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(index[j], index[j - 1], d); j--) {
                exch(j, j - 1);
            }
        }
    }

    private boolean less(int i, int j, int d) {
        if (i == j) {
            return false;
        }
        i = i + d;
        j = j + d;
        while (i < 2 * N && j < 2 * N) {
            if (textChars[i % N] < textChars[j % N]) {
                return true;
            }
            if (textChars[i % N] > textChars[j % N]) {
                return false;
            }
            i++;
            j++;
        }
        return false;
    }

    private void exch(int i, int j) {
        int swap = index[i];
        index[i] = index[j];
        index[j] = swap;
    }

    public int length() {
        return N;
    }

    public int index(int i) {
        if (i < 0 || i >= N) {
            throw new IndexOutOfBoundsException();
        }
        return index[i];
    }

    public static void main(String[] args) {
        String s = "ABRACADABRA!";
        CircularSuffixArray test = new CircularSuffixArray(s);
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.charAt(test.index(i)));
        }
    }
}

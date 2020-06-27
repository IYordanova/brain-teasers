package brainteasers;

public class WoodCut {

    public int woodCut(int[] L, int k) {
        int max = 0;
        for (int i : L) {
            max = Math.max(max, i);
        }

        int longest = 0;
        int maxLen = max, minLen = 1;

        while (minLen <= maxLen) {
            int testChopLen = minLen + (maxLen - minLen) / 2;
            int woodPieces = 0;
            for (int woodLen : L) {
                woodPieces += woodLen / testChopLen;
            }

            if (woodPieces >= k) {
                longest = Math.max(longest, testChopLen);
                minLen = testChopLen + 1;
            } else {
                maxLen = testChopLen - 1;
            }
        }
        return longest;
    }

    public static void main(String[] args) {

    }
}

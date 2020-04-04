public class ZIgZagString {

    private static String printZigZagConcat(String A, int B) {
        if (B == 1) {
            return A;
        }

        StringBuilder result = new StringBuilder();
        int downDistance = B + (B - 2);
        int upDistance = 0;
        for (int line = 0; line < B; line++) {
            int charNum = line;
            int numLineChars = 0;
            int lastAdded = -1;
            while (charNum < A.length()) {
                if (lastAdded != charNum) {
                    result.append(A.charAt(charNum));
                    lastAdded = charNum;
                }
                numLineChars++;
                int distance = numLineChars % 2 != 0 ? downDistance : upDistance;
                charNum += distance;
            }

            downDistance -= 2;
            upDistance += 2;
        }
        return result.toString();
    }


    public static void main(String[] args) {
        System.out.println(printZigZagConcat("ABCDEFGH", 1));
        System.out.println(printZigZagConcat("ABCDEFGH", 3));
        System.out.println(printZigZagConcat("ABCDEFGHK", 4));
        System.out.println(printZigZagConcat("GEEKSFORGEEKS", 4));
    }


}

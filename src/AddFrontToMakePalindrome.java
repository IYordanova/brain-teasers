public class AddFrontToMakePalindrome {

    private static int solve(String A) {
        int count = 0;
        while (A.length() > 0) {
            if (isPalindrome(A)) {
                break;
            } else {
                count++;
                A = A.substring(0, A.length() - 1);
                System.out.println(A);
            }
        }
        return count;
    }

    private static boolean isPalindrome(String s) {
        int l = s.length();

        for (int i = 0, j = l - 1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(solve("ABC"));
        System.out.println(solve("AACECAAAA"));
        System.out.println(solve("ABCD"));
    }


}

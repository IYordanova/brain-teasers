public class PalindromeInteger {

    private static boolean isPalindrome(int A) {
        char[] chars = String.valueOf(A).toCharArray();
        int length = chars.length;
        int forward = 0;
        int backward = length - 1;
        while (backward > forward) {
            char forwardChar = chars[forward++];
            char backwardChar = chars[backward--];
            if (forwardChar != backwardChar) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(isPalindrome(6996));
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(2));
        System.out.println(isPalindrome(43));
    }
}

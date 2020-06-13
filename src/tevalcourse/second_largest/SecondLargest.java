package tevalcourse.second_largest;

public class SecondLargest {

    /**
     * Finds and returns the second largest number in an integer array.
     * @param numbers - the array to lookup the number in
     * @return - the second largest number in the array.
     * If the input is null, the size of the input array is less than two or it contains only duplicate elements,
     * then -1 is returned.
     */
    public static int getSecondLargest(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return -1;
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int currentNumber : numbers) {
            if (currentNumber > largest) {
                secondLargest = largest;
                largest = currentNumber;
            } else if (currentNumber > secondLargest && currentNumber != largest) {
                secondLargest = currentNumber;
            }
        }

        if (secondLargest == Integer.MIN_VALUE) {
            return -1;
        }

        return secondLargest;
    }
}

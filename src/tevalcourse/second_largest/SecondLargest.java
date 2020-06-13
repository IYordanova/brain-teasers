package tevalcourse.second_largest;

public class SecondLargest {
    static int getSecondLargest(int[] numbers) {
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

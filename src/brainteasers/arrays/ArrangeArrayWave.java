package brainteasers.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArrangeArrayWave {

    public static ArrayList<Integer> wave(ArrayList<Integer> A) {
        Collections.sort(A);
        int i = 0;
        int j = 1;
        while(i < j) {
            if(j >= A.size()) {
                break;
            }
            int temp = A.get(j);
            A.set(j, A.get(i));
            A.set(i, temp);
            i+=2;
            j+=2;
        }
        return A;
    }

    public static void main(String[] args) {
        System.out.println(wave(new ArrayList<>(Arrays.asList(5, 1, 3, 2, 4))));

    }
}

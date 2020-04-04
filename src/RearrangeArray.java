import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RearrangeArray {

    private static  void arrange(List<Integer> a){
        List<Integer> newArray = new ArrayList<>(a);
        for (int i = 0; i < a.size(); i ++) {
            int temp = a.get(i);
            a.set(i, newArray.get(temp));
        }
    }

    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(4, 0, 2, 1, 3);
        arrange(a);
        System.out.println(a);


        List<Integer> b = Arrays.asList(1, 0);
        arrange(b);
        System.out.println(b);
    }
}

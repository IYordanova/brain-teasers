package tevalcourse.search;

import java.util.Arrays;

public class SearchTest {

    public static void main(String[] args) {
        Search search = new Search(Arrays.asList("Hello world", "World Top"));
        System.out.println(search.search("world", 10));
    }

}

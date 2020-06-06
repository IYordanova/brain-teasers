package tevalcourse.search;

import tevalcourse.BaseTestHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SearchTest extends BaseTestHelper {

    public static void main(String[] args) throws IOException {
        List<String> index;

        index = readFile("index_1");
        new Search(index).search(Arrays.asList(
                "world cup football",
                "results",
                "views",
                "jam code",
                "google program"));
    }

}

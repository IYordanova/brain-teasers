package tevalcourse.search;

import tevalcourse.BaseTestHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SearchTest extends BaseTestHelper {

    public static void main(String[] args) throws IOException {
        List<String> index;
//
//        index = readFile("index_1");
//        new Search(index).search(Arrays.asList(
//                "world cup football",
//                "results",
//                "views",
//                "jam code",
//                "google program"));
//        System.out.println(SEPARATOR);

        index = readFile("index_2");
        new Search(index).search(Arrays.asList(
                "someTHing"
//                "here ",
//                "there",
//                "there     ",
//                "    there",
//                "launching",
//                "program",
//                "australian name",
//                "australian name missingword",
//                "newest here",
//                "",
//                "a",
//                "something something"
//                "videos viewed",
//                "are youtube",
//                "start middle end",
//                "a short title"
        ));
        System.out.println(SEPARATOR);
    }

}

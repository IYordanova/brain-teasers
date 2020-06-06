package tevalcourse.search;

import java.util.Comparator;
import java.util.List;

public class Search {
    private static final int LIMIT = 10;
    private final Index index;

    public Search(List<String> indexValues) {
        this.index = new Index(indexValues);
    }

    public void search(List<String> queries) {
        queries.stream()
                .map(query -> index.find(query, LIMIT))
                .forEach(resultSet -> {
                    System.out.println(resultSet.size());
                    resultSet.stream()
                            .sorted(Comparator.comparingInt(s ->((String)s).split("\\s+").length)
                                    .thenComparing(s -> (String)s))
                            .forEach(System.out::println);
                });
    }
}

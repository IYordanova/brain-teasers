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
                    int size = resultSet.size();
                    System.out.println(Math.min(size, 10));
                    resultSet.stream()
                            .sorted(Comparator.comparingInt(s ->((String)s).split("\\s+").length)
                                    .thenComparing(s -> (String)s))
                            .sequential()
                            .limit(LIMIT)
                            .forEach(System.out::println);
                });
    }
}

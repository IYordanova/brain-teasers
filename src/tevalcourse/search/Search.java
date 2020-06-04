package tevalcourse.search;

import java.util.List;
import java.util.Set;

public class Search {
    private final Index index;

    public Search(List<String> indexValues) {
        this.index = new Index(indexValues);
    }

    public Set<String> search(String query, int limit) {
        return index.find(query, limit);
    }
}

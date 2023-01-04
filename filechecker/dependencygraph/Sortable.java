package filechecker.dependencygraph;

import java.util.*;

public interface Sortable<T> {
    List<T> topologicalSort();
}

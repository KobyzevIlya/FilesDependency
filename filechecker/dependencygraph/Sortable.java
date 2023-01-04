//https://e-maxx.ru/algo/topological_sort
//https://e-maxx.ru/algo/finding_cycle

package filechecker.dependencygraph;

import java.util.*;

public interface Sortable<T> {
    List<T> topologicalSort();
}

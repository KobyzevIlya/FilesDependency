package filechecker.dependencygraph;

import java.util.*;

/**
 * The interface should have told you that the graph can be sorted in any of the provided ways.
 * @param <T> this template is specified the data type in the nodes of the graph.
 */
public interface Sortable<T> {
    /**
     * Definition of topological sorting. The implementation is left to the heirs.
     * @return Topologically sorted <code>List</code> of node contents.
     * @see <a href="https://en.wikipedia.org/wiki/Topological_sorting">Topological sort description</a>
     */
    List<T> topologicalSort();
}

package filechecker.dependencygraph;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * This abstract class defines the structure of an oriented graph for further operations on it.<br>
 * Requires class Node.
 * @param <T> this template is specified the data type in the nodes of the graph.
 * @see Node
 */
public abstract class AbstractGraph<T> implements Sortable<T> {

    /**
     * Container for graph nodes.
     */
    protected Set<Node<T>> nodes;

    /**
     * Allocates memory for a new node and adds it to the graph.
     * @param data node content.
     */
    public void addNode(T data) {
        nodes.add(new Node<>(data));
    }

    /**
     * Searches for a node with this content in the graph and returns it if successful.
     * @param data node content.
     * @return link to the node if it was found, <code>null</code> otherwise.
     */
    public Node<T> getNode(T data) {
        for (var node : nodes) {
            if (node.getData().equals(data)) {
                return node;
            }
        }
        return null;
    }

    /**
     * Checks the graph for loops and finds the node where the loop starts.
     * @return link to the content of node that starts the loop, if it was found, <code>null</code> otherwise.
     * @see <a href="https://e-maxx.ru/algo/finding_cycle">Algorithm description</a>
     */
    public T checkCycles() {
        Node<T> possibleProblem = null;
        for (var node : nodes) {
            Map<Node<T>, Color> colorMap = new HashMap<>();
            possibleProblem = dfsCheckCycles(node, colorMap);
            if (possibleProblem != null) {
                break;
            }
        }
        if (possibleProblem == null) {
            return null;
        }
        return possibleProblem.getData();
    }

    private Node<T> dfsCheckCycles(Node<T> node, Map<Node<T>, Color> colorMap) {
        colorMap.put(node, Color.black);
        for (var to : node.getDescendants()) {
            if (!colorMap.containsKey(to)) {
                return dfsCheckCycles(to, colorMap);
            } else if (colorMap.get(to) == Color.black) {
                return node;
            }
        }
        return null;
    }

    /**
     * Arranges the nodes of the graph in the order of their dependencies: if there is a path from node A to node B, then node B must come after node A.
     * @return Topologically sorted <code>List</code> of node contents.
     * @see <a href="https://e-maxx.ru/algo/topological_sort">Algorithm description</a>
     */
    @Override
    public List<T> topologicalSort() {
        List<T> sortedData = new ArrayList<>();
        Map<Node<T>, Color> colorMap = new HashMap<>();
        for (var node : nodes) {
            if (!colorMap.containsKey(node)) {
                dfsTopologicalSort(node, colorMap, sortedData);
            }
        }

        Collections.reverse(sortedData);
        return sortedData;
    }

    private void dfsTopologicalSort(Node<T> node, Map<Node<T>, Color> colorMap, List<T> sortedData) {
        colorMap.put(node, Color.black);
        for (var to : node.getDescendants()) {
            if (!colorMap.containsKey(to) || colorMap.get(to) == Color.white) {
                dfsTopologicalSort(to, colorMap, sortedData);
            }
        }
        sortedData.add(node.getData());
    }
}

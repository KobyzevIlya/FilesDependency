package filechecker.dependencygraph;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class AbstractGraph<T> implements Sortable<T> {
    protected Set<Node<T>> nodes;

    public void addNode(T data) {
        nodes.add(new Node<>(data));
    }

    public Node<T> getNode(T data) {
        for (var node : nodes) {
            if (node.getData().equals(data)) {
                return node;
            }
        }
        return null;
    }

    //https://e-maxx.ru/algo/finding_cycle
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

    // https://e-maxx.ru/algo/topological_sort
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

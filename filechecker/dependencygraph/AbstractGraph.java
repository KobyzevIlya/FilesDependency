package filechecker.dependencygraph;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractGraph<T> implements Sortable<T> {
    private Set<Node<T>> nodes;

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
    public Node<T> checkCycles() {
        Map<Node<T>, Color> colorMap = new HashMap<>();
        Node<T> cycleStart = null;
        Node<T> cycleEnd = null;

        for (var node : nodes) {
            if (dfsCheckCycles(node, colorMap, cycleStart)) {
                break;
            }
        }
        return cycleStart;
    }

    private boolean dfsCheckCycles(Node<T> node, Map<Node<T>, Color> colorMap, Node<T> cycleStart) {
        colorMap.put(node, Color.gray);
        for (var to : node.getDescendants()) {
            if (!colorMap.containsKey(to)) {
                return dfsCheckCycles(to, colorMap, cycleStart);
            } else if (colorMap.get(to) == Color.gray) {
                cycleStart = to;
                return true;
            }
        }
        colorMap.put(node, Color.black);
        return false;
    }

    @Override
    public List<T> topologicalSort() {
        return null;
    }
}

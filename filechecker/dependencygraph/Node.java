package filechecker.dependencygraph;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private final T data;
    private final List<Node<T>> parents;
    private final List<Node<T>> descendants;

    Node(T data) {
        this.data = data;
        parents = new ArrayList<>();
        descendants = new ArrayList<>();
    }

    public void addParent(Node<T> parent) {
        parents.add(parent);
    }

    public void addDescendant(Node<T> descendant) {
        descendants.add(descendant);
    }
}

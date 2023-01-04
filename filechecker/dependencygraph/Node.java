package filechecker.dependencygraph;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private final T data;
    private final List<Node<T>> parents;
    private final List<Node<T>> descendants;

    protected Node() {
        data = null;
        parents = new ArrayList<>();
        descendants = new ArrayList<>();
    }

    protected Node(T data) {
        this.data = data;
        parents = new ArrayList<>();
        descendants = new ArrayList<>();
    }

    protected void addParent(Node<T> parent) {
        parents.add(parent);
    }

    protected List<Node<T>> getParents() {
        return parents;
    }

    protected void addDescendant(Node<T> descendant) {
        descendants.add(descendant);
    }

    protected List<Node<T>> getDescendants() {
        return descendants;
    }

    protected T getData() {
        return data;
    }

}

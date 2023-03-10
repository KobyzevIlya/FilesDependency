package ru.hse.filechecker.dependencygraph;

import java.util.ArrayList;
import java.util.List;

/**
 * This class describes the nodes of a graph. It is not intended for public use and should only be used together with
 * the AbstractGraph class or its descendants.<br>
 * The <code>clone()</code> method is not defined for this class, so be careful when working with nodes.
 *
 * @param <T> this template is specified the data type in the nodes.
 * @see AbstractGraph
 */
public class Node<T> {
    private final T data;
    private final List<Node<T>> descendants;

    /**
     * Constructor from content
     *
     * @param data content of node.
     */
    protected Node(T data) {
        this.data = data;
        descendants = new ArrayList<>();
    }

    /**
     * Adds a descendant to this node. A node can have more than one descendant.
     *
     * @param descendant link to the descendant <code>Node</code> class.
     */
    protected void addDescendant(Node<T> descendant) {
        descendants.add(descendant);
    }

    /**
     * Returns descendants of the node.
     *
     * @return <code>List</code> of descendants.
     */
    protected List<Node<T>> getDescendants() {
        return descendants;
    }

    /**
     * Returns the content of the node.
     *
     * @return link to node template content
     */
    protected T getData() {
        return data;
    }

}

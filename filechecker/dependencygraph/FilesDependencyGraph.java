package filechecker.dependencygraph;

import filechecker.consoleworkers.ConsoleWriter;
import filechecker.file.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilesDependencyGraph extends AbstractGraph<File> {
    private Set<Node<File>> nodes;
    private FileHandler fileHandler;

    public FilesDependencyGraph(FileHandler fileHandler) {
        nodes = new HashSet<>();
        this.fileHandler = fileHandler;
    }

    @Override
    public Node<File> getNode(File data) {
        for (var node : nodes) {
            if (node.getData().equals(data)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public void addNode(File file) {
        Node<File> currentNode = getNode(file);
        if (currentNode == null) {
            nodes.add(new Node<>(file));
            currentNode = getNode(file);
        }

        for (var requirement : fileHandler.getRequires(file)) {
            Node<File> parent = getNode(requirement);
            if (parent == null) {
                nodes.add(new Node<>(requirement));
                parent = getNode(requirement);
            }
            parent.addDescendant(currentNode);
            currentNode.addParent(parent);
        }
    }
}

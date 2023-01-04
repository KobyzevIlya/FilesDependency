package filechecker.dependencygraph;

import filechecker.file.FileHandler;

import java.io.File;
import java.util.HashSet;

public class FilesDependencyGraph extends AbstractGraph<File> {
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

package ru.hse.src.filechecker.dependencygraph;

import ru.hse.src.filechecker.file.FileHandler;

import java.io.File;
import java.util.HashSet;


/**
 * Graph that has a File class as its node contents. Some methods are redefined to work with files.
 */
public class FilesDependencyGraph extends AbstractGraph<File> {
    private final FileHandler fileHandler;

    /**
     * Constructor from the FileHandler class. Allocates memory for the node container.
     * @param fileHandler link to file handler.
     */
    public FilesDependencyGraph(FileHandler fileHandler) {
        nodes = new HashSet<>();
        this.fileHandler = fileHandler;
    }

    @Override
    public void addNode(File file) {
        Node<File> currentNode = getNode(file);
        if (currentNode == null) {
            super.addNode(file);
            currentNode = getNode(file);
        }

        for (var requirement : fileHandler.getRequires(file)) {
            Node<File> parent = getNode(requirement);
            if (parent == null) {
                super.addNode(requirement);
                parent = getNode(requirement);
            }
            parent.addDescendant(currentNode);
            currentNode.addParent(parent);
        }
    }
}

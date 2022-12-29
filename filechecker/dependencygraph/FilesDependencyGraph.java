package filechecker.dependencygraph;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilesDependencyGraph {
    private Set<Node<File>> nodes;

    public FilesDependencyGraph() {
        nodes = new HashSet<>();
    }

    public void addNode(Node<File> node) {
        List<File> requires;
    }
}

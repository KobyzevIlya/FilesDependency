package filechecker;

import filechecker.consoleworkers.ConsoleReader;
import filechecker.dependencygraph.FilesDependencyGraph;
import filechecker.file.FileHandler;
import filechecker.heap.ContentsHeap;

import java.io.File;

public class AppBuilder {
    private final File mainFolder;
    private final FilesDependencyGraph dependencyGraph;
    private final ContentsHeap contentsHeap;
    private final FileHandler fileHandler;

    AppBuilder() {
        mainFolder = ConsoleReader.getMainFolder();
        fileHandler = new FileHandler(mainFolder.getAbsolutePath());
        dependencyGraph = new FilesDependencyGraph(fileHandler);
        contentsHeap = new ContentsHeap();
    }

    public void buildApp() {
        contentsHeap.releaseContents(mainFolder);
        for (var content : contentsHeap.getFiles()) {
            dependencyGraph.addNode(content);
        }
    }

}

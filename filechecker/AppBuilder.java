package filechecker;

import filechecker.consoleworkers.ConsoleHandler;
import filechecker.dependencygraph.FilesDependencyGraph;
import filechecker.file.FileHandler;
import filechecker.heap.ContentsHeap;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AppBuilder {
    private final File mainFolder;
    private final FilesDependencyGraph dependencyGraph;
    private final ContentsHeap contentsHeap;
    private final FileHandler fileHandler;

    AppBuilder() {
        mainFolder = ConsoleHandler.getMainFolder();
        fileHandler = new FileHandler(mainFolder.getAbsolutePath());
        dependencyGraph = new FilesDependencyGraph(fileHandler);
        contentsHeap = new ContentsHeap();
    }

    public void buildApp() {
        contentsHeap.releaseContents(mainFolder);

        for (var content : contentsHeap.getFiles()) {
            dependencyGraph.addNode(content);
        }

        File problemFile = dependencyGraph.checkCycles();
        ConsoleHandler.cycleInfoMessage(problemFile);

        List<File> files = dependencyGraph.topologicalSort();
        ConsoleHandler.printFilesWithContents(files);
    }

}

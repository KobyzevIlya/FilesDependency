package ru.hse.filechecker;

import ru.hse.filechecker.consoleworkers.ConsoleHandler;
import ru.hse.filechecker.dependencygraph.FilesDependencyGraph;
import ru.hse.filechecker.file.FileHandler;
import ru.hse.filechecker.heap.ContentsHeap;

import java.io.File;
import java.util.List;

/**
 * This class creates instances of other classes and organizes their work.
 */
public class AppBuilder {
    private final File mainFolder;
    private final FilesDependencyGraph dependencyGraph;
    private final ContentsHeap contentsHeap;

    /**
     * The constructor calls a method that reads the path to the main folder.
     */
    public AppBuilder() {
        mainFolder = ConsoleHandler.getMainFolder();
        FileHandler fileHandler = new FileHandler(mainFolder.getAbsolutePath());
        dependencyGraph = new FilesDependencyGraph(fileHandler);
        contentsHeap = new ContentsHeap();
    }

    /**
     * Main work cycle. All folders and files are located, files are added to the dependency graph, the graph is checked
     * for a cycle and sorted topologically.
     */
    public void buildApp() {
        contentsHeap.releaseContents(mainFolder);

        for (var content : contentsHeap.getFiles()) {
            dependencyGraph.addNode(content);
        }

        File problemFile = dependencyGraph.checkCycles();
        ConsoleHandler.cycleInfoMessage(problemFile);
        if (problemFile != null) {
            return;
        }

        List<File> files = dependencyGraph.topologicalSort();
        ConsoleHandler.printFilesWithContents(files, mainFolder);
    }

}

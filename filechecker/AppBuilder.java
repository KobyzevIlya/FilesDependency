package filechecker;

import filechecker.consoleworkers.ConsoleHandler;
import filechecker.dependencygraph.FilesDependencyGraph;
import filechecker.file.FileHandler;
import filechecker.heap.ContentsHeap;

import java.io.File;
import java.util.List;

public class AppBuilder {
    private final File mainFolder;
    private final FilesDependencyGraph dependencyGraph;
    private final ContentsHeap contentsHeap;

    AppBuilder() {
        mainFolder = ConsoleHandler.getMainFolder();
        FileHandler fileHandler = new FileHandler(mainFolder.getAbsolutePath());
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
        if (problemFile != null) {
            return;
        }

        List<File> files = dependencyGraph.topologicalSort();
        ConsoleHandler.printFilesWithContents(files, mainFolder);
    }

}

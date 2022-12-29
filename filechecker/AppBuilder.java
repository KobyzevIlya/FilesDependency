package filechecker;

import filechecker.consoleworkers.ConsoleReader;
import filechecker.dependencygraph.FilesDependencyGraph;
import filechecker.heap.ContentsHeap;

import java.io.File;

public class AppBuilder {
    private final File mainFolder;
    private final FilesDependencyGraph dependencyGraph;
    private final ContentsHeap contentsHeap;

    AppBuilder() {
        mainFolder = ConsoleReader.getMainFolder();
        dependencyGraph = new FilesDependencyGraph();
        contentsHeap = new ContentsHeap();
    }

    public void buildApp() {
        contentsHeap.releaseContents(mainFolder);
    }

}

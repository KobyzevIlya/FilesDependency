package filechecker.heap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContentsHeap {
    private final List<File> files;
    private final List<File> folders;

    public ContentsHeap() {
        files = new ArrayList<>();
        folders = new ArrayList<>();
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void addFolder(File folder) {
        folders.add(folder);
    }

    public void releaseContents(File mainFolder) {
        folders.add(mainFolder);
        List<File> visitedFolders = new ArrayList<>();

        while (visitedFolders.size() != folders.size()) {
            for (var currentFolder : folders) {
                if (visitedFolders.contains(currentFolder)) {
                    continue;
                }
                visitedFolders.add(currentFolder);

                for (var content : Objects.requireNonNull(currentFolder.listFiles())) {
                    if (content.isDirectory()) {
                        folders.add(content);
                    } else {
                        files.add(content);
                    }
                }
            }
        }
    }
}

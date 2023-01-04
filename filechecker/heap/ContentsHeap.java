package filechecker.heap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Contains files and folders that are in the main folder, but does not have information about their relationships.
 */
public class ContentsHeap {
    private final List<File> files;
    private final List<File> folders;

    /**
     * Base constructor. Allocates memory for the container of files and folders.
     */
    public ContentsHeap() {
        files = new CopyOnWriteArrayList<>();
        folders = new CopyOnWriteArrayList<>();
    }

    /**
     * Returns a <code>List</code> of files.
     *
     * @return direct link to container with files.
     */
    public List<File> getFiles() {
        return files;
    }

    /**
     * Returns a <code>List</code> of folders.
     *
     * @return direct link to container with folders.
     */
    public List<File> getFolders() {
        return folders;
    }

    /**
     * Finds all folders and files in the main folder and adds them to their respective containers.
     *
     * @param mainFolder class <code>File</code> of main folder
     */
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

package ru.hse.src.filechecker.file;

import ru.hse.src.filechecker.consoleworkers.ConsoleHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class processes a file and defines the files it requires.
 */
public class FileHandler {
    private final String mainFolderPath;

    /**
     * Constructor from main folder.
     * @param mainFolderPath main folder absolute path.
     */
    public FileHandler(String mainFolderPath) {
        this.mainFolderPath = mainFolderPath;
    }

    /**
     * Reads the file and looks inside the word <code>require</code>. Then returns a list of requirements.<br>
     * This method also validates the request and calls incorrectRequireMessage from ConsoleHandler if necessary.
     * @param file <code>File</code> class of file to read.
     * @return <Code>List</Code> of files that current file requires.
     * @see ConsoleHandler
     */
    public List<File> getRequires(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException exception) {
            ConsoleHandler.fileNotFoundMessage(file);
        }
        List<File> requires = new ArrayList<>();

        while (true) {
            assert scanner != null;
            if (!scanner.hasNextLine()) break;
            String currentString = scanner.nextLine();

            if (currentString.startsWith("require")) {
                File requirement = new File(mainFolderPath + "\\" + currentString.substring(9, currentString.length() - 1));

                if (!file.exists() || !file.isFile()) {
                    ConsoleHandler.incorrectRequireMessage();
                    System.exit(0);
                }

                requires.add(requirement);
            }
        }
        return requires;
    }
}

package filechecker.file;

import filechecker.consoleworkers.ConsoleWriter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class FileHandler {
    private final String mainFolderPath;

    public FileHandler(String mainFolderPath) {
        this.mainFolderPath = mainFolderPath;
    }

    public List<File> getRequires(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<File> requires = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String currentString = scanner.nextLine();

            if (currentString.startsWith("require")) {
                File requirement = new File(mainFolderPath + "\\" + currentString.substring(9, currentString.length() - 1));

                if (!file.isFile()) {
                    ConsoleWriter.incorrectRequireMessage();
                    System.exit(0);
                }

                requires.add(requirement);
            }
        }
        return requires;
    }
}

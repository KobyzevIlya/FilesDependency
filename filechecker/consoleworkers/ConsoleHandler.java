package filechecker.consoleworkers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * A class that allows you to read information from the console or write messages there.
 */
abstract public class ConsoleHandler {
    /**
     * Reads the path to the main folder from the console. The path must be absolute.
     * @return the main folder as a <code>File</code> class
     */
    public static File getMainFolder() {
        getFilenameMessage();

        Scanner scanner = new Scanner(System.in);
        File mainFolder = new File(scanner.nextLine());

        if (!mainFolder.isDirectory()) {
            incorrectDirectoryMessage();
            System.exit(0);
        }

        return mainFolder;
    }

    /**
     * Prints message to the console about the wrong directory.
     */
    public static void incorrectDirectoryMessage() {
        System.out.print("->Incorrect Directory!<-\n");
    }

    /**
     * Prints message to the console about the wrong requirement in file content.
     */
    public static void incorrectRequireMessage() {
        System.out.print("->Incorrect Require File!<-\n");
    }

    /**
     * Prints input file require message.
     */
    protected static void getFilenameMessage() {
        System.out.print("->Input main folder absolute path<-\n");
    }

    /**
     *
     Prints a message about the loop to the console. If there is no cycle, it displays "No cycles", otherwise it displays
     the cycle reason file and exits the application with code 0.
     * @param file cycle reason file
     */
    public static void cycleInfoMessage(File file) {
        if (file == null) {
            System.out.print("->No cycles<-\n");
        } else {
            System.out.print("->Cycle in " + file.getAbsolutePath() + "<-\n");
        }
    }

    /**
     * Displays paths relative to the main folder, names and contents of files from the <code>List</code>.
     * @param files list of files to output.
     * @param mainFolder main folder handler. Needed to remove its path from the file path.
     */
    public static void printFilesWithContents(List<File> files, File mainFolder) {
        for (var file : files) {
            try {
                Scanner scanner = new Scanner(file);
                System.out.print("\n" + file.getAbsolutePath().substring(mainFolder.getAbsolutePath().length() + 1) + "\n");
                while (scanner.hasNextLine()) {
                    System.out.print("   " + scanner.nextLine() + "\n");
                }
            } catch (FileNotFoundException exception) {
                fileNotFoundMessage(file);
            }
        }
    }

    /**
     * Displays a message that the file does not exist and exits the application with code 0.
     * @param file missing file handler
     */
    public static void fileNotFoundMessage(File file) {
        System.out.print("->Cannot find file " + file.getAbsolutePath() + "\n");
        System.exit(0);
    }
}

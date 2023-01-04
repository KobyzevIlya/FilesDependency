package filechecker.consoleworkers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

abstract public class ConsoleHandler {
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

    public static void incorrectDirectoryMessage() {
        System.out.print("->Incorrect Directory!<-\n");
    }

    public static void incorrectRequireMessage() {
        System.out.print("->Incorrect Require File!<-\n");
    }

    protected static void getFilenameMessage() {
        System.out.print("->Input main folder absolute path<-\n");
    }

    public static void cycleInfoMessage(File file) {
        if (file == null) {
            System.out.print("->No cycles<-\n");
        } else {
            System.out.print("->Cycle in " + file.getAbsolutePath() + "<-\n");
        }
    }

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

    public static void fileNotFoundMessage(File file) {
        System.out.print("->Cannot find file " + file.getAbsolutePath() + "\n");
        System.exit(0);
    }
}

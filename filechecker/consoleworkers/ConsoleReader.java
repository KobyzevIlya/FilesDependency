package filechecker.consoleworkers;

import filechecker.MainClass;

import java.io.File;
import java.util.Scanner;

abstract public class ConsoleReader {
    public static File getMainFolder() {
        Scanner scanner = new Scanner(System.in);
        File mainFolder = new File(scanner.nextLine());

        if (!mainFolder.isDirectory()) {
            ConsoleWriter.incorrectDirectoryMessage();
            System.exit(0);
        }

        return mainFolder;
    }
}

package filechecker.consoleworkers;

abstract public class ConsoleWriter {
    public static void incorrectDirectoryMessage() {
        System.out.print("->Incorrect Directory!<-\n");
    }

    public static void incorrectRequireMessage() {
        System.out.print("->Incorrect Require File!<-\n");
    }
}

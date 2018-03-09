package com.mycom.storage;

import com.microsoft.azure.storage.StorageException;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * A class which provides log utility methods
 * 
 * @author Neusoft
 */
final class PrintHelper {
    
    private PrintHelper() {        
    }
    
    /**
     * Prints out the common information .
     */
    static void printInfo(String message) {
        System.out.println(message);
    }

    /**
     * Prints out the start information .
     */
    static void printStartInfo(String message) {
        System.out.println(String.format(
                "%s starting...",
                message));
    }

    /**
     * Prints out the complete information .
     */
    static void printCompleteInfo(String message) {
        System.out.println(String.format(
                "%s completed.",
                message));
    }

    /**
     * Print the exception stack trace
     *
     * @param t Exception to be printed
     */
    static void printException(Throwable t) {

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        t.printStackTrace(printWriter);
        if (t instanceof StorageException) {
            if (((StorageException) t).getExtendedErrorInformation() != null) {
                System.out.println(String.format("\nError: %s", ((StorageException) t).getExtendedErrorInformation().getErrorMessage()));
            }
        }
        System.out.println(String.format("Exception details:\n%s", stringWriter.toString()));
    }
}
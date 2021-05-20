package utilities;

import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Utility {

    //Create the report path if it does not exist
    public static void createReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!");
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }

    public static void cleanDirectory(String path) {

        File directory = new File(path);
        if (directory.exists()) {
            directory.delete();
        }
        directory.mkdir();
    }

    public static File createFile(String filePath) {
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

    }

    public static String getStackTrace(Throwable t) {
        if (t == null) {
            return null;
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    public static void isFileExisted(String filePathString) {
        File f = new File(filePathString);
        if (!f.exists()) {
            Assert.fail("no file found");
        }
    }
}

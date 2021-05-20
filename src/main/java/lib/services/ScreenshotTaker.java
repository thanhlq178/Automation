package lib.services;

import lib.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.Constants;
import utilities.Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ScreenshotTaker {
    public static String takeScreenShot(String methodName) {
        RemoteWebDriver driver = (RemoteWebDriver) DriverManager.getWebDriver();
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File tmpFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        String[] keyWords = methodName.split("\\.");
        String shortName = keyWords.length == 1 ? keyWords[keyWords.length - 1]
                : keyWords[keyWords.length - 1] + "_" + keyWords[keyWords.length - 2];
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(Paths.get(Constants.SCREENSHOT_PATH));
        pathBuilder.append(File.separator);
        pathBuilder.append(shortName);
        pathBuilder.append(Math.random());
        pathBuilder.append(Constants.SCREENSHOT_EXTENSION);
        String screenshotFilePath = pathBuilder.toString();
        try {
            FileUtils.copyFile(tmpFile, new File(screenshotFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tmpFile.delete();
        return screenshotFilePath;

    }

    /**
     * Clean up the directory of the generated sreenShots and create new one.
     */
    public static void cleanUpScreenShotDir() {
        Utility.cleanDirectory(Constants.SCREENSHOT_PATH);
    }
}

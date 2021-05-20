package lib.services;

import com.aventstack.extentreports.Status;
import org.apache.commons.lang3.StringUtils;
import utilities.Constants;
import utilities.parse.ReadWriteJson;


public class Logger {

    public static void log(String className, String message, LogLevel level) {
        Status reportStatus;

        switch (level) {
            case FATAL:
                reportStatus = Status.FATAL;
                break;
            case ERROR:
                reportStatus = Status.ERROR;
                break;
            case FAIL:
                reportStatus = Status.FAIL;
                break;
            case WARNING:
                reportStatus = Status.WARNING;
                break;
            case DEBUG:
                reportStatus = Status.DEBUG;
                break;
            case INFO:
                reportStatus = Status.INFO;
                break;
            case SKIP:
                reportStatus = Status.SKIP;
                break;
            default:
                reportStatus = Status.PASS;
        }
        String methodName = Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
        if (StringUtils.equals(ReadWriteJson.getValueFromConfigFile(Constants.GENERATE_SCREENSHOT),"true")) {

            String screenshotFilePath = ScreenshotTaker.takeScreenShot(methodName);
            if (screenshotFilePath == null) {
                ExtentReport.log(reportStatus, message);
            } else {
                ExtentReport.logWithScreenshot(reportStatus, message, screenshotFilePath);
            }

        } else {
            if (reportStatus.equals(Status.FATAL) || reportStatus.equals(Status.ERROR) || reportStatus.equals(Status.FAIL)
                    || reportStatus.equals(Status.WARNING) || reportStatus.equals(Status.PASS)) {
                String screenshotFilePath = ScreenshotTaker.takeScreenShot(methodName);
                if (screenshotFilePath == null) {
                    ExtentReport.log(reportStatus, message);
                } else {
                    ExtentReport.logWithScreenshot(reportStatus, message, screenshotFilePath);
                }
            } else {
                ExtentReport.log(reportStatus, message);
            }
        }

    }

    public enum LogLevel {
        FATAL, ERROR, FAIL, WARNING, DEBUG, INFO, PASS, SKIP
    }


}

package utilities.listeners;

import lib.services.ExtentReport;
import lib.services.ScreenshotTaker;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import utilities.Constants;

public class SuiteListener implements ISuiteListener {
    public static String configPath;

    @Override
    public void onStart(ISuite suite) {
        ScreenshotTaker.cleanUpScreenShotDir();
        configPath = suite.getXmlSuite().getParameter(Constants.CONFIG_NAME);
        ExtentReport.getInstance(suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {

    }
}

package lib.commons;

import lib.driver.CreateDriver;
import lib.services.Logger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utilities.Constants;

/**
 * Test setup Base class
 */
public abstract class MyCommonSetup {
    // abstract methods
    protected abstract void testClassSetup(ITestContext context) throws Exception;
    protected abstract void testClassTeardown(ITestContext context) throws Exception;
    protected abstract void testMethodSetup(ITestResult result) throws Exception;
    protected abstract void testMethodTeardown(ITestResult result) throws Exception;

    @BeforeSuite(alwaysRun = true, enabled = true)
    protected void suiteSetup(ITestContext context) throws Exception {}

    @AfterSuite(alwaysRun = true, enabled = true)
    protected void suiteTeardown(ITestContext context) throws Exception {}

    @BeforeTest(alwaysRun = true, enabled = true)
    protected void testSetup(ITestContext context) throws Exception {
        // setup driver
        //CreateDriver.getInstance().setDriver(browser, platform, env);
    }

    @AfterTest(alwaysRun = true, enabled = true)
    protected void testTeardown(ITestContext context) throws Exception {
        //CreateDriver.getInstance().closeDriver();
    }

    @BeforeClass(alwaysRun = true, enabled = true)
    protected void classSetup(ITestContext context) throws Exception {}

    @AfterClass(alwaysRun = true, enabled = true)
    protected void classTeardown(ITestContext context) throws Exception {}

    /**
     * The driver started in @BeforeTest and closed in @AfterTest methods.
     * This allows the user the ability to run all the classes contained in the <test></test> sections of the suite XML file in parallel.
     *
     * @param browser
     * @param platform
     * @param env
     * @param result
     * @throws Exception
     */
    @Parameters({"browser", "platform", "environment"})
    @BeforeMethod(alwaysRun = true, enabled = true)
    protected void methodSetup(@Optional(Constants.BROWSER) String browser,
                               @Optional(Constants.PLATFORM) String platform,
                               @Optional(Constants.ENVIRONMENT) String env,
                               ITestResult result) throws Exception {
        // setup driver
        CreateDriver.getInstance().setDriver(browser, platform, env);
        System.out.println("Setup before method for base class");
    }

    @AfterMethod(alwaysRun = true, enabled = true)
    protected void methodTeardown(ITestResult result) throws Exception {
        System.out.println("Teardown after method for base class");
        if (!result.isSuccess()) {
            Logger.log(this.getClass().getName(),
                    ExceptionUtils.getStackTrace(result.getThrowable()), Logger.LogLevel.FAIL);
        }

        CreateDriver.getInstance().closeDriver();
    }
}

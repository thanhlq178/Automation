package utilities.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import lib.services.ExtentReport;
import lib.services.ScreenshotTaker;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;
import utilities.data.InjectData;

import java.lang.reflect.Method;


public class TestListener implements ITestListener {

    private static ExtentReports extent = null;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ExtentTest extentTest, extentTestValue;
    private static MediaEntityModelProvider provider;
    public static String dataPath;
	private static final String EXTENT_REPORT_PATH_TO_DATA_FILE_PATH = "../src/test/resources";

    //helps to generate the logs in test report.
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(result.getTestClass().getName() + "." + result.getMethod().getMethodName() + result.getMethod().getDescription() + " started!");
        ITestNGMethod method = result.getMethod();
        dataPath = getDataMapperPath(method);
        extent = ExtentReport.getExtentReports();
        Object[] params = result.getParameters();
        extentTest = extent.createTest(
                result.getTestClass().getName() + "." + result.getMethod().getMethodName().toString()/*,
                params[0].toString()*/);
        test.set(extentTest);
    }
    

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        String methodName = testResult.getName().toString().trim();
        String failedScreenShot = ScreenshotTaker.takeScreenShot(methodName);
        if (failedScreenShot == null) {
            test.get().log(Status.FAIL, testResult.getThrowable().toString());
        } else {
            provider = ExtentReport.logWithScreenshot(failedScreenShot);
            if (provider != null) {
                test.get().log(Status.FAIL, testResult.getThrowable().toString(), provider);
            } else {
                test.get().log(Status.FAIL, testResult.getThrowable().toString());
            }
        }
    }

    public void onFinish(ITestContext context) {
        System.out.println(("Reports Test is ending!"));
        ExtentReport.endTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip(result.getThrowable());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
        System.out.println("Reports Version Test started!");
    }

    public static ExtentTest getExtentTest() {
        extentTestValue = extentTest;
        return extentTestValue;
    }

    private String getFormattedFileName(ITestNGMethod method) {
        return method.getTestClass().getName() + "." + method.getMethodName();
    }

    /**
     * Return the path of the Json file that describe the data of the test
     *
     * @param method
     * @return
     */
    private String getDataMapperPath(ITestNGMethod method) {
        Method m = getMethod(method);
        if (m == null || m.getAnnotation(InjectData.class) == null) {
            return null;
        }
        String jsonFile = m.getAnnotation(InjectData.class).json();
        return jsonFile;
    }

    private Method getMethod(ITestNGMethod method) {
        if (!method.isTest()) {
            return null;
        }
        ConstructorOrMethod com = method.getConstructorOrMethod();
        return com.getMethod();
    }
    
    private String getTestDescription(ITestNGMethod method,ITestResult result){
    	String dataFilePath = getDataMapperPath(method);
		String dataLink = "No data file";
		if (dataFilePath != null) {
			StringBuilder pathBuilder = new StringBuilder();
			pathBuilder.append("<a href=\"");
			pathBuilder.append(EXTENT_REPORT_PATH_TO_DATA_FILE_PATH);
			pathBuilder.append(dataFilePath);
			pathBuilder.append("\">Data file : ");
			pathBuilder.append(dataFilePath);
			pathBuilder.append("</a>");
			dataLink = pathBuilder.toString();
		}
		String testDescription = result.getMethod().getDescription();
		if (testDescription == null) {
			testDescription = dataLink;
		} else {
			testDescription = testDescription + "<br>" + dataLink;
		}
		return testDescription;
    }
}

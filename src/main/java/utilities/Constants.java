package utilities;

public class Constants {

    public static final byte SE_WAIT_IN_SECOND = 5;
    public static final String USER_PATH = System.getProperty("user.dir");
    public static final String SCREENSHOT_PATH = USER_PATH + "/Report/Screenshots";
    public static final String RESOURCES_PATH = USER_PATH + "/src/test/resources";
    public static final String DRIVER_PATH = USER_PATH + "/src/test/resources/drivers";
    public static final String CHROME_DRIVER_NAME = "chromedriver.exe";
    public static final String FIREFOX_DRIVER_NAME = "geckodriver.exe";
    public static final String REPORT_PATH = USER_PATH + "/Report";
    public static final String HTML_SUFFIX = ".html";
    public static final String NEW_LINE = System.getProperty("line.separator");
    public static final String OS = System.getProperty("os.name").toLowerCase();
    public static final String BROWSER_NAME = "browserName";
    public static final String PLATFORM_VERSION = "platformVersion";
    public static final String PLATFORM_NAME = "platformName";
    public static final String DEVICE_NAME = "deviceName";
    public static final String NEW_COMMAND_TIMEOUT = "newCommandTimeout";
    public static final String SENDKEY = "sendKeyStrategy";
    public static final String HUB_URL = "hubUrl";
  
    public static final String DRIVER_TYPE = "driverType";
    public static final String DRIVER_IE_PROPERTY = "webdriver.ie.driver";
    public static final String DRIVER_CHROME_PROPERTY = "webdriver.chrome.driver";
    public static final String LOCAL_EXECUTION = "localExecution";
    public static final String GENERATE_SCREENSHOT = "generateScreenShots";
    public static final String CONFIG_NAME = "configfile";
    public static long TIMEOUT = 40;
    public final static String SCREENSHOT_EXTENSION = ".png";

    // browser defaults
    public static final String BROWSER = "chrome";
    public static final String PLATFORM = "Windows 10";
    public static final String ENVIRONMENT = "local";
    public static String DEF_BROWSER = null;
    public static String DEF_PLATFORM = null;
    public static String DEF_ENVIRONMENT = null;

    // suite timeout defaults
    public static final int TIMEOUT_MINUTE = 60;
    public static final int TIMEOUT_ELEMENT = 10;
}


package lib.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.Constants;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CreateDriver {

    // local variables
    private static CreateDriver instance = null;

    private static final int IMPLICIT_TIMEOUT = 0;

    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    private ThreadLocal<String> sessionBrowser = new ThreadLocal<String>();

    private ThreadLocal<String> sessionPlatform = new ThreadLocal<String>();

    private ThreadLocal<String> sessionVersion = new ThreadLocal<String>();

    private String getEnv = null;

    // constructor
    private CreateDriver() {}

    /**
     * getInstance method to retrieve active driver instance
     *
     * @return CreateDriver
     */
    public static CreateDriver getInstance() {
        if (instance == null) {
            instance = new CreateDriver();
        }
        return instance;
    }

    /**
     * This is the setDriver method used to create the Selenium WebDriver or AppiumDriver instance!
     *
     * @param browser
     * @param platform
     * @param environment
     * @param optPreferences
     * @throws Exception
     */
    @SafeVarargs
    public final void setDriver(String browser,
                                String platform,
                                String environment,
                                Map<String, Object>... optPreferences) throws Exception {
        DesiredCapabilities caps = null;

        switch (browser) {
            case "firefox":
                caps = DesiredCapabilities.firefox();
                FirefoxOptions ffOpts = new FirefoxOptions();
                FirefoxProfile ffProfile = new FirefoxProfile();
                ffProfile.setPreference("browser.autofocus", true);
                caps.setCapability(FirefoxDriver.PROFILE, ffProfile);
                caps.setCapability(FirefoxDriver.MARIONETTE, true);
                if (environment.equalsIgnoreCase("local") ) {
                    System.setProperty("webdriver.gecko.driver",
                            getDriverResourcePath(platform, Constants.DRIVER_PATH, Constants.FIREFOX_DRIVER_NAME));
                    webDriver.set(new FirefoxDriver(ffOpts.merge(caps)));
                }
                break;
            case "chrome":
                caps = DesiredCapabilities.chrome();
                ChromeOptions chOptions = new ChromeOptions();
                Map<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("credentials_enable_service", false);
                chOptions.setExperimentalOption("prefs", chromePrefs);
                chOptions.addArguments("--disable-plugins", "--disable-extensions", "--disable-popup-blocking");
                caps.setCapability(ChromeOptions.CAPABILITY, chOptions);
                caps.setCapability("applicationCacheEnabled", false);
                if (environment.equalsIgnoreCase("local") ) {
                    System.setProperty("webdriver.chrome.driver",
                            getDriverResourcePath(platform, Constants.DRIVER_PATH ,Constants.CHROME_DRIVER_NAME));
                    webDriver.set(new ChromeDriver(chOptions.merge(caps)));
                }
                break;
           
        }

        getEnv = environment;
        sessionId.set(((RemoteWebDriver) webDriver.get()).getSessionId().toString());
        sessionBrowser.set(caps.getBrowserName());
        sessionVersion.set(caps.getVersion());
        sessionPlatform.set(platform);

        System.out.println("\n*** TEST ENVIRONMENT = "
                + getSessionBrowser().toUpperCase()
                + "/" + getSessionPlatform().toUpperCase()
                + "/" + getEnv.toUpperCase()
                + "/Selenium Version= 3.7.1"
                + "/Session ID=" + getSessionId() + "\n");

        getDriver().manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
    }

    /**
     * overloaded setDriver method to switch driver to specific WebDriver if running concurrent drivers
     *
     * @param driver instance to switch to
     */
    public void setDriver(WebDriver driver) {
        webDriver.set(driver);

        sessionId.set(((RemoteWebDriver) webDriver.get()).getSessionId().toString());

        sessionBrowser.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getBrowserName());

        sessionPlatform.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getPlatform().toString());

        //setBrowserHandle(getDriver().getWindowHandle());
    }

 
    /**
     * getDriver method will retrieve the active WebDriver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return webDriver.get();
    }


    /**
     * driverWait method pauses the driver in seconds
     *
     * @param seconds to pause
     */
    public void driverWait(long seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            // do something
            e.printStackTrace();
        }
    }

    /**
     *  driverRefresh method reloads the current browser page
     */
    public void driverRefresh() {
        getDriver().navigate().refresh();
    }

    /**
     *  closeDriver method quits the current active driver
     */
    public void closeDriver() {
        try {
            getDriver().quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  getSessionId method gets the browser or mobile id of the active session
     *
     * @return String
     */
    public String getSessionId() {
        return sessionId.get();
    }

    /**
     * getSessionBrowser method gets the browser or mobile type of the active session
     *
     * @return String
     */
    public String getSessionBrowser() {
        return sessionBrowser.get();
    }

    /**
     * getSessionVersion method gets the browser or mobile version of the active session
     *
     * @return String
     */
    public String getSessionVersion() {
        return sessionVersion.get();
    }

    /**
     * getSessionPlatform method gets the browser or mobile platform of the active session
     *
     * @return String
     */
    public String getSessionPlatform() {
        return sessionPlatform.get();
    }

    public String getGetEnv() {
        return getEnv;
    }

    private String getDriverResourcePath(String platform, String path, String name) {
        if (platform.equals("Linux")) {
            return Paths.get(path, "linux", name.substring(0, name.lastIndexOf("."))).toString();
        }

        return Paths.get(path, name).toString();
    }
}

package lib.driver;

import lib.enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.Constants;
import utilities.parse.ReadWriteJson;

import java.util.concurrent.TimeUnit;

public class DriverManager {
	//public static WebDriver driver;
    public static ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<RemoteWebDriver>();

    /**
     * Build a "local" browser instance web driver
     */
    @BeforeMethod()
    public static WebDriver buildDriver() {
        WebDriver driver =  DriverType.of(getDriverType()).getInitDriverSupplier().get();
    	setWebDriver(driver);
    	getWebDriver().manage().timeouts().implicitlyWait(Constants.SE_WAIT_IN_SECOND, TimeUnit.SECONDS);
    	getWebDriver().manage().window().maximize();
        return getWebDriver();
    }

    @AfterMethod
    public static void closeDriver() {
        getWebDriver().quit();
    }

    private static String getDriverType() {
        return ReadWriteJson.getValueFromConfigFile(Constants.DRIVER_TYPE);
    }
    
    public static ThreadLocal<RemoteWebDriver> setWebDriver(WebDriver driver){
    	
    	webDriver.set((RemoteWebDriver) driver);
    	return webDriver;
    }
    
    public static WebDriver getWebDriver(){
    	return webDriver.get();
    }

}

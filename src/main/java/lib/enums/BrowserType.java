package lib.enums;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.Constants;
import utilities.parse.ReadWriteJson;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.openqa.selenium.firefox.FirefoxDriver.SystemProperty.DRIVER_XPI_PROPERTY;
import static org.openqa.selenium.ie.InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING;
import static org.openqa.selenium.ie.InternetExplorerDriver.IGNORE_ZOOM_SETTING;
import static org.openqa.selenium.ie.InternetExplorerDriver.REQUIRE_WINDOW_FOCUS;
import static org.openqa.selenium.remote.CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR;

public enum BrowserType {
    FIREFOX("firefox") {
        @Override
        public WebDriver buildWebDriver(Platform platform) throws MalformedURLException {
            DesiredCapabilities firefoxCapabilities = new DesiredCapabilities();
            firefoxCapabilities.setJavascriptEnabled(true);
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 0);
            profile.setPreference("browser.download.manager.showWhenStarting", false);
            profile.setPreference("browser.download.manager.focusWhenStarting", false);
            profile.setPreference("browser.download.useDownloadDir", true);
            profile.setPreference("browser.helperApps.alwaysAsk.force", false);
            profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
            profile.setPreference("browser.download.manager.closeWhenDone", true);
            profile.setPreference("browser.download.manager.showAlertOnComplete", false);
            profile.setPreference("browser.download.manager.useWindow", false);
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
            profile.setPreference("security.enable_java", true);
            profile.setPreference("plugin.state.java", 2);
            firefoxCapabilities.setCapability(FirefoxDriver.MARIONETTE, true);
            firefoxCapabilities.setCapability(FirefoxDriver.PROFILE, profile);

            if (StringUtils.equals(ReadWriteJson.getValueFromConfigFile(Constants.LOCAL_EXECUTION), "true")) {
                System.setProperty("webdriver.gecko.driver",
                        getDriverResourcePath(platform, Constants.DRIVER_PATH ,Constants.FIREFOX_DRIVER_NAME));
                return new FirefoxDriver();
            } else {
                System.setProperty("webdriver.gecko.driver",
                        getDriverResourcePath(platform, Constants.DRIVER_PATH ,Constants.FIREFOX_DRIVER_NAME));
                return new RemoteWebDriver(
                        new URL(ReadWriteJson.getValueFromConfigFile(Constants.HUB_URL)),
                        firefoxCapabilities);
                //launchGridPlatform();
            }
        }
    },
    CHROME("chrome") {
        @Override
        public WebDriver buildWebDriver(Platform platform) throws MalformedURLException {
            DesiredCapabilities chromeCapabilities = new DesiredCapabilities();
            chromeCapabilities.setCapability("chrome.switches", Arrays.asList("--ignore-ssl-errors=yes"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-extensions");
            options.addArguments("--start-maximized");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--allow-insecure-localhost=yes");
            options.addArguments("--ignore-urlfetcher-cert-requests=yes");
            options.addArguments("--disable-infobars");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("credentials_enable_service", false);
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--test-type");
            chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
            chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, false);
            chromeCapabilities.setCapability(UNEXPECTED_ALERT_BEHAVIOUR, false);
            chromeCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
            chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            chromeCapabilities.setCapability("browserstack.debug", true);

            if (StringUtils.equals(ReadWriteJson.getValueFromConfigFile(Constants.LOCAL_EXECUTION), "true")) {
                System.setProperty(Constants.DRIVER_CHROME_PROPERTY,
                        getDriverResourcePath(platform, Constants.DRIVER_PATH ,Constants.CHROME_DRIVER_NAME));
                return new ChromeDriver(options);
            } else {
                System.setProperty(
                        Constants.DRIVER_CHROME_PROPERTY,
                        getDriverResourcePath(platform, Constants.DRIVER_PATH ,Constants.CHROME_DRIVER_NAME));
                return new RemoteWebDriver(
                        new URL(ReadWriteJson.getValueFromConfigFile(Constants.HUB_URL)),
                        chromeCapabilities);
                //launchGridPlatform();
            }
        }
    };
  

    private static final Map<String, BrowserType> BROWSER_TYPE_MAP = Arrays.stream(BrowserType.values())
            .collect(Collectors.toMap(BrowserType::getKey, e -> e));

    private String key;

    public String getKey() {
        return this.key;
    }

    BrowserType(final String key) {
        this.key = key;
    }

    public static Optional<BrowserType> instanceByKey(String key) {
        return Optional.of(BROWSER_TYPE_MAP.get(key));
    }

    /**
     * The method is used for build the web driver for each browser type
     * @return
     */
    public WebDriver buildWebDriver(Platform platform) throws MalformedURLException {
        return null;
    }

    public String getDriverResourcePath(Platform platform, String path, String name) {
   /*     if (platform == Platform.LINUX) {
            return Paths.get(path, "linux", name.substring(0, name.lastIndexOf("."))).toString();
        }
*/
        return Paths.get(path, name).toString();
    }
}

package lib.driver;

import lib.enums.BrowserType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import utilities.Constants;
import utilities.parse.ReadWriteJson;

import java.net.MalformedURLException;
import java.util.Optional;

public class SeleniumConfig implements ISeleniumConfig {

    private static final String BROWSER_TYPE = ReadWriteJson.getValueFromConfigFile(Constants.BROWSER_NAME);
    private static final Platform platform = Platform.getCurrent();

    @Override
    public WebDriver getWebDriver() {
        Optional<BrowserType> browserTypeOpt;
        try {
            browserTypeOpt =  BrowserType.instanceByKey(BROWSER_TYPE);
            if (!browserTypeOpt.isPresent()) {
                throw new RuntimeException("The web browser is not support, please check again.");
            }

            return browserTypeOpt.get().buildWebDriver(platform);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

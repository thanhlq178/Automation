package lib.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.Constants;
import utilities.parse.ReadWriteJson;

public interface ISeleniumConfig {

    WebDriver getWebDriver();

    default DesiredCapabilities buildDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, ReadWriteJson.getValueFromConfigFile(Constants.BROWSER_NAME));
        capabilities.setCapability(CapabilityType.VERSION, ReadWriteJson.getValueFromConfigFile(Constants.PLATFORM_VERSION));
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, ReadWriteJson.getValueFromConfigFile(Constants.PLATFORM_NAME));
        capabilities.setCapability(Constants.NEW_COMMAND_TIMEOUT, ReadWriteJson.getValueFromConfigFile(Constants.NEW_COMMAND_TIMEOUT));
        capabilities.setCapability(Constants.SENDKEY, ReadWriteJson.getValueFromConfigFile(Constants.SENDKEY));
        capabilities.setCapability(Constants.HUB_URL, ReadWriteJson.getValueFromConfigFile(Constants.HUB_URL));
        return capabilities;
    }
}

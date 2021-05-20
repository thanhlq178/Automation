package TestCase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;


public class testMobile {
    public AndroidDriver<MobileElement> driver;
    public WebDriverWait                wait;

    //Elements By
    By btn_startChat    = By.id("com.google.android.apps.messaging:id/start_new_conversation_button");
    By tb_inputPhoneNumber = By.id("com.google.android.apps.messaging:id/recipient_text_view");
    By lb_contactName   = By.id("com.google.android.apps.messaging:id/contact_name");
    By tb_inputText      = By.id("com.google.android.apps.messaging:id/compose_message_text");
    By btn_sendMessage   = By.id("com.google.android.apps.messaging:id/send_message_button_icon");
    By lb_messageStatus   = By.id("com.google.android.apps.messaging:id/message_status");


    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices"
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.google.android.apps.messaging");
        caps.setCapability("appActivity", "com.google.android.apps.messaging.ui.ConversationListActivity");
        caps.setCapability("noReset", "true");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void basicTest() throws InterruptedException {
        //Click Start Chat
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_startChat)).click();

        //Input phone number
        wait.until(ExpectedConditions.visibilityOfElementLocated(tb_inputPhoneNumber)).sendKeys("0988888888");
        wait.until(ExpectedConditions.visibilityOfElementLocated(tb_inputPhoneNumber)).click();
        Thread.sleep(5000);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

        //Input message
        wait.until(ExpectedConditions.visibilityOfElementLocated(tb_inputText)).sendKeys("Hello");
        //Click send message
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_sendMessage)).click();
        //Do a simple assertion
        Thread.sleep(5000);
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(lb_messageStatus)).getText();
        Assert.assertTrue(message.contains("SMS"));
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

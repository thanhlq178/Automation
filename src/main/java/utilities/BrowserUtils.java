package utilities;

import lib.driver.CreateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils {

    /**
     * waitFor method to poll page title
     *
     * @param title
     * @param timer
     * @throws Exception
     */
    public static void waitFor(String title, int timer) throws Exception {

        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);

        exists.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));
    }

    /**
     * waitFor method to wait up to a designated period before throwing exception (static locator)
     *
     * @param element
     * @param timer
     * @throws Exception
     */
    public static void waitFor(WebElement element, int timer) throws Exception {

        WebDriver dirver = CreateDriver.getInstance().getDriver();
        // wait for the static element to appear
        WebDriverWait exists = new WebDriverWait(dirver, timer);

        exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    /**
     * waitFor method to wait up to a designated period before throwing exception (dynamic locator)
     *
     * @param by
     * @param timer
     * @throws Exception
     */
    public static void waitFor(By by, int timer) throws Exception {

        WebDriver dirver = CreateDriver.getInstance().getDriver();
        // wait for the dynamic element to appear
        WebDriverWait exists = new WebDriverWait(dirver, timer);

        // examples: By.id(id),By.name(name),By.xpath(locator),
        // By.cssSelector(css)
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(by)));
    }

    /**
     * waitForClickable method to poll for clickable
     *
     * @param by
     * @param timer
     * @throws Exception
     */
    public static void waitForClickable(By by, int timer) throws Exception {

        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);

        exists.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(by)));
    }

    /**
     * waitForClickable method to poll for clickable
     *
     * @param element
     * @param timer
     * @throws Exception
     */
    public static void waitForClickable(WebElement element, int timer) throws Exception {

        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);

        exists.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }

    /**
     * waitForURL method to poll page URL
     *
     * @param url
     * @param timer
     * @throws Exception
     */
    public static void waitForURL(String url, int timer) throws Exception {

        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);

        exists.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(url)));
    }

    /**
     * elementExists - wrapper around the WebDriverWait method to
     * return true or false
     *
     * @param element
     * @param timer
     * @throws Exception
     */
    public static boolean elementExists(WebElement element, int timer) {
        try {
            WebDriver driver = CreateDriver.getInstance().getDriver();
            WebDriverWait exists = new WebDriverWait(driver, timer);

            exists.until(ExpectedConditions.refreshed(
                    ExpectedConditions.visibilityOf(element)));
            return true;
        }

        catch (StaleElementReferenceException | TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
}

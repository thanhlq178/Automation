package lib.action;

import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.utils.ExceptionUtil;
import lib.driver.DriverManager;
import lib.services.Logger;
import lib.services.Logger.LogLevel;
import utilities.Constants;

public class Action {
	protected static WebDriver driver;
	protected WebDriverWait explicitWait;

	public WebDriver getDriver() {
		return this.driver = DriverManager.getWebDriver();
	}

	// Constructor
	public Action(final WebDriver driver) {
		this.driver = driver;
		explicitWait = new WebDriverWait(driver, Constants.SE_WAIT_IN_SECOND);
	}
	
	public Action() {
		explicitWait = new WebDriverWait(driver, Constants.SE_WAIT_IN_SECOND);
	}

	/**
	 * Click on element
	 * @param element
	 */
	public void click(WebElement element) {
		element.click();
		System.out.println("Click on element");
	}

	/**
	 * Type text
	 * @param element
	 * @param text
	 */
	public void type(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
		System.out.println("Type text");
	}

	/**
	 * Verify element present
	 * @param element
	 */
	public void verifyElementIsPresent(WebElement element) {
		Boolean isElementPresent = element.isDisplayed();
		if (isElementPresent == true) {
			System.out.println("Element " + element + " is present");
		} else {
			System.out.println("Element " + element + " is NOT present");
		}
	}
	/**
	 * Hover mouse to a selected element.
	 *
	 * @param element
	 *            Xpath location to selected object
	 */
	public void hoverInAElement(WebElement element) {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
		} catch (Exception ex) {
			System.out.println("Hover on element - Exception occurred : " + ex);
			throw ex;

		}
		System.out.println("Hover on element");
	}
	/**
	 * Verify element contain specific text
	 * @param element
	 * @param Expected_Text
	 */
	public void verifyElementIsContainsText(WebElement element, String Expected_Text) {
		String Actual_Text = element.getText();
		System.out.println("Verify element " +element.getText()+ " with conditions " + Expected_Text + "");
		Assert.assertEquals(Actual_Text, Expected_Text);
	}

	/**
	 * Execute javascript with element
	 * @param element
	 * @param script
	 */
	public void executeScript(WebElement element, String script) {
		((JavascriptExecutor) driver).executeScript(script, element);
		System.out.println("Execute javascript with element.");
	}

	/**
	 * Execute javascript
	 * @param script
	 */
	public void executeScript(String script) {
		((JavascriptExecutor) driver).executeScript(script);
		System.out.println("Execute javascript");
	}

	/**
	 * Wait element present
	 * @param element
	 */
	public void presenceOfElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
	}

	/**
	 * Select a option in dropdownlist by the index's value of that option.
	 * 
	 * @param element
	 *            Xpath location to selected object
	 * @param index
	 *            Index of option
	 */
	public void selectDropDownListOptionByIndex(WebElement element, String index) {
		int numberOfIndex = Integer.parseInt(index);
		try {
			Select select = new Select(element);
			select.selectByIndex(numberOfIndex);

		} catch (Exception ex) {
			System.out.println("Can't select option by index" + ex.getMessage());
		}
		System.out.println("Selected option by index");
	}

	/**
	 * Choose the option of select tag through the label (visible text)
	 * 
	 * @param element
	 *            Xpath location to selected object
	 * @param label
	 *            Text of option being selected
	 * @throws Exception 
	 */
	public void selectDropDownListOptionByLabel(WebElement element, String label) throws Exception {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(label);
		} catch (Exception ex) {
			System.out.println("Can't select option by visible text" + ex.getMessage());
			throw ex;
		}
		System.out.println("Selected option by visible text" + label);
	}

	/**
	 * Choose the option of select tag through the value.
	 * 
	 * @param element
	 *            Xpath location to selected object
	 * @param value
	 *            Value of option being selected
	 * @throws Exception 
	 */
	public void selectDropDownListOptionByValue(WebElement element, String value) throws Exception {
		try {
			Select select = new Select(element);
			select.selectByValue(value);
		} catch (Exception ex) {
			System.out.println("Can't select option by value" + ex.getMessage());
			throw ex;
		}
		System.out.println("Selected option by value");
	}

	/**
	 *
	 * @return
	 */
	protected Wait<WebDriver> buildFluentWait() {
		return new FluentWait<WebDriver>(this.driver).withTimeout(Duration.ofMillis(3000)).pollingEvery(Duration.ofMillis(1000))
				.ignoring(NoSuchElementException.class);
	}
	/**
	 * Click when element visible
	 * @param element
	 */
	protected void clickIfVisibilityOfElement(WebElement element) {		
		try {
			explicitWait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (ElementNotVisibleException e) {
			Logger.log(this.getClass().getName(), ExceptionUtil.getStackTrace(e), LogLevel.INFO);
		}
	}

	/**
	 * Get text of element
	 * @param element
	 * @return
	 */
	public String getText(WebElement element) {
		String text = "";
		try{
			text = element.getText();
		} catch (Exception ex) {
			System.out.println("get text element FAILED:  " + ex.getMessage());
			Logger.log(this.getClass().getName(), ExceptionUtil.getStackTrace(ex), LogLevel.FAIL);
		}
		return text;
	}

	/**
	 * Assert two objects
	 * @param expected
	 * @param actual
	 * @param description
	 */
	public void assertEquals(Object expected, Object actual, String description) {
		try {
			if (expected.equals(actual)) {
				Logger.log(this.getClass().getName(), description + " is OK", Logger.LogLevel.INFO);
				System.out.println(description + " is OK");
			} else {
				Logger.log(this.getClass().getName(), description + " is KO", Logger.LogLevel.ERROR);
				System.out.println(description + " is KO");
			}
		} catch (Exception ex) {
			System.out.println("Assertion FAILED:  " + ex.getMessage());
			Logger.log(this.getClass().getName(), ExceptionUtil.getStackTrace(ex), LogLevel.FAIL);
		}
	}

	/**
	 * Assert contains text
	 * @param expected
	 * @param actual
	 * @param description
	 */
	public void assertContains(String expected, String actual, String description) {
		try {
			if (expected.contains(actual)) {
				Logger.log(this.getClass().getName(), description, Logger.LogLevel.INFO);
			} else {
				Logger.log(this.getClass().getName(), description, Logger.LogLevel.ERROR);
			}
			System.out.println(description);
		} catch (Exception ex) {
			System.out.println("Assertion FAILED:  " + ex.getMessage());
			Logger.log(this.getClass().getName(), ExceptionUtil.getStackTrace(ex), LogLevel.FAIL);
		}
	}
}

package page.common;

import lib.action.Action;
import lib.services.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SummaryPage extends Action {

    @FindBy(how = How.XPATH, using = "//*[@class=\"btn btn-default button button-medium\"]//span")
    public WebElement btn_checkOutPopup;

    @FindBy(how = How.XPATH, using = "//*[@class=\"cart_navigation clearfix\"]//*[contains(text(),\"Proceed to checkout\")]")
    public WebElement btn_checkOut;

    public SummaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SummaryPage clickButtonProceedCheckOutInPopup() {
      	click(btn_checkOutPopup);
        Logger.log(this.getClass().getName(), "click Button Proceed CheckOut in Pop up", Logger.LogLevel.INFO);
        return this;
    }

    public SummaryPage clickButtonProceedCheckOut() {
        click(btn_checkOut);
        Logger.log(this.getClass().getName(), "click Button Proceed CheckOut", Logger.LogLevel.INFO);
        return this;
    }

  
}

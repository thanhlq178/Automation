package page.common;

import lib.action.Action;
import lib.services.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddressPage extends Action {

    @FindBy(how = How.XPATH, using = "//*[@name=\"processAddress\"]")
    public WebElement btn_checkOut;

    public AddressPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public AddressPage clickButtonProceedCheckOut() {
        click(btn_checkOut);
        Logger.log(this.getClass().getName(), "click Button Proceed CheckOut", Logger.LogLevel.INFO);
        return this;
    }

}

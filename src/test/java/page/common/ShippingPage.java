package page.common;

import lib.action.Action;
import lib.services.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ShippingPage extends Action {

    @FindBy(how = How.XPATH, using = "//*[@name=\"processCarrier\"]")
    public WebElement btn_checkOut;

    @FindBy(how = How.XPATH, using = "//*[@id=\"cgv\"]")
    public WebElement checkbox_agreeTermOfService;


    public ShippingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ShippingPage clickButtonProceedCheckOut() {
        click(btn_checkOut);
        Logger.log(this.getClass().getName(), "click Button Proceed CheckOut", Logger.LogLevel.INFO);
        return this;
    }

    public ShippingPage clickCheckBoxAgreeTermServices() {
        click(checkbox_agreeTermOfService);
        Logger.log(this.getClass().getName(), "click CheckBox Agree Term Services", Logger.LogLevel.INFO);
        return this;
    }



  
}

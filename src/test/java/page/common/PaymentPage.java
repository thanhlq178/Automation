package page.common;

import lib.action.Action;
import lib.services.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends Action {

    @FindBy(how = How.XPATH, using = "//*[@class=\"cheque\"]")
    public WebElement btn_selectPaymentCheque;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),\"I confirm my order\")]")
    public WebElement btn_confirmOrder;

    @FindBy(how = How.XPATH, using = "//*[@class=\"alert alert-success\"]")
    public WebElement lb_successfulOrder;

    public PaymentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public PaymentPage clickSelectPaymentCheque() {
        click(btn_selectPaymentCheque);
        Logger.log(this.getClass().getName(), "clickSelectPaymentCheque", Logger.LogLevel.INFO);
        return this;
    }

    public PaymentPage clickButtonConfirmOrder() {
        click(btn_confirmOrder);
        Logger.log(this.getClass().getName(), "click Button Confirm Order", Logger.LogLevel.INFO);
        return this;
    }

    public PaymentPage verifyOrderSuccessfully(String wordingMessage) {
        verifyElementIsContainsText(lb_successfulOrder,wordingMessage);
        Logger.log(this.getClass().getName(), "Verify wording of message successfully", Logger.LogLevel.INFO);
        return this;
    }




  
}

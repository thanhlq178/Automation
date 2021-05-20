package page.common;

import lib.action.Action;
import lib.services.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.parse.ReadWriteJson;

public class HomePage extends Action {

    @FindBy(how = How.XPATH, using = "//a[@class='login']")
    public WebElement btn_signIn;

    @FindBy(how = How.XPATH, using = "//a[@title=\"Women\"]")
    public WebElement lb_menuWomen;

    @FindBy(how = How.XPATH, using = "//a[@title=\"Women\"]/following-sibling::*//a[@title=\"T-shirts\"]")
    public WebElement lb_subMenuTShirt;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage openHomePage() {
        String url = ReadWriteJson.getValueFromConfigFile("url");
        getDriver().get(url);
        Logger.log(this.getClass().getName(), "Open page: " + url, Logger.LogLevel.INFO);
        return this;
    }
    
    public HomePage goToSignInPage() {
        click(btn_signIn);
        Logger.log(this.getClass().getName(), "Go to Sign In page", Logger.LogLevel.INFO);
        return this;
    }

    public HomePage hoverMouseOnMenuWomen() {
        hoverInAElement(lb_menuWomen);
        Logger.log(this.getClass().getName(), "hover Mouse On Menu Women", Logger.LogLevel.INFO);
        return this;
    }

    public HomePage clickLabelTShirtInSubmenu() {
        click(lb_subMenuTShirt);
        Logger.log(this.getClass().getName(), "click Label TShirt In Submenu", Logger.LogLevel.INFO);
        return this;
    }
  

}

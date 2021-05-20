package page.common;

import lib.action.Action;
import lib.services.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Authentication extends Action {

    @FindBy(how = How.XPATH, using = "//input[@id='email']")
    public WebElement tb_email;
    
    @FindBy(how = How.XPATH, using = "//input[@id='passwd']")
    public WebElement tb_password;
    
    @FindBy(how = How.XPATH, using = "//button[@id='SubmitLogin']")
    public WebElement btn_signIn;
       
    
    public Authentication(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Authentication clickButtonSignIn() {
      	click(btn_signIn);
        Logger.log(this.getClass().getName(), "Click on button Sign in", Logger.LogLevel.INFO);
        return this;
    }
 
    public Authentication inputEmail(String email) {
		type(tb_email, email);
        Logger.log(this.getClass().getName(), "Input Email", Logger.LogLevel.INFO);
        return this;
    }
    public Authentication inputPassword(String passwd) {
		type(tb_password, passwd);
        Logger.log(this.getClass().getName(), "Input Password", Logger.LogLevel.INFO);
        return this;
    }
       
  
}

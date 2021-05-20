package TestCase;

import lib.driver.DriverManager;

import org.testng.annotations.Test;

import page.common.*;
import utilities.data.InjectData;
import utilities.parse.ReadWriteJson;

import static utilities.parse.ReadWriteJson.getValueFromDataFile;

public class Test01 extends DriverManager {

	@InjectData(json = "/data/login/login.json")
	@Test(description = "Test Log in")
	public void test_Login() throws Exception {
		HomePage homePage = new HomePage(getWebDriver());
		Authentication loginPage = new Authentication(getWebDriver());
		ProductPage productPage = new ProductPage(getWebDriver());
		SummaryPage summaryPage = new SummaryPage(getWebDriver());
		AddressPage addressPage = new AddressPage(getWebDriver());
		ShippingPage shippingPage = new ShippingPage(getWebDriver());
		PaymentPage paymentPage = new PaymentPage(getWebDriver());


		homePage.openHomePage().goToSignInPage();
		loginPage.inputEmail(getValueFromDataFile("email"))
				.inputPassword(getValueFromDataFile("passwd")).clickButtonSignIn();
		homePage.hoverMouseOnMenuWomen().clickLabelTShirtInSubmenu();
		productPage.hoverOnProductImage().clickButtonMore().clickButtonPlusQuantity().selectSizeByIndex("2").clickButtonBlueColor()
				.clickButtonAddToCart();
		summaryPage.clickButtonProceedCheckOutInPopup().clickButtonProceedCheckOut();
		addressPage.clickButtonProceedCheckOut();
		shippingPage.clickCheckBoxAgreeTermServices().clickButtonProceedCheckOut();
		paymentPage.clickSelectPaymentCheque().clickButtonConfirmOrder().
				verifyOrderSuccessfully(getValueFromDataFile("wordingOrderSuccess"));
	}
}

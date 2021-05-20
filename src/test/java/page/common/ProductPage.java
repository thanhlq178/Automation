package page.common;

import lib.action.Action;
import lib.services.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends Action {

	@FindBy(how = How.XPATH, using = "//*[@class=\"product-image-container\"]//img")
	public WebElement btn_imageOfProduct;

	@FindBy(how = How.XPATH, using = "//a[@title=\"View\"]/span")
	public WebElement btn_more;

	@FindBy(how = How.XPATH, using = "//i[@class=\"icon-plus\"]")
	public WebElement btn_plusQuantity;

	@FindBy(how = How.XPATH, using = "//*[@id=\"group_1\"]")
	public WebElement dropdown_Size;

	@FindBy(how = How.XPATH, using = "//*[@title=\"Blue\"]")
	public WebElement btn_blueColor;

	@FindBy(how = How.XPATH, using = "//*[@id=\"add_to_cart\"]//span")
	public WebElement btn_addToCart;



	public ProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public ProductPage hoverOnProductImage() {
		hoverInAElement(btn_imageOfProduct);
		Logger.log(this.getClass().getName(), "hover On Product Image", Logger.LogLevel.INFO);
		return this;
	}

	public ProductPage clickButtonMore() {
		click(btn_more);
		Logger.log(this.getClass().getName(), "Click Button More", Logger.LogLevel.INFO);
		return this;
	}

	public ProductPage clickButtonPlusQuantity() {
		click(btn_plusQuantity);
		Logger.log(this.getClass().getName(), "click Button Plus Quantity", Logger.LogLevel.INFO);
		return this;
	}

	public ProductPage selectSizeByIndex(String indexNumber) {
		selectDropDownListOptionByIndex(dropdown_Size, indexNumber);
		Logger.log(this.getClass().getName(), "select Size By Index", Logger.LogLevel.INFO);
		return this;
	}

	public ProductPage clickButtonBlueColor() {
		click(btn_blueColor);
		Logger.log(this.getClass().getName(), "click Button Blue Color", Logger.LogLevel.INFO);
		return this;
	}

	public ProductPage clickButtonAddToCart() {
		click(btn_addToCart);
		Logger.log(this.getClass().getName(), "click Button Add To Cart", Logger.LogLevel.INFO);
		return this;
	}



}

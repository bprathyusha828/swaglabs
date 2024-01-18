package pages;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout_Page2 {
	WebDriverWait wait;
	WebDriver driver;
	public Checkout_Page2(WebDriver wd,WebDriverWait w) {
		driver =wd;
		wait= w;
		PageFactory.initElements(driver, this);
		
	}
@FindBy(xpath="//span[text()='Checkout: Overview']") private WebElement checkoutPage2DashBoard;
@FindBy(xpath="//div[@class='cart_quantity_label']") private WebElement quantityLabel;
@FindBy(xpath="//div[@class='cart_desc_label']")private WebElement descriptionLabel;
@FindBy(xpath="//button[@id='cancel']")private WebElement CheckoutPage2CancelButton;
@FindBy(xpath="//button[@id='finish']")private WebElement finishButton;
@FindBy(xpath="//div[@class='cart_item_label']")private List<WebElement> cartItems;
@FindBy(xpath="//div[text()='Payment Information']")private WebElement paymentInformationText;
@FindBy(xpath="//div[text()='Shipping Information']")private WebElement shippingInformationText;
@FindBy(xpath="//div[text()='Price Total']")private WebElement priceTotal;
@FindBy(xpath="//div[@class='summary_value_label']")private List<WebElement> summaryValueLabels;
@FindBy(xpath="//button[@id='react-burger-menu-btn']")private WebElement menuButton;
@FindBy(xpath="//a[@id='reset_sidebar_link']") private WebElement ResetAppState;
@FindBy(xpath="//a[@id='inventory_sidebar_link']")private WebElement AllItems;

public void verifyCheckoutPage2()
{
	String url= driver.getCurrentUrl();
	Assert.assertEquals(url, "https://www.saucedemo.com/checkout-step-two.html");
	Assert.assertEquals(checkoutPage2DashBoard.getText(), "Checkout: Overview");
}
public void verifyCheckoutSummaryInfo()
{
	for(WebElement cartItem:cartItems)
	{
		cartItem.isDisplayed();
	}
	Assert.assertEquals(paymentInformationText.getText(), "Payment Information");
	Assert.assertEquals(shippingInformationText.getText(),"Shipping Information" );
	for(WebElement summaryValueLable:summaryValueLabels)
	{
		summaryValueLable.isDisplayed();
	}
	}

public void verifyCheckoutPage2FinishButton()
{
	verifyCheckoutPage2();
	verifyCheckoutSummaryInfo();
	finishButton.click();
	String url=driver.getCurrentUrl();
	Assert.assertEquals(url, "https://www.saucedemo.com/checkout-complete.html");
	
}
	
public void verifyCheckoutPage2CancelButton() throws InterruptedException
{
	verifyCheckoutPage2();
	verifyCheckoutSummaryInfo();
	Thread.sleep(3000);
	wait.until(ExpectedConditions.visibilityOf(CheckoutPage2CancelButton));
	CheckoutPage2CancelButton.click();
	String url=driver.getCurrentUrl();
	Assert.assertEquals(url, "https://www.saucedemo.com/inventory.html");
}
public void resetAppStateAndGoToAllItemsPage()
{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	wait.until(ExpectedConditions.visibilityOf(menuButton));
menuButton.click();
wait.until(ExpectedConditions.visibilityOf(ResetAppState));
ResetAppState.click();
AllItems.click();
}
public void clickOnAllItems()
{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	wait.until(ExpectedConditions.visibilityOf(menuButton));
menuButton.click();
AllItems.click();
}
}

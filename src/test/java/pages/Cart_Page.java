package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;





public class Cart_Page {
	WebDriverWait wait;
	WebDriver driver;
	public  Cart_Page(WebDriver wd,WebDriverWait w) {
		driver =wd;
		wait= w;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//a[@class='shopping_cart_link']")private WebElement cart;
	@FindBy(xpath="//span[@class='title']") private WebElement cartDashBoard;
	@FindBy(xpath="//div[@class='cart_quantity_label']") private WebElement quantityLabel;
	@FindBy(xpath="//div[@class='cart_desc_label']")private WebElement descriptionLabel;
	@FindBy(xpath="//div[@class='cart_item']")public List<WebElement> cartItemList;
	@FindBy(xpath="//button[@class='btn btn_secondary btn_small cart_button']")private List<WebElement> cartItemRemoveButton;
	@FindBy(xpath="//button[@id='continue-shopping']")public WebElement continueShopping;
	@FindBy(xpath="//button[@id='checkout']") private WebElement checkOutButton;
	
	
	public void verifyCartPage()
	{
		String url= driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.saucedemo.com/cart.html");	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Assert.assertEquals(cartDashBoard.getText(), "Your Cart");	
		Assert.assertEquals(quantityLabel.getText(), "QTY");	
		Assert.assertEquals(descriptionLabel.getText(), "Description");
		
	}
	public void verifyRemoveAllItemsFromCart()
	{
		for(WebElement removeButton:cartItemRemoveButton)
		{
			removeButton.click();
			
		}
		System.out.println(cartItemRemoveButton.size());
		Assert.assertEquals(cartItemRemoveButton.size(), 0);
	}
	public void AddProducts()
	{
		Inventory_Page Ip= new Inventory_Page(this.driver,wait);
		Ip.verifyAddProductsFromInventory();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Ip.cartButtonClick();
		
		
	}
	public void verifyCartContinueShopping()
	{
		AddProducts();
		verifyCartPage();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		continueShopping.click();
		String url= driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.saucedemo.com/inventory.html");
	}
	public void ContinueShopping()
	{
		
		continueShopping.click();
	
	}
	public void verifyCartCheckOutButton()
	{
		AddProducts();
		verifyCartPage();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		checkOutButton.click();
		String url= driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.saucedemo.com/checkout-step-one.html");
	}
	
}


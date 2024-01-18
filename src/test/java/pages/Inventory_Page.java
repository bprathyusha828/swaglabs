package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Inventory_Page {
	WebDriverWait wait;
	WebDriver driver;
	public  Inventory_Page(WebDriver wd,WebDriverWait w) {
		driver =wd;
		wait= w;
		PageFactory.initElements(driver, this);
		
	}
@FindBy(xpath="//div[@class='inventory_item']") private List<WebElement> ProductsList;
@FindBy(xpath="//span[text()='Products']")private WebElement ProductsText;
@FindBy(xpath="//select[@class='product_sort_container']")private WebElement productFilter;
@FindBy(xpath="//div[@class='inventory_item_name ']")private List<WebElement> filteredProductList;
@FindBy(xpath="//div[@class='inventory_item_price']")private List<WebElement>filteredProductPriceList;
@FindBy(xpath="//a[@target=\"_blank\"]")private List<WebElement>socialMediaButtons;
@FindBy(xpath="//button[@class='btn btn_primary btn_small btn_inventory ']") private List<WebElement> addToCartButton;
@FindBy(xpath="//a[@class='shopping_cart_link']")public WebElement cart;
@FindBy(xpath="//div[@class='inventory_item']")public List<WebElement>inventoryItemList;
@FindBy(xpath="//button[@id=\"add-to-cart-sauce-labs-backpack\"]") private WebElement backPackProduct;
@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-bike-light']") private WebElement bikeLightProduct;
@FindBy(xpath="//span[@class='shopping_cart_badge']")private WebElement shoppingCartBadge;
@FindBy(xpath="//button[@id='remove-sauce-labs-backpack']")private WebElement removeBackPackProduct;
@FindBy(xpath="//button[@id='remove-sauce-labs-bike-light']")private WebElement removeBikeLightProduct;
@FindBy(xpath="//div[@class='footer_copy']")private WebElement footerText;
@FindBy(xpath="//button[@id='react-burger-menu-btn']")private WebElement menuButton;
@FindBy(xpath="//a[@id='reset_sidebar_link']") private WebElement ResetAppState;
@FindBy(id="logout_sidebar_link")private WebElement logout;
@FindBy(xpath="//a[@id='item_5_title_link']") private WebElement inventoryProduct;
@FindBy(xpath="//div[text()='Sauce Labs Fleece Jacket']")private WebElement inventoryProductName;
@FindBy(xpath="//div[@class='inventory_details_desc large_size']")private WebElement inventoryProductDesc;
@FindBy(xpath="//div[@class='inventory_details_price']") private WebElement ProductPrice;
@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-fleece-jacket']") private WebElement ProductAddToCartButton;
@FindBy(xpath="//button[@id='back-to-products']")private WebElement backToProductButton;
public void VerifyAllProdutsDisplay()
{
	Assert.assertEquals(ProductsText.getText(), "Products");
	wait.until(ExpectedConditions.visibilityOfAllElements(ProductsList));	 
	System.out.println(ProductsList.size());
	for (WebElement productName :ProductsList ) {
		productName.isDisplayed();
         System.out.println(productName.getText());        
     }
	
}
public void verifyProcutsByNameFilterZtoA()
{
	Assert.assertEquals(ProductsText.getText(), "Products");
	if(productFilter.isDisplayed()) {
	Select productSelect= new Select(productFilter);
	productSelect.selectByVisibleText("Name (Z to A)");;

	}
}
public void verifyZtoAFilterdataFromExcel() throws IOException{
	String fileName=".\\TestData\\SL_LoginData.xlsx";
	FileInputStream fileExcel = new FileInputStream(new File(fileName));
	XSSFWorkbook wb = new XSSFWorkbook(fileExcel);
	XSSFSheet sheet = wb.getSheet("Data");
	int rows=sheet.getLastRowNum();
	System.out.println("No of total rows :"+rows);
	
	for(int r=0;r<=rows;r++)
	{
		try {
			for (int i=0;i<filteredProductList.size();i++){
				String Results=filteredProductList.get(i).getText();
	System.out.println(Results);
	if(r==i) {
	Assert.assertEquals(Results,sheet.getRow(r).getCell(4).toString());
	}
		}
		}
	
				 catch (Exception e) {
					System.out.println(e.getMessage());
				}
	}
				fileExcel.close();
				wb.close();
}
public void verifyProcutsByNameFilterAtoZ()
{
	Assert.assertEquals(ProductsText.getText(), "Products");
	if(productFilter.isDisplayed()) {
	Select productSelect= new Select(productFilter);
	productSelect.selectByVisibleText("Name (A to Z)");;

	}
}
public void verifyAtoZFilterdataFromExcel() throws IOException{
	String fileName=".\\TestData\\SL_LoginData.xlsx";
	FileInputStream fileExcel = new FileInputStream(new File(fileName));
	XSSFWorkbook wb = new XSSFWorkbook(fileExcel);
	XSSFSheet sheet = wb.getSheet("Data");
	int rows=sheet.getLastRowNum();
	System.out.println("No of total rows :"+rows);
	
	for(int r=0;r<=rows;r++)
	{
		try {
			for (int i=0;i<filteredProductList.size();i++){
				String Results=filteredProductList.get(i).getText();
	System.out.println(Results);
	if(r==i) {
	Assert.assertEquals(Results,sheet.getRow(r).getCell(5).toString());
	}
		}
		}
	
				 catch (Exception e) {
					System.out.println(e.getMessage());
				}
	}
				fileExcel.close();
				wb.close();
}
public void verifyProcutFilterByPriceLowtoHigh()
{
	Assert.assertEquals(ProductsText.getText(), "Products");
	if(productFilter.isDisplayed()) {
	Select productSelect= new Select(productFilter);
	productSelect.selectByVisibleText("Price (low to high)");;

	}
}
public void verifyPriceLowtoHighFilterdataFromExcel() throws IOException{
	String fileName=".\\TestData\\SL_LoginData.xlsx";
	FileInputStream fileExcel = new FileInputStream(new File(fileName));
	XSSFWorkbook wb = new XSSFWorkbook(fileExcel);
	XSSFSheet sheet = wb.getSheet("Data");
	int rows=sheet.getLastRowNum();
	System.out.println("No of total rows :"+rows);
	
	for(int r=7;r<=rows;r++)
	{
		try {
			for (int i=0;i<filteredProductPriceList.size();i++){
				String Results=filteredProductPriceList.get(i).getText();
	System.out.println(Results);
	if(r-7==i) {
	Assert.assertEquals(Results,sheet.getRow(r).getCell(0).toString());
	}
		}
		}
	
				 catch (Exception e) {
					System.out.println(e.getMessage());
				}
	}
				fileExcel.close();
				wb.close();
}
public void verifyProcutFilterByPriceHightolow()
{
	Assert.assertEquals(ProductsText.getText(), "Products");
	if(productFilter.isDisplayed()) {
	Select productSelect= new Select(productFilter);
	productSelect.selectByVisibleText("Price (high to low)");;

	}
}
public void verifyPriceHightoLowFilterdataFromExcel() throws IOException{
	String fileName=".\\TestData\\SL_LoginData.xlsx";
	FileInputStream fileExcel = new FileInputStream(new File(fileName));
	XSSFWorkbook wb = new XSSFWorkbook(fileExcel);
	XSSFSheet sheet = wb.getSheet("Data");
	int rows=sheet.getLastRowNum();
	System.out.println("No of total rows :"+rows);
	
	for(int r=7;r<=rows;r++)
	{
		try {
			for (int i=0;i<filteredProductPriceList.size();i++){
				String Results=filteredProductPriceList.get(i).getText();
	System.out.println(Results);
	if(r-7==i) {
	Assert.assertEquals(Results,sheet.getRow(r).getCell(1).toString());
	}
		}
		}
	
				 catch (Exception e) {
					System.out.println(e.getMessage());
				}
	}
				fileExcel.close();
				wb.close();
}
public void verifySocialMediaButtons()
{
	Assert.assertEquals(ProductsText.getText(), "Products");
	wait.until(ExpectedConditions.visibilityOfAllElements(socialMediaButtons));
	for(WebElement socialMediaAccount:socialMediaButtons)
	{
		String accountName=socialMediaAccount.getText();
		System.out.println(accountName);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		if(socialMediaAccount.isEnabled()&&socialMediaAccount.isDisplayed())
		{
			socialMediaAccount.click();	
		String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
	            String subWindowHandler = null;

	            Set<String> handles = driver.getWindowHandles(); // get all window handles
	            Iterator<String> iterator = handles.iterator();
	            while (iterator.hasNext()){
	                subWindowHandler = iterator.next();
	                if(!parentWindowHandler.equals(subWindowHandler))
	                {
	                	driver.switchTo().window(subWindowHandler);
	                	System.out.println(driver.switchTo().window(subWindowHandler).getCurrentUrl());
	                	driver.close();
	            }
	                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	            driver.switchTo().window(parentWindowHandler); 
		}
	
	}

		}
	}
public void verifyOneInventoryProduct() throws IOException
{
	inventoryProduct.click();
	String url=driver.getCurrentUrl();
	Assert.assertEquals(url, "https://www.saucedemo.com/inventory-item.html?id=5");
	String fileName=".\\TestData\\SL_LoginData.xlsx";
	FileInputStream fileExcel = new FileInputStream(new File(fileName));
	XSSFWorkbook wb = new XSSFWorkbook(fileExcel);
	XSSFSheet sheet = wb.getSheet("Data");
	try {
		String prosuctName= sheet.getRow(7).getCell(4).getStringCellValue();	
		Assert.assertEquals(inventoryProductName.getText(), prosuctName);
		String productDesc=sheet.getRow(8).getCell(4).getStringCellValue();
		Assert.assertEquals(inventoryProductDesc.getText(),productDesc );
		
		String price=sheet.getRow(9).getCell(4).getStringCellValue();
		Assert.assertEquals(ProductPrice.getText(), price);
	Assert.assertTrue(ProductAddToCartButton.isDisplayed());
		backToProductButton.click();
		String url1=driver.getCurrentUrl();
		Assert.assertEquals(url1, "https://www.saucedemo.com/inventory.html");
			}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	fileExcel.close();
	wb.close();
		}
	
	
	;
public void verifyAddAllProductsToCart()
{
	
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	for(WebElement ProductAddToCart :addToCartButton)
	{
		ProductAddToCart.click();
	}
	countInventoryProducts();

	 
}
public void cartButtonClick()
{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	cart.click();	
}
public  int countInventoryProducts()
{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	int countofinventoryproducts=inventoryItemList.size();
	System.out.println(inventoryItemList.size());
	return countofinventoryproducts;
	
}
public void verifyAddProductsFromInventory()
{

backPackProduct.click();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
bikeLightProduct.click();
Assert.assertTrue(shoppingCartBadge.isDisplayed());



}
public void removeProductsFromInventory()
{
	removeBackPackProduct.click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	removeBikeLightProduct.click();
	
	Assert.assertFalse(verifyNoelement());
	
}
public boolean verifyNoelement() {
    try
    {
       if(shoppingCartBadge.isDisplayed())
       {
       return true;
       }
       return true;
    }
    catch(Exception e)
    {
         System.out.println("No element displayed");
        return false;
    }


}
public void verifyInventoryFooterTextFromExcel() throws IOException{
	String fileName=".\\TestData\\SL_LoginData.xlsx";
	FileInputStream fileExcel = new FileInputStream(new File(fileName));
	XSSFWorkbook wb = new XSSFWorkbook(fileExcel);
	XSSFSheet sheet = wb.getSheet("Data");
	System.out.println(footerText.getText());
	Assert.assertEquals(footerText.getText(),sheet.getRow(7).getCell(2).toString());
	fileExcel.close();
	wb.close();

}
public void verifyResetAppStateButton()
{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	wait.until(ExpectedConditions.visibilityOf(menuButton));
menuButton.click();
wait.until(ExpectedConditions.visibilityOf(ResetAppState));
ResetAppState.click();
Assert.assertFalse(verifyNoelement());
	logout.click();
}

}




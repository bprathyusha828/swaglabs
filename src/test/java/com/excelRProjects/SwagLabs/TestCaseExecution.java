package com.excelRProjects.SwagLabs;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Cart_Page;
import pages.Checkout_Page1;
import pages.Checkout_Page2;
import pages.Checkout_Page3;
import pages.Home_Page;
import pages.Inventory_Page;


public class TestCaseExecution {

	WebDriver driver;
	WebDriverWait wait;
	Home_Page hp;
	Inventory_Page Ip;
	Cart_Page cp;
	Checkout_Page1 CP1;
	Checkout_Page2 CP2;
	Checkout_Page3 CP3;
	@BeforeTest
public void initialisation()
{
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		hp= new Home_Page(this.driver,wait);
		cp=new Cart_Page(this.driver,wait);
        Ip=new Inventory_Page(this.driver,wait);
        CP1=new Checkout_Page1(this.driver,wait);
        CP2=new Checkout_Page2(this.driver,wait);
		CP3=new Checkout_Page3(this.driver,wait);
}
@Test(priority=1)
public void validateAllValidCredentials() throws InterruptedException, IOException
{
hp.url();
hp.dataFromExcel(".\\TestData\\SL_LoginData.xlsx");
}
@Test(priority=2)
public void validateInvalidUserName() throws InterruptedException, IOException
{
	hp.url();
	hp.verifyInvalidUserName(".\\TestData\\SL_LoginData.xlsx");
	
}
@Test(priority=3)
public void validateInvalidPassword() throws InterruptedException, IOException
{
	hp.url();
	hp.verifyInvalidPassword(".\\TestData\\SL_LoginData.xlsx");
	
}
@Test(priority=4)
public void validateBlankCredentials() throws InterruptedException, IOException
{
	hp.url();
	hp.verifyBlankCredentials();

}
@Test(priority=5)
public void validateValidLogin() throws InterruptedException, IOException
{
	hp.url();
	hp.verifyvalidLogin(".\\TestData\\SL_LoginData.xlsx");
	
}
@Test(priority=6)
public void validateAllProductsDisplay() throws InterruptedException, IOException
{



	Ip.VerifyAllProdutsDisplay();

	
}
@Test(priority=7)
public void validateProductByNameFilterZtoA() throws InterruptedException, IOException
{

	Ip.verifyProcutsByNameFilterZtoA();
	Ip.verifyZtoAFilterdataFromExcel();
}
@Test(priority=8)
public void validateProductByNameFilterAtoZ() throws InterruptedException, IOException
{
	
	
	Ip.verifyProcutsByNameFilterAtoZ();
	Ip.verifyAtoZFilterdataFromExcel();
}
@Test(priority=9)
public void validateProductFilterByPriceLowtoHigh() throws InterruptedException, IOException
{

	
	Ip.verifyProcutFilterByPriceLowtoHigh();
	Ip.verifyPriceLowtoHighFilterdataFromExcel();
}
@Test(priority=10)
public void validateProductFilterByPriceHightoLow() throws InterruptedException, IOException
{


	Ip.verifyProcutFilterByPriceHightolow();
	Ip.verifyPriceHightoLowFilterdataFromExcel();
}
@Test(priority=11)
public void validateSocialMediaButtons() throws InterruptedException, IOException
{	
	
	Ip.verifySocialMediaButtons();
}
@Test(priority=12)
public void validateInventoryPageProduct() throws IOException, InterruptedException
{

	Ip.verifyOneInventoryProduct();
}
@Test(priority=13)

	public void validateaddAllProductsToCart() throws InterruptedException, IOException
	{
	
	Ip.verifyAddAllProductsToCart();
	int ProductCount =Ip.countInventoryProducts();
	Ip.cartButtonClick();
	cp.verifyCartPage();
	Assert.assertEquals( ProductCount,cp.cartItemList.size());
		
	}
@Test(priority=14)
public void validateremoveAllItemsFromCart() throws InterruptedException, IOException
{

	cp.verifyCartPage();
	cp.verifyRemoveAllItemsFromCart();
	 cp.ContinueShopping();
}

@Test(priority=15)
public void validateAddandRemoveProductsInInventory() throws InterruptedException, IOException
{
	
	Ip.verifyAddProductsFromInventory();
	Ip.removeProductsFromInventory();
	
}

@Test(priority=16)
public void validateFooterText() throws InterruptedException, IOException
{
	
	Ip.verifyInventoryFooterTextFromExcel();
}
@Test(priority=17)
	public void validateResetAppStateFunction() throws InterruptedException, IOException 
	{
	
	cp.verifyCartContinueShopping();
	Ip.verifyResetAppStateButton();
	
	
}
@Test(priority=18)
public void validateCartPageCheckOutButton() throws InterruptedException, IOException
{
	hp.url();
	hp.verifyvalidLogin(".\\TestData\\SL_LoginData.xlsx");
	cp.verifyCartCheckOutButton();
	CP1.verifyCheckoutPage1CancelButton();
    cp.ContinueShopping();
    Ip.removeProductsFromInventory();

}
@Test(priority=19)
public void validateCkeckoutPage1ContinueButtonAndAllItemsFunction() throws InterruptedException, IOException
{
	
		cp.verifyCartCheckOutButton();
	CP1.verifyCheckoutPage1ContinueButton();
	CP2.resetAppStateAndGoToAllItemsPage();
	



}
@Test(priority=20)
public void validateCkeckoutPage1ErrorMsgsText() throws InterruptedException, IOException
{

	cp.verifyCartCheckOutButton();
	CP1.verifyCheckoutPage1ErrorMsgsText();
	
	CP2.resetAppStateAndGoToAllItemsPage();
	
}

@Test(priority=21)
public void validateCheckoutPage2FinishButton() throws InterruptedException, IOException
{
	cp.verifyCartCheckOutButton();
	CP1.verifyCheckoutPage1ContinueButton();
	CP2.verifyCheckoutPage2FinishButton();
	CP2.clickOnAllItems();
	
}
@Test(priority=22)
public void validateCheckoutPage2CancelButton() throws InterruptedException, IOException
{

	cp.verifyCartCheckOutButton();
	CP1.verifyCheckoutPage1ContinueButton();
	CP2.verifyCheckoutPage2CancelButton();
	Ip.removeProductsFromInventory();
	
}
@Test(priority=23)
public void validateCheckoutcompletePage() throws InterruptedException, IOException
{
	
	cp.verifyCartCheckOutButton();
	CP1.verifyCheckoutPage1ContinueButton();
	CP2.verifyCheckoutPage2FinishButton();
	CP3.verifyCheckoutCompletionPage();
	hp.logoutProcess();
}
}


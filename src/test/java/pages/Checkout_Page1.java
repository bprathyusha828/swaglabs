package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class Checkout_Page1 {
	WebDriverWait wait;
	WebDriver driver;
	public Checkout_Page1(WebDriver wd,WebDriverWait w) {
		driver =wd;
		wait= w;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//span[text()='Checkout: Your Information']")private WebElement checkoutDashBoard;
	@FindBy(xpath="//input[@id='first-name']") private WebElement firstName;
	@FindBy(xpath="//input[@id='last-name']") private WebElement lastName;
	@FindBy(xpath="//input[@id='postal-code']")private WebElement zipCode;
	@FindBy(xpath="//button[@id='cancel']") private WebElement cancelButton;
	@FindBy(xpath="//input[@id='continue']")private WebElement continueButton;
	@FindBy(xpath="//button[@class='error-button']")private WebElement erroeMsgTextButton;
	@FindBy(xpath="//h3[@data-test='error']")private WebElement errorMsgText;
public void verifyCheckoutPage1()
{
	String url= driver.getCurrentUrl();
	Assert.assertEquals(url, "https://www.saucedemo.com/checkout-step-one.html");
	Assert.assertEquals(checkoutDashBoard.getText(),"Checkout: Your Information" );
}
public void enterCheckoutPageDataFromExcel() throws IOException
{
	String fileName=".\\TestData\\SL_LoginData.xlsx";
	FileInputStream fileExcel = new FileInputStream(new File(fileName));
	XSSFWorkbook wb = new XSSFWorkbook(fileExcel);
	XSSFSheet sheet = wb.getSheet("Data");
	try {
		String userFirstName= sheet.getRow(9).getCell(2).getStringCellValue();	
		firstName.sendKeys(userFirstName);
		String userLastName=sheet.getRow(10).getCell(2).getStringCellValue();
		lastName.sendKeys(userLastName);
		String postalCode=String.valueOf((long)sheet.getRow(11).getCell(2).getNumericCellValue());
		zipCode.sendKeys(postalCode);
			}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	fileExcel.close();
	wb.close();
		}
public void checkoutPageContinueButton()
{
	continueButton.click();
}
	
public void verifyCheckoutPage1ContinueButton() throws IOException
{
	verifyCheckoutPage1();
	enterCheckoutPageDataFromExcel();
	checkoutPageContinueButton();
	String url= driver.getCurrentUrl();
	Assert.assertEquals(url, "https://www.saucedemo.com/checkout-step-two.html");
}
public void verifyCheckoutPage1ErrorMsgsText() throws IOException
{
	verifyCheckoutPage1();
	checkoutPageContinueButton();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	wait.until(ExpectedConditions.visibilityOfAllElements(errorMsgText));

	Assert.assertEquals(errorMsgText.getText(), "Error: First Name is required");
	erroeMsgTextButton.click();
	String fileName=".\\TestData\\SL_LoginData.xlsx";
	FileInputStream fileExcel = new FileInputStream(new File(fileName));
	XSSFWorkbook wb = new XSSFWorkbook(fileExcel);
	XSSFSheet sheet = wb.getSheet("Data");
	try {
		
		String userFirstName= sheet.getRow(9).getCell(2).getStringCellValue();	
		firstName.sendKeys(userFirstName);
		checkoutPageContinueButton();
		Assert.assertEquals(errorMsgText.getText(), "Error: Last Name is required");
		erroeMsgTextButton.click();
		String userLastName=sheet.getRow(10).getCell(2).getStringCellValue();
		lastName.sendKeys(userLastName);
		checkoutPageContinueButton();
		Assert.assertEquals(errorMsgText.getText(), "Error: Postal Code is required");
		erroeMsgTextButton.click();
		String postalCode=String.valueOf((long)sheet.getRow(11).getCell(2).getNumericCellValue());
		zipCode.sendKeys(postalCode);
		checkoutPageContinueButton();
		String url= driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.saucedemo.com/checkout-step-two.html");
		
			}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	fileExcel.close();
	wb.close();
		}

	

public void verifyCheckoutPage1CancelButton() throws IOException
{
	

	verifyCheckoutPage1();
	enterCheckoutPageDataFromExcel();
	cancelButton.click();
	String url= driver.getCurrentUrl();
	Assert.assertEquals(url, "https://www.saucedemo.com/cart.html");
	
	
}
}


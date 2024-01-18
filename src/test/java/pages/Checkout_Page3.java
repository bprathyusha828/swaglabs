package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;




public class Checkout_Page3 {
	WebDriverWait wait;
	WebDriver driver;
	public Checkout_Page3(WebDriver wd,WebDriverWait w) {
		driver =wd;
		wait= w;
		PageFactory.initElements(driver, this);
		
	}
@FindBy(xpath="//span[text()='Checkout: Complete!']")private WebElement completePageDashBoard;
@FindBy(xpath="//h2[text()='Thank you for your order!']")private WebElement orderPlacedThankYouMsg;
@FindBy(xpath="//button[@id='back-to-products']")public WebElement backHomeButton;
@FindBy(xpath="//div[@class='complete-text']")private WebElement page3OrderPlacedText;


public void verifyCheckoutCompletionPage() throws IOException
{
	String page3url=driver.getCurrentUrl();
	Assert.assertEquals(page3url, "https://www.saucedemo.com/checkout-complete.html");

	String fileName=".\\TestData\\SL_LoginData.xlsx";
	FileInputStream fileExcel = new FileInputStream(new File(fileName));
	XSSFWorkbook wb = new XSSFWorkbook(fileExcel);
	XSSFSheet sheet = wb.getSheet("Data");
	try {
		String checkoutPage3DashBoard= sheet.getRow(7).getCell(3).getStringCellValue();	
		Assert.assertEquals(completePageDashBoard.getText(), checkoutPage3DashBoard);
		String thankYouMsg=sheet.getRow(8).getCell(3).getStringCellValue();
		Assert.assertEquals(orderPlacedThankYouMsg.getText(), thankYouMsg);
		
		String orderPlacedMsg=sheet.getRow(9).getCell(3).getStringCellValue();
		Assert.assertEquals(page3OrderPlacedText.getText(), orderPlacedMsg);
	
		backHomeButton.click();
		String url=driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.saucedemo.com/inventory.html");
			}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	fileExcel.close();
	wb.close();
		}

public void backToHomeButton()
{
	backHomeButton.click();
}
}

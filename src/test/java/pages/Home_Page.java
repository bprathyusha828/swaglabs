package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Home_Page {
	WebDriverWait wait;
	WebDriver driver;
	public  Home_Page(WebDriver wd,WebDriverWait w) {
		driver =wd;
		wait= w;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(id="user-name")private WebElement userName;
	@FindBy(id="password")private WebElement password;
	@FindBy(id="login-button")public WebElement loginButton;
	@FindBy(xpath="//button[@id='react-burger-menu-btn']")private WebElement menuButton;
	@FindBy(id="logout_sidebar_link")private WebElement logout;
	@FindBy(xpath="//button[@class='error-button']")private WebElement errorMsg;
	@FindBy(xpath="//h3[@data-test='error']") private WebElement invaldUserErrorMsg;
	@FindBy(xpath="//h3[@data-test='error']") private WebElement blankCredentialsErrorMsg;
	@FindBy(xpath="//div[text()='Swag Labs']")private WebElement inventoryDashBordText;
	public void url() throws InterruptedException
	
	{
		 driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		String url= driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.saucedemo.com/");
	}
	public void dataFromExcel(String filepath) throws IOException{
	
			FileInputStream fileExcel = new FileInputStream(new File(filepath));
			XSSFWorkbook wb = new XSSFWorkbook(fileExcel);
			XSSFSheet sheet = wb.getSheet("Data");
			int rows=5;
			System.out.println("No of total rows :"+rows);
			for(int r=1;r<=rows;r++)
			{
				XSSFRow row=sheet.getRow(r);
				XSSFCell name=row.getCell(0);
				XSSFCell pwd=row.getCell(1);
				try {
				wait.until(ExpectedConditions.visibilityOf(userName));
				userName.sendKeys(name.toString());
				wait.until(ExpectedConditions.visibilityOf(password));
				password.sendKeys(pwd.toString());
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	
				wait.until(ExpectedConditions.visibilityOf(loginButton));
			loginButton.click();
			logoutProcess();
				
			}			
			

		 catch (Exception e) {
				System.out.println(e.getMessage());
			}
			}
			fileExcel.close();
			wb.close();
		
		}

public void logoutProcess() throws InterruptedException
{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(menuButton));
	menuButton.click();
	wait.until(ExpectedConditions.visibilityOf(logout));
	logout.click();
}
public void verifyInvalidUserName(String filepath) throws IOException
{
	FileInputStream fileExcel = new FileInputStream(new File(filepath));
	XSSFWorkbook wb = new XSSFWorkbook(fileExcel);
	XSSFSheet sheet = wb.getSheet("Data");
try {
	String invalidUserName= sheet.getRow(1).getCell(2).getStringCellValue();	
	userName.sendKeys(invalidUserName);
	String validPwd=sheet.getRow(1).getCell(1).getStringCellValue();
	password.sendKeys(validPwd);
	loginButton.click();
	Assert.assertEquals(invaldUserErrorMsg.getText(), "Epic sadface: Username and password do not match any user in this service");
	
}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
fileExcel.close();
wb.close();
	}
public void verifyInvalidPassword(String filepath) throws IOException
{
	
		
	
	FileInputStream fileExcel = new FileInputStream(new File(filepath));
	XSSFWorkbook wb = new XSSFWorkbook(fileExcel);
	XSSFSheet sheet = wb.getSheet("Data");
	try {
	String validUserName= sheet.getRow(1).getCell(0).getStringCellValue();	
	userName.sendKeys(validUserName);
	String invalidPwd=sheet.getRow(1).getCell(3).getStringCellValue();
	password.sendKeys(invalidPwd);
	loginButton.click();
	Assert.assertEquals(invaldUserErrorMsg.getText(), "Epic sadface: Username and password do not match any user in this service");
	
}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	fileExcel.close();
	wb.close();
	}
public void verifyBlankCredentials()
{
	loginButton.click();
	Assert.assertEquals(blankCredentialsErrorMsg.getText(),"Epic sadface: Username is required");
}
public void verifyvalidLogin(String filepath ) throws IOException
{
			
		
		FileInputStream fileExcel = new FileInputStream(new File(filepath));
		XSSFWorkbook wb = new XSSFWorkbook(fileExcel);
		XSSFSheet sheet = wb.getSheet("Data");
try {
		String validUserName= sheet.getRow(1).getCell(0).getStringCellValue();	
		userName.sendKeys(validUserName);
		String validPwd=sheet.getRow(1).getCell(1).getStringCellValue();
		password.sendKeys(validPwd);
		loginButton.click();
		String url= driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.saucedemo.com/inventory.html");
		Assert.assertEquals(inventoryDashBordText.getText(), "Swag Labs");
}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	fileExcel.close();
	wb.close();
	}

}





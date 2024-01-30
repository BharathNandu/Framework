package org.pages;

import org.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// callling the Abstract component constrcutor
		super(driver);
		// inizallization of driver
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	// WebElement useremail= driver.findElement(By.id("userEmail"));

	// WebElement password =
	// driver.findElement(By.xpath("//*[@id='userPassword']"));
	@FindBy(id = "userEmail")
	WebElement useremail;

	@FindBy(xpath = "//*[@id='userPassword']")
	WebElement userpassword;

	@FindBy(xpath = "//*[@value='Login']")
	WebElement login;
	
	
	//div[@aria-label='Incorrect email or password.']
	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")	
	WebElement error;
	
	public void landingpage(String UserID,String password)
	{
		useremail.sendKeys(UserID);
		userpassword.sendKeys(password);
		login.click();
	}
	
	public void gotoURL() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	public String getErrormessage() {
		return error.getText();
	}

}

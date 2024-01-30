package org.pages;

import org.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComfirmationPage extends AbstractComponent {

	WebDriver driver;
	
	public ComfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath= "//*[@class='hero-primary']")
	WebElement confirmationmessage;
	
	
	public String getconfirmationMessage()
	{
		return confirmationmessage.getText();
	}
			

}

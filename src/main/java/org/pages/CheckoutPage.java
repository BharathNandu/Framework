package org.pages;

import org.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css =".form-group input")
	WebElement country;
	
	@FindBy(xpath ="//*[contains(@class,'ta-item')][2]")
	WebElement selectcountry;
	
	@FindBy(css=".actions a")
	WebElement  submitbutton;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		//driver.findElement(By.cssSelector(".form-group input")).sendKeys("Ind");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		Actions act = new Actions(driver);
			act.sendKeys(country ,countryName).build().perform();
			elementvisible(results,5);
			selectcountry.click();
	}
	
	public ComfirmationPage sumbitOrder()
	{
		submitbutton.click();
		ComfirmationPage comfirmation=new ComfirmationPage(driver);
		return comfirmation;
	}

	
	
}

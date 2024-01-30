package org.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath = "//*[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	
	@FindBy(xpath ="//*[@routerlink='/dashboard/myorders']")
	WebElement myorders;
	
	
	public void elementvisible(By findBy, int time)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		List<WebElement> products = driver.findElements(findBy);
	}
	
	public void invisibilityofelement(WebElement ele,int time)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void gotoCartPage()
	{
		cartHeader.click();
	}
	public void gotoOrdersPage()
	{
		myorders.click();
	}
	

}

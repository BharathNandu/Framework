package org.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CartPage {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super();
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css = ".cartSection h3")
	List<WebElement>cartproducts;
	
	@FindBy(xpath ="//*[@class='totalRow']/button")
	WebElement checkout;
	
	
	public Boolean verifymatchofproduct(String Productname)
	{
		Boolean match = cartproducts.stream().anyMatch(cartproduct -> cartproduct.getText().equals(Productname));
		return match;
		
	}
	//driver.findElement(By.xpath("//*[@class='totalRow']/button")).click();
	public CheckoutPage checkout()
	{
		checkout.click();
		CheckoutPage checkoutp = new CheckoutPage(driver);
		return checkoutp;
		
	}
	

}

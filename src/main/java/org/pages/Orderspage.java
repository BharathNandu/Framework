package org.pages;

import java.util.List;

import org.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Orderspage extends AbstractComponent{

	
WebDriver driver;
	
	public Orderspage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	@FindBy(css = ".cartSection h3")
	List<WebElement>cartproducts;
	
	@FindBy(xpath ="//*[@class='totalRow']/button")
	WebElement checkout;
	
	@FindBy(xpath="")
	List<WebElement>productnames;
	
	public Boolean verifyOrderDisplay(String Productname)
	{
		Boolean match = cartproducts.stream().anyMatch(cartproduct -> cartproduct.getText().equals(Productname));
		return match;
		
		
	}
}

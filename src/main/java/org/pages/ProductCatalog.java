package org.pages;

import java.util.List;

import org.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalog extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement animating;
	By productBy = By.cssSelector(".mb-3");
	By addtocart = By.xpath("//*[@class='card-body']/button[2]");
	By toastmessage = By.xpath("//*[@id='toast-container']");
	//By animating = By.cssSelector(".ng-animating");
	public List<WebElement> getproductlist()
	{
		elementvisible(productBy, 5);
		return products;
		
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = products.stream().filter(
				product -> product.findElement(By.xpath("//*[@class='card-body']/h5/b")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}
	
	public void addproducttoCart(String productName)
	{
		
		WebElement prod = getProductByName(productName);
		 prod.findElement(addtocart).click();
		 elementvisible(toastmessage,5);
		 invisibilityofelement(animating,10);
		 
		 
	}
	
	
}

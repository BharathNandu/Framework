package org.testcases;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.pages.CartPage;
import org.pages.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import Base.Retry;

public class ErrorvalidationsTest extends BaseTest{
	@Test(retryAnalyzer=Retry.class)
	public void loginerror() throws IOException {
		// TODO Auto-generated method stub
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		lp.landingpage("bharath@yopmail.com", "Test@12345");
		String Errortext=lp.getErrormessage();
		
		Assert.assertEquals(Errortext, "Incorrect email or password.");
		// Able to login into url
		System.out.println(Errortext);
		
	}
	
	
	@Test(retryAnalyzer=Retry.class)
	public void productCatelogError()
	{
		String Productname = "ZARA COAT 3";
		String Country = "IND";
		
		ProductCatalog pc = new ProductCatalog(driver);
		CartPage cp = new CartPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		lp.landingpage("bharath@yopmail.com", "Test@1234");
		// Able to login into url

		List<WebElement> products = pc.getproductlist();
		// get the list of product codes from the product catelog //3
		pc.addproducttoCart(Productname);
		// add the Productname into the cart
		pc.gotoCartPage();
		// click on the cart

		Boolean match = cp.verifymatchofproduct(Productname);
		System.out.println("Is product in cart: " + match);
		Assert.assertTrue(match);

	}
	

}



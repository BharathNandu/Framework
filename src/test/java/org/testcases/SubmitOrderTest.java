package org.testcases;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.CartPage;
import org.pages.CheckoutPage;
import org.pages.ComfirmationPage;
import org.pages.LandingPage;
import org.pages.ProductCatalog;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.*;

import com.sun.net.httpserver.Authenticator.Retry;

import Base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider="getData",groups="smoke")
	public void submitOrder(HashMap<String,String> input) throws IOException, Exception{
		// TODO Auto-generated method stub
		//String Productname = "ZARA COAT 3";
		String Country = "IND";
		
		ProductCatalog pc = new ProductCatalog(driver);
		CartPage cp = new CartPage(driver);
		lp.landingpage(input.get("email"), input.get("password"));
		// Able to login into url
		Thread.sleep(5000);
		List<WebElement> products = pc.getproductlist();
		// get the list of product codes from the product catelog //3
		pc.addproducttoCart(input.get("productName"));
		// add the Productname into the cart
		pc.gotoCartPage();
		// click on the cart

		Boolean match = cp.verifymatchofproduct(input.get("productName"));
		System.out.println("Is product in cart: " + match);
		Assert.assertTrue(match);
		CheckoutPage checkoutpg = cp.checkout();
		checkoutpg.selectCountry(Country);

		ComfirmationPage cmf = checkoutpg.sumbitOrder();
		String ActualText = cmf.getconfirmationMessage();

		Assert.assertTrue(ActualText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
	
	@Test( dependsOnMethods = {"submitOrder"})
	public void orderHistory()
	{
		lp.landingpage("bharath@yopmail.com", "Test@1234");
		ProductCatalog pc = new ProductCatalog(driver);
		pc.gotoOrdersPage();
		
	}
	
	@DataProvider
	public Object[][] getData() {
		HashMap<String,String> hm = new HashMap<String,String>();
		hm.put("email", "bharath@yopmail.com");
		hm.put("password", "Test@1234");
		hm.put("productName", "ZARA COAT 3");
		
		HashMap<String,String> hm1 = new HashMap<String,String>();
		hm1.put("email", "sharath@yopmail.com");
		hm1.put("password", "Test@1234");
		hm1.put("productName", "ZARA COAT 3");
		
		return  new  Object[][] {{hm},{hm1}};
		
	}
	


}

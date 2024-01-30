package stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.pages.CartPage;
import org.pages.CheckoutPage;
import org.pages.ComfirmationPage;
import org.pages.LandingPage;
import org.pages.ProductCatalog;
import org.testng.Assert;

import Base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImp extends BaseTest{

	public LandingPage landingPage ;
	ComfirmationPage cmf;
	CheckoutPage checkoutpg;
	ProductCatalog pc = new ProductCatalog(driver);
	CartPage cp = new CartPage(driver);
	@Given ("I land on landing page")
	public void I_land_on_landing_page() throws IOException
	{
		landingPage=launchApplication();
	}
	@Given ("^I logged in with (.+) and (.+)$")
	public void I_logged_in_with_username_and_password(String username,String password)	
	{
		lp.landingpage(username,password );	
	}
	@When ("^I add the (.+) from the cart$")
	public void I_add_the_product_from_the_cart(String productName)
	{
	
		 ProductCatalog productCatalog = new ProductCatalog(driver);
	       List<WebElement> productList = productCatalog.getproductlist();
		List<WebElement> products = pc.getproductlist();
		if (products != null && !products.isEmpty()) {
            // Example: Wait for the first product to be visible
            WebElement productElement = products.get(0);
  		
		System.out.println("*****");
		// get the list of product codes from the product catelog //3
		pc.addproducttoCart(productName);
	}
	}
	@And ("^checkout (.+) and submit the order")
	public void submitorder(String productName)
	{
		pc.gotoCartPage();
		// click on the cart

		Boolean match = cp.verifymatchofproduct(productName);
		System.out.println("Is product in cart: " + match);
		Assert.assertTrue(match);
		checkoutpg = cp.checkout();
		checkoutpg.selectCountry("Ind");

		cmf = checkoutpg.sumbitOrder();
	}
	@Then ("{string} message is displayed.")
	public void message_is_displayed(String string)
	{
		String ActualText = cmf.getconfirmationMessage();

		Assert.assertTrue(ActualText.equalsIgnoreCase(string));
	}

}

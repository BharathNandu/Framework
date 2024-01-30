package org.testcases;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.LandingPage;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Productname = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		LandingPage lp = new LandingPage(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("bharath@yopmail.com");
		driver.findElement(By.xpath("//*[@id='userPassword']")).sendKeys("Test@1234");
		driver.findElement(By.xpath("//*[@value='Login']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		System.out.println(products.size());
		WebElement prod = products.stream().filter(
				product -> product.findElement(By.xpath("//*[@class='card-body']/h5/b")).getText().equals(Productname))
				.findFirst().orElse(null);
		// products.stream().forEach(s->System.out.println(s));
		System.out.println(prod);
		WebElement addtocart = prod.findElement(By.xpath("//*[@class='card-body']/button[2]"));
		addtocart.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toast-container']")));
		// ng-animating
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.xpath("//*[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartproducts.stream().anyMatch(cartproduct -> cartproduct.getText().equals(Productname));
		Assert.assertTrue(match);
		System.out.println(match);

		driver.findElement(By.xpath("//*[@class='totalRow']/button")).click();

		driver.findElement(By.cssSelector(".form-group input")).sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		// String keys = Keys.chord(Keys.SHIFT,Keys.DOWN);

		/*
		 * driver.findElement(By.cssSelector(".ta-backdrop")).sendKeys(keys);
		 * driver.findElement(By.cssSelector(".ta-backdrop")).sendKeys(keys);
		 */
		// driver.findElement(By.cssSelector(".ta-backdrop")).sendKeys(Keys.ENTER);

		driver.findElement(By.xpath("//*[contains(@class,'ta-item')][2]")).click();
		driver.findElement(By.cssSelector(".actions a")).click();
		String Actualtext= driver.findElement(By.xpath("//*[@class='hero-primary']")).getText();
		Assert.assertTrue(Actualtext.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		 driver.close();
		if (driver != null) {
		driver.quit();
		}

	}

}

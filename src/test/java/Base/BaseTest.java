package Base;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.pages.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage lp;
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream("src\\main\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName =System.getProperty("browser")!=null? System.getProperty("browser") : prop.getProperty("browser");
		
		// prop.getProperty("browser");
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))
			{
			options.addArguments("headless","--start-maximized");
			}
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			//driver.manage().window().setSize(new Dimension(1440,900));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else {
			System.out.println("Browser initalized is incorrect" + browserName);

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver();
		 lp = new LandingPage(driver);
		lp.gotoURL();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
		if (driver != null) {
			driver.quit();
		}
	}
	
	public String Screenshot(String testcaseName, WebDriver driver) throws IOException {
	TakesScreenshot src=	(TakesScreenshot)driver;
	File source=src.getScreenshotAs(OutputType.FILE);
	File des = new File(System.getenv("user.dir")+"//reports"+testcaseName +".png");
	FileUtils.copyFile(source, des);
	return System.getenv("user.dir")+"//reports"+testcaseName +".png";
	
	}
}

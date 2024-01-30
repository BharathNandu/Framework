package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", 
				glue = "stepDefinations", monochrome = true, // it gives the
																									// reaport in
																									// readable format
		plugin = { "html:target/cucmber.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}

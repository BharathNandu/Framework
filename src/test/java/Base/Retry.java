package Base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	int retryCounter =0;
	int retryLimit =2;
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(retryCounter<retryLimit)
		       if (retryCounter < retryLimit) {
	                retryCounter++;
	                
	                return true;
	            } else {
	                
	                
	                retryCounter = 0;
	            }
		return false;
	}

}

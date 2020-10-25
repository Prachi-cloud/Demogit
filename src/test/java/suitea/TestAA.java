package suitea;

import org.testng.annotations.Test;

import dataprovider.TestDataProvider;
import testbase.TestBase;

public class TestAA extends TestBase{
	
	@Test(groups = {"sanity","smoke","browsergroup1"}, dataProviderClass = TestDataProvider.class , dataProvider = "dataSuiteA")
	public void testAA(String browserName, String username, String password) throws InterruptedException
	{
		log("Starting test AA");
		log(username + " "+ password);
		Thread.sleep(2000);
		log("Ending AA");
	}

}

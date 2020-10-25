package suiteb;

import org.testng.annotations.Test;

import dataprovider.TestDataProvider;
import testbase.TestBase;

public class TestB extends TestBase{
	
	@Test(groups = {"sanity","browsergroup2"}, dataProviderClass = TestDataProvider.class , dataProvider = "dataSuiteB")
	public void testB(String username, String password) throws InterruptedException
	{
		log("Starting test B");
		log(username + " "+ password);
		Thread.sleep(2000);
		log("Ending B");
	}

}

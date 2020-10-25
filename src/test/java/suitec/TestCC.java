package suitec;

import org.testng.annotations.Test;

import dataprovider.TestDataProvider;
import testbase.TestBase;

public class TestCC extends TestBase {

	@Test(groups = {"sanity", "smoke","browser"},dataProviderClass = TestDataProvider.class , dataProvider = "dataSuiteC")
	public void testCC(String username, String password) throws InterruptedException
	{
		log("Executing the test in browser "+browser);
		log("Starting test CC");
		log(username + " "+ password);
		Thread.sleep(2000);
		log("Ending CC");
	}
}

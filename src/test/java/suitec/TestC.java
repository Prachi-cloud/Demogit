package suitec;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dataprovider.TestDataProvider;
import testbase.TestBase;

public class TestC extends TestBase{
	
	//@Parameters("browser")
	@Test(groups = {"sanity","browser"})
	public void testC() throws InterruptedException
	{
		log("Executing the test in browser "+browser);
		log("Starting test C" );
		
		Thread.sleep(2000);
		log("Ending C");
	}
	

}

package suitea;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import dataprovider.TestDataProvider;
import reports.ExtentManager;
import testbase.TestBase;

public class TestA extends TestBase {
	
	
	
	@Test(groups = {"sanity","smoke","browsergroup1"},dataProviderClass = TestDataProvider.class , dataProvider = "dataSuiteA")
	public void testA(String browserName,String username, String password) throws InterruptedException
	{
		log("Starting A");
		if(!"Title1".equals("Title"))
			softAssert("Titles do not match");
		//softAssert.assertEquals("Title 1", "Title");
		//softAssert.assertEquals("Text 1", "Text");
		log("logging into application");
		log("Booking ticket");
		
		if(!"Text1".equals("Text"))
			softAssert("Text do not match");
		
		if(!"a".equals("b"))
			failandStopTest("a is not valid");

		log(username + " "+ password); 
		Thread.sleep(2000);
		log("Ending A");
		Assert.fail("Some error");
		softAssert.assertAll();
		
		
	}

}

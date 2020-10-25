package suiteb;
import org.testng.annotations.Test;

import dataprovider.TestDataProvider;
import testbase.TestBase;
public class TestBB extends TestBase{


	
		
	@Test(groups = {"sanity","browsergroup2"},dataProviderClass = TestDataProvider.class , dataProvider = "dataSuiteB")
		public void testBB(String username, String password) throws InterruptedException
		{
			log("Starting test BB");
			log(username + " "+ password);
			Thread.sleep(2000);
			log("Ending BB");
		}

	}




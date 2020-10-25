package suitea;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import testbase.TestBase;

public class SakraWorldTest extends TestBase {
	
	@Test
	public void appointmentTest() throws InterruptedException
	{
		
	
		launchBrowser("Chrome");
		log("Opened browser chrome");
		driver.get(prop.getProperty("url"));
		Thread.sleep(7000);
		//waitForPageToLoad();
		//WebDriver wait = new WebDriverWait(driver, TimeUnit.MICROSECONDS);
		
		
		driver.findElement(By.linkText(prop.getProperty("doctor_name"))).click();
		if(!isElementPresent("name_id"))
		failandStopTest("Name field is not present/visble");
		log("Name field is present");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(getObjectLocator("name_id")));
		driver.findElement(getObjectLocator("name_id")).sendKeys(prop.getProperty("first_name"));
		driver.findElement(getObjectLocator("email_id")).sendKeys(prop.getProperty("email_id"));
		driver.findElement(getObjectLocator("mobile_id")).sendKeys(prop.getProperty("mobile_no"));
		
		String expectedGenderValues[] = prop.getProperty("expectedGenderOptions").split(",");
		
		WebElement gender = driver.findElement(getObjectLocator("gender"));
		Select s = new Select(gender);
		s.selectByIndex(1);
		List <WebElement> genders = s.getOptions();
		if(genders.size() != expectedGenderValues.length)
			fail("Gender list length/sizeis not same");
		for(int i=0; i<genders.size();i++)
		{
			
			System.out.println(expectedGenderValues[i]+ "----"+ genders.get(i).getText());
		}
		
		if(!isElementPresent((prop.getProperty("dob"))))
			failandStopTest("DOB field is not present/visble");
			log("DOB field is present");
			
		driver.findElement(getObjectLocator("dob_id")).click();
		selectDate(prop.getProperty("dob_val"));
		log("Selected DOB successfully");
		
		if(!isElementPresent((prop.getProperty("prefer_date_visit1"))))
			failandStopTest("Preferred date1 field is not present/visble");
			log("Preferred date1 field is present");
	
			driver.findElement(getObjectLocator("prefer_date_visit1_id")).click();
			selectDate(prop.getProperty("preferred_date_1"));
			log("Selected preferred date1 successfully");
			

		if(!isElementPresent((prop.getProperty("prefer_date_visit2"))))
				failandStopTest("Preferred date2 field is not present/visble");
				log("Preferred date2 field is present");
		
				driver.findElement(getObjectLocator("prefer_date_visit1_id")).click();
				selectDate(prop.getProperty("preferred_date_1"));
				log("Selected preferred date2 successfully");
				
		if(driver.findElement(getObjectLocator("uhid_id")).isDisplayed())
		failandStopTest("UHID is displayed");
		log("UHID is not dispalyed, clicking on no");
		driver.findElement(getObjectLocator("yes_radio_xpath")).click();
		
		if(!driver.findElement(By.id(prop.getProperty("uhid"))).isDisplayed())
			failandStopTest("UHID is not displayed");
		
		driver.findElement(getObjectLocator("uhid_id")).sendKeys(prop.getProperty("uhid_val"));
		
		driver.findElement(getObjectLocator("no_radio_xpath")).click();
		if(driver.findElement(By.id(prop.getProperty("uhid"))).isDisplayed())
			failandStopTest("UHID is displayed");
		
		driver.findElement(getObjectLocator("yes_radio_xpath")).click();
		String val = driver.findElement(getObjectLocator("uhid_id")).getAttribute("value");
		if(!val.equals(prop.getProperty("uhid_val")))
			failandStopTest("Text field value is not correct");
		log("UHID functionality is OK");
			
		
			
	}
	
	
	public boolean isElementPresent(String locatorKey)
	{
		//WebElement e = null;
		By locator = getObjectLocator(locatorKey);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	/*	try {
			e = driver.findElement(By.id(locator));
		}catch (Exception ex) {
			log("Excpetion while extracting object"+ ex.getMessage());
			return false;
		}
		
		log("Element visibility status"+ e.isDisplayed());
		if(!e.isDisplayed())
			return false;*/
		
		return true;
	}
		
		
	public void selectDate(String dateVal)
	{
		String monthYearDisplayed = driver.findElement(getObjectLocator("monthyear_css")).getText();
		System.out.println("Month Year to be Displayed "+ monthYearDisplayed );
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyy");
		try {
			Date dateToBeSelected = sd.parse(dateVal);//converting string type date to date type date
			System.out.println("Date to be selected "+ dateToBeSelected);
			Date currentDate = new Date();
			System.out.println("Current Date "+ currentDate);
			String day = new SimpleDateFormat("d").format(dateToBeSelected);
			System.out.println(day);
			String month = new SimpleDateFormat("MMMM").format(dateToBeSelected);
			System.out.println(month);
			String year = new SimpleDateFormat("yyyy").format(dateToBeSelected);
			System.out.println(year);
			//System.out.println(dateToBeSelected.toString());
			
			String monthYearToBeSelected = month+" "+year;
			System.out.println("Month year to be selected "+ monthYearToBeSelected);
			while(!monthYearToBeSelected.equals(monthYearDisplayed)) {
				if(dateToBeSelected.compareTo(currentDate)== 1)
				{
					driver.findElement(getObjectLocator("calendar_forward_xpath")).click();
				}
				else if(dateToBeSelected.compareTo(currentDate)== -1)
				{
				
					driver.findElement(getObjectLocator("calendar_back_xpath")).click();
				}
			
				monthYearDisplayed = driver.findElement(getObjectLocator("monthyear_css")).getText();
				System.out.println("Month Year Displayed "+ monthYearDisplayed );
			}	
			driver.findElement(By.xpath("//a[text()='"+day+"']")).click();
			
			
			
			} catch (ParseException e) {
			
			e.printStackTrace();
		}
	}
	
	public By getObjectLocator(String locatorKey)
	{
		if(locatorKey.endsWith("_id"))
			return By.id(prop.getProperty(locatorKey));
		else if(locatorKey.endsWith("_xpath"))
			return By.id(prop.getProperty(locatorKey));
		else if(locatorKey.endsWith("_css"))
			return By.id(prop.getProperty(locatorKey));
		else 
			return By.id(prop.getProperty(locatorKey));
	}
	
	
	/*public void waitForPageToLoad() throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int i=0;
		
		while(i!=10){
		String state = (String)js.executeScript("return document.readyState;");
		System.out.println(state);

		if(state.equals("complete"))
			break;
		else
			wait(10);

		i++;
		}
		// check for jquery status
		i=0;
		while(i!=10){
	
			Long d= (Long) js.executeScript("return jQuery.active;");
			System.out.println(d);
			if(d.longValue() == 0 )
			 	break;
			else
				 wait(10);
			 i++;
				
			}*/
		
		}
	
	



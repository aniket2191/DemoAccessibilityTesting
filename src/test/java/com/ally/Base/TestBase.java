package com.ally.Base;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.PublicKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.ally.Utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	ReadConfig config=new ReadConfig();
	public String baseUrl=config.getApplicationUrl();
	public static WebDriver driver;
	public WebDriverWait wait;
	public Actions act;
	

	@Parameters("browser") 
	@BeforeClass
	public void setUp(@Optional("firefox") String browsr)
	{


		switch(browsr.toLowerCase())
		{
		case "chrome": 
			WebDriverManager.chromedriver().setup();
			ChromeOptions cop=new ChromeOptions();
			cop.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(cop);
			
			
			break;
			
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
			
		case "edge":
			driver = WebDriverManager.edgedriver().create();
			//driver=new EdgeDriver();
			break;
			
		default :
			System.err.println("Invalid Browser Name : ");
		     System.err.println("Go to valid Browser Name : ");
		}


        driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofMillis(3000));
		act=new Actions(driver);

	}

	public static String takesScreenshot(String testName) throws IOException
	{        
		
	
		LocalDateTime myLocalDateTimeObj=LocalDateTime.now();
		DateTimeFormatter dateTimeFormatterObj=DateTimeFormatter.ofPattern("ddMMyyyHHmmss");
		String cuDateAndTime= myLocalDateTimeObj.format(dateTimeFormatterObj);
    
		String filepath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+cuDateAndTime+".png";		
		File fType=(File)((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);		
		FileUtils.copyFile(fType, new File(filepath));
		return filepath;
		
	}

   
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	

}

package com.ally.TestCases;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ally.Base.TestBase;
import com.ally.PageObjects.ElliciumAllyPOM;
import com.ally.Utilities.Demo_ExtentReport;
import com.aventstack.extentreports.Status;
import com.deque.axe.AXE;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.google.gson.JsonArray;


public class ElliciumAllyTesting extends TestBase{

	Demo_ExtentReport e = new Demo_ExtentReport();
	final URL scriptUrl=ElliciumAllyTesting.class.getResource("/axe.min.js");
	

	@Test
	public void elliciumAllyPageTest() throws InterruptedException, IOException {

		driver.get(baseUrl);
		
		ElliciumAllyPOM a1=new ElliciumAllyPOM(driver);
		
		JSONObject jObj=new AXE.Builder(driver, scriptUrl).analyze();
		
		JSONArray violations=jObj.getJSONArray("violations");
		
		if(violations.length()==0)
		{
			System.out.println("No Error on WebPage...");
		}
		else
		{
			AXE.writeResults("ElliciumAllyTestResult", jObj);
			
			 File file = new File("D:\\E_Git_Traingings_Ecplise_workspace\\DemoForAccessibilityTesting\\TestData\\Test.csv");
			  
	            
	  
	            String csvString = CDL.toString(violations);
	            FileUtils.writeStringToFile(file, csvString);	
			
			Assert.assertTrue(false,AXE.report(violations));
		}
		
		
	}
	
	@Test
	public void elliciumTitleCheck() throws InterruptedException, IOException {

		driver.get(baseUrl);
		
		ElliciumAllyPOM a1=new ElliciumAllyPOM(driver);
		
		JSONObject jObj=new AXE.Builder(driver, scriptUrl).include("Title").analyze();
		
		JSONArray violations=jObj.getJSONArray("violations");
		
		if(violations.length()==0)
		{
			System.out.println("No Error on WebPage...");
		}
		else
		{
			AXE.writeResults("ElliciumAllyTestResult1", jObj);
			
			 //File file = new File("D:\\E_Git_Traingings_Ecplise_workspace\\DemoForAccessibilityTesting\\TestData\\Test.csv");
			   
	           // String csvString = CDL.toString(violations);
	            //FileUtils.writeStringToFile(file, csvString);	
			
			Assert.assertTrue(false,AXE.report(violations));
		}
		
		
	}




}

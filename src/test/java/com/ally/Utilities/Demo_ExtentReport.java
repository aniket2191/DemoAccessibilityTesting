package com.ally.Utilities;

import java.io.IOException;
import java.util.Date;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.ally.Base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;





//public class Demo_ExtentReport  extends TestListenerAdapter {
	
public class Demo_ExtentReport extends TestListenerAdapter{
	static Date d = new Date();

	String filepath="";
	
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	public static ExtentReports extent = ExtentManager
			.createInstance(".\\reports\\" + fileName);
	//ExtentTest testlog=ExtentManager.createTest();

	public static ExtentTest test;
	

	public void onTestStart(ITestResult result) {
	
		test = extent
				.createTest("@TestCase : " + result.getMethod().getMethodName());
		Capabilities capabilities=( (RemoteWebDriver) TestBase.driver).getCapabilities();
        extent.setSystemInfo("Browser Name", capabilities.getBrowserName());
        extent.setSystemInfo("Browser Varsion",capabilities.getBrowserVersion());
		//test.debug(MarkupHelper.createLabel(ExtentManager.createTest(),ExtentColor.TRANSPARENT));
		//extentTest.debug(MarkupHelper.createLabel(getBrowser() + " " + getVersion(), ExtentColor.TRANSPARENT));
		
	}

	public void onTestSuccess(ITestResult result) {
		
		
		
		
		Capabilities capabilities=( (RemoteWebDriver) TestBase.driver).getCapabilities();
		
		
		
		
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED ON <h2><font color=white> "+capabilities.getBrowserName().toUpperCase() + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);
	}
	public void onTestFailure(ITestResult result) {
		
	
		
		Capabilities capabilities=( (RemoteWebDriver) TestBase.driver).getCapabilities();
		
		test.fail(result.getThrowable().getLocalizedMessage());
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " FAILED on  <h2><font color=white>"+capabilities.getBrowserName().toUpperCase() +"</b>";	
		//		String excepionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		//		test.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
		//				+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
		//	
		//	

		try {
			test.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b><br>"+TestBase.takesScreenshot(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.log(Status.FAIL, m);
		
	
		
		
		


	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		test.skip(m);

	}

	

	public void onFinish(ITestContext context) {
		
		if (extent != null) {

			extent.flush();
		}

	}

}



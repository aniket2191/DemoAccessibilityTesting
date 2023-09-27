package com.ally.PageObjects;

import java.util.List;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class ElliciumAllyPOM {

	WebDriver driver;
	

	public ElliciumAllyPOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}






}

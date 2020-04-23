package com.learning.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.learning.base.TestBase;
//@Listeners(com.learning.listeners.CustomListener.class)
public class BankManagerLoginTest extends TestBase{
	@Test
	public void bankManagerLoginTest(){
		//test=report.startTest("BankManagerLoginTest");
		//System.setProperty("org.uncommons.reportng.escape-output", "false");
		log.debug("Inside logintest");
		click("btnBankManager_XPATH");
		//driver.findElement(By.xpath(OR.getProperty("btnBankManager"))).click();
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("btnAddCust_XPATH"))),"Login not successful");
		Reporter.log("Successfully logged in as Bankmanager");
		//Assert.fail();
		//Reporter.log("<a target=\"_blank\" href=\"C:\\Drive\\BackUp\\TRAINING\\Projects\\DataDriven\\src\\test\\resources\\logs\\Screenshot.jpg\">Screenshot</a>");
	}
}

package com.learning.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.learning.base.TestBase;
import com.learning.utilities.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

@Guice
public class OpenAccountTest extends TestBase {
	@Test(dataProviderClass = TestUtils.class,dataProvider="dp")
	public void openAccountTest(String customer, String currency,String alertText,String runmode) {
		if(!runmode.equals("Y")){
			throw new SkipException("Skipped testcase for this iteration as runmode is set to NO ");
		}
		click("btnOpenAccount_XPATH");
		dropdown("customerName_ID",customer);
		dropdown("currency_ID",currency);
		click("btnProcess_XPATH");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());;
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();
		test.log(LogStatus.PASS, "Test Passed");
		
	}

	
}

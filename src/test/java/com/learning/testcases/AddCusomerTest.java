package com.learning.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.learning.base.TestBase;
import com.learning.utilities.TestUtils;

//@Listeners(com.learning.listeners.CustomListener.class)
public class AddCusomerTest extends TestBase {
	@Test(dataProvider = "dp",dataProviderClass=TestUtils.class)
	public void addCusomerTest(String firstName, String lastName, String postCode,String alertText,String runmode) {
		if(!runmode.equals("Y")){
			throw new SkipException("Skipped testcase for this iteration as runmode is set to NO ");
		}
		click("pageBtnAddCust_XPATH");
		enter("firstName_XPATH",firstName);
		enter("lastName_XPATH",lastName);
		enter("postCode_XPATH",postCode);
		click("formBtnAddCust_XPATH");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();
		Reporter.log("Successfully added the customer");
		
	}

	/*@DataProvider
	public Object[][] getData(){
		String sheetName="AddCusomerTest";
		int rows=excel.getRowCount(sheetName);
		int cols=excel.getColumnCount(sheetName);
		
		Object[][] data=new Object[rows-1][cols];
		for(int rowNum=2;rowNum<=rows;rowNum++){
			for(int colNum=0;colNum<cols;colNum++){
				data[rowNum-2][colNum]=excel.getCellData(sheetName,colNum,rowNum);
				System.out.println("Row:"+(rowNum-2)+",Col:"+colNum);
				}
			
		}
		return data;
	}*/
}

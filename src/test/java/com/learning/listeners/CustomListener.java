package com.learning.listeners;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.learning.base.TestBase;
import com.learning.utilities.MonitoringMail;
import com.learning.utilities.TestConfig;
import com.learning.utilities.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListener extends TestBase implements ITestListener, ISuiteListener {
	static String messageBody;

	public void onTestStart(ITestResult result) {
		test = report.startTest(result.getName().toUpperCase());
		if (!TestUtils.isTestRunnable(result.getName(), excel)) {
			throw new SkipException("Skipping the test " + result.getName().toUpperCase() + "as Run Mode is NO ");
		}

	}

	public void onTestSuccess(ITestResult result) {

		test.log(LogStatus.PASS, result.getName().toUpperCase() + ":PASS");

	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, "Skipped as run mode is NO " + result.getName().toUpperCase());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ISuite arg0) {

		/*
		 * MonitoringMail mail = new MonitoringMail();
		 * 
		 * try { messageBody = "http://" +
		 * InetAddress.getLocalHost().getHostAddress() +
		 * ":8080/job/DataDrivenLiveProject/Extent_Reports/"; } catch
		 * (UnknownHostException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * try { mail.sendMail(TestConfig.server, TestConfig.from,
		 * TestConfig.to, TestConfig.subject, messageBody); } catch
		 * (AddressException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (MessagingException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}

	public void onFinish(ITestContext context) {
		report.endTest(test);
		report.flush();

	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		TestUtils.captureScreenshot();
		Reporter.log("<br>");
		// Reporter.log("Successfully added the customer");
		Reporter.log(
				"<a target=\"_blank\" href=\"C:\\Drive\\BackUp\\TRAINING\\Projects\\DataDriven\\src\\test\\resources\\logs\\Screenshot.jpg\"><img src=\"C:\\Drive\\BackUp\\TRAINING\\Projects\\DataDriven\\src\\test\\resources\\logs\\Screenshot.jpg\" height=200 width=200\"></img>Screenshot</a>");
		// Reporter.log("<a target=\"_blank\"
		// href="+TestUtils.screenshotName+"><img
		// src="+TestUtils.screenshotName+" height=200
		// width=200\"></img>Screenshot</a>");
		test.log(LogStatus.FAIL, result.getName() + ":Failed with exeception " + result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtils.screenshotName));

	}
}

package com.learning.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.learning.utilities.ExcelReader;
import com.learning.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	/*
	 * Webdriver Properties DB Excel Logs Extent reports Mail
	 */
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() {
		System.out.println(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");

		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");
		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("config file loaded");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String browser = config.getProperty("browser");
			switch (browser) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.info("Chrome driver initiated");
				break;
			case "firefox":
				driver = new FirefoxDriver();
				log.info("Firefox driver initiated");
				break;
			}
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitlyWait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
		}
	}

	public boolean isElementPresent(By by) {
		log.debug("Inside isElementPresent");
		try {
			driver.findElement(by);
			log.debug("Element Present" + by.toString());
			test.log(LogStatus.INFO, "Element is present");
			return true;
		} catch (NoSuchElementException e) {
			log.debug("Element not present" + by.toString());
			test.log(LogStatus.INFO, "Element is not present");
			return false;
		}
	}

	public void click(String locator) {
		try {
			if (locator.endsWith("XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator))).click();
			} else if (locator.endsWith("CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			} else if (locator.endsWith("ID")) {
				driver.findElement(By.id(OR.getProperty(locator))).click();
			}
			test.log(LogStatus.INFO, "clicked on " + locator + "successfully");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "unable to click due to " + e.getMessage());
		}

	}

	public void enter(String locator, String value) {
		try {
			// driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			if (locator.endsWith("XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			} else if (locator.endsWith("CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			} else if (locator.endsWith("ID")) {
				driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
			}
			test.log(LogStatus.INFO, "entered " + value + " on " + locator + "successfully");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "unable to enter value due to " + e.getMessage());
		}

	}

	public void dropdown(String locator, String visibleText) {
		Select select;
		WebElement element;
		try{
		if (locator.endsWith("XPATH")) {
			element = driver.findElement(By.xpath(OR.getProperty(locator)));
			select = new Select(element);
			select.selectByVisibleText(visibleText);

		} else if (locator.endsWith("CSS")) {
			element = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			select = new Select(element);
			select.selectByVisibleText(visibleText);

		} else if (locator.endsWith("ID")) {
			element = driver.findElement(By.id(OR.getProperty(locator)));
			select = new Select(element);
			select.selectByVisibleText(visibleText);

		}
		test.log(LogStatus.INFO, "Selected dropdown value "+visibleText);
		}catch(Exception e){
			test.log(LogStatus.INFO, "Unable to select the dropdown value "+visibleText+"due to exception :"+e.getMessage());
		}
	}

	@AfterSuite
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
}

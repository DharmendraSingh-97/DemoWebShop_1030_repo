package com.tricentis.genericutility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.tricentis.objectrepository.HomePage;
import com.tricentis.objectrepository.LoginPage;
import com.tricentis.objectrepository.WelcomePage;

public class BaseClass {
	public static WebDriver driver;
	public static ExtentReports extReport;
	public WebDriverWait wait;
	public ExtentTest test;
	
	public FileUtility fileLib;
	public ExcelUtility excelLib;
	public WebDriverUtility webdriverLib;
	public JavaUtility javaLib;
	
	public WelcomePage wp;
	public LoginPage lp;
	public HomePage hp;
	
	@BeforeSuite(alwaysRun = true)
	public void reportConfig() {
		javaLib=new JavaUtility();
		String timestamp = javaLib.getSystemTime();
		ExtentSparkReporter spark=new ExtentSparkReporter("./HTML_reports/ExtentReport_"+timestamp+".html");
		extReport=new ExtentReports();
		extReport.attachReporter(spark);
	}
	@Parameters("Browser")
	@BeforeClass(alwaysRun = true)
	public void launchBrowser(@Optional("chrome") String browserName) throws IOException {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if (browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		fileLib=new FileUtility();
		driver.get(fileLib.getDataFromProperty("url"));
	}
	
	@BeforeMethod(alwaysRun = true)
	public void login() throws IOException {
		wp=new WelcomePage(driver);
		wp.getLoginLink().click();
		lp=new LoginPage(driver);
		lp.getEmailTextField().sendKeys(fileLib.getDataFromProperty("email"));
		lp.getPasswordTextField().sendKeys(fileLib.getDataFromProperty("password"));
		lp.getLoginButton().click();
	}
	@AfterMethod(alwaysRun = true)
	public void logout() {
		hp=new HomePage(driver);
		hp.getLogoutLink().click();
	}
	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}
	
	@AfterSuite(alwaysRun = true)
	public void reportBackup() {
		extReport.flush();
	}
}

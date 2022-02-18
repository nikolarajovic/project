package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.BasicPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;
import pages.SearchResultPage;
import sun.net.www.protocol.http.AuthCacheImpl;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public abstract class BasicTest {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor js;
	protected SoftAssert softAssertion;
	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationSystemPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	protected SearchResultPage searchResultPage;
	String baseUrl = "http://demo.yo-meals.com/";
	String username = "customer@dummyid.com";
	String password = "12345678a";

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.navigate().to(baseUrl);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		softAssertion = new SoftAssert();

		locationPopupPage = new LocationPopupPage(driver, wait, js);
		loginPage = new LoginPage(driver, wait, js);
		notificationSystemPage = new NotificationSystemPage(driver, wait, js);
		profilePage = new ProfilePage(driver, wait, js);
		authPage = new AuthPage(driver, wait, js);
		mealPage = new MealPage(driver, wait, js);
		cartSummaryPage = new CartSummaryPage(driver, wait, js);
		searchResultPage = new SearchResultPage(driver, wait, js);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-h-mm-ssa");
		String formattedDate = sdf.format(date);

		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
				File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(sourceFile, new File("./screenshots/" + formattedDate + ".png"));
				System.out.println("Screenshot taken!");
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage() + "!");
			}
		}

		driver.quit();

	}

}

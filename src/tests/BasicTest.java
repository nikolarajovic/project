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

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public abstract class BasicTest {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor js;
	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationSystemPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	protected SearchResultPage searchResultPage;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.navigate().to("http://demo.yo-meals.com/");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		SoftAssert softAssertion = new SoftAssert();

		locationPopupPage = new LocationPopupPage(driver, wait, js);
		loginPage = new LoginPage(driver, wait, js);
		notificationSystemPage = new NotificationSystemPage(driver, wait, js);
		profilePage = new ProfilePage(driver, wait, js);
		authPage = new AuthPage(driver, wait, js);
		mealPage = new MealPage(driver, wait, js);
		cartSummaryPage = new CartSummaryPage(driver, wait, js);
		searchResultPage = new SearchResultPage(driver, wait, js);
	}

	@Test
	public void function() throws InterruptedException {

//		locationPopupPage.chooseLocation("Beverwyck - Albany");
//		loginPage.login("customer@dummyid.com", "12345678a");
//		notificationSystemPage.waitForMessageToDisappear();
//		profilePage.goToProfilePage();
//		profilePage.uploadPhoto("img/profilePhoto.jpeg");
//		profilePage.removePhoto();
//		profilePage.personalInformationInput("Jackson", "Roland", "StreetInUK10", "066666666", "18000",
//				"United Kingdom", "Aberdeen", "Swadlincote");
//		authPage.logOut();
//		mealPage.addToFavourite();
//		mealPage.addProductToTheCart("3");
//		cartSummaryPage.clearAll();
//		searchResultPage.searchResultsNames();
		
//		[ERROR] Unexpected logout message
//		rešenje sa listom ili try/catch-om
		
		String email = "customer@dummyid.com";
		String password = "12345678a";

	}

	@AfterMethod
	public void afterMethod() throws IOException {
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-h-mm-ss a");
//		String formattedDate = sdf.format(date);
//		TakesScreenshot screenshot = ((TakesScreenshot) driver);
//		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(sourceFile, new File("./screenshots/" + formattedDate + ".png"));
		
		driver.quit();
	}

}

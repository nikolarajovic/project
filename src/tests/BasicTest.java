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

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class BasicTest {

	private WebDriver driver;
	private WebDriverWait wait;
	private LocationPopupPage locationPopupPage;
	private LoginPage loginPage;
	private NotificationSystemPage notificationSystemPage;
	private ProfilePage profilePage;
	private AuthPage authPage;
	private MealPage mealPage;
	private CartSummaryPage cartSummaryPage;
	private SearchResultPage searchResultPage;

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

		locationPopupPage = new LocationPopupPage(driver, wait);
		loginPage = new LoginPage(driver, wait);
		notificationSystemPage = new NotificationSystemPage(driver, wait);
		profilePage = new ProfilePage(driver, wait);
		authPage = new AuthPage(driver, wait);
		mealPage = new MealPage(driver, wait);
		cartSummaryPage = new CartSummaryPage(driver, wait);
		searchResultPage = new SearchResultPage(driver, wait);
	}

	@Test
	public void function() throws InterruptedException {

		locationPopupPage.chooseLocation("Beverwyck - Albany");
		loginPage.login("customer@dummyid.com", "12345678a");
		notificationSystemPage.waitForMessageToDisappear();
//		profilePage.goToProfilePage();
//		profilePage.uploadPhoto("img/profilePhoto.jpeg");
//		profilePage.removePhoto();
//		profilePage.personalInformationInput("Jackson", "Roland", "StreetInUK10", "066666666", "18000",
//				"United Kingdom", "Aberdeen", "Swadlincote");
//		authPage.logOut();
//		mealPage.addToFavourite();
//		mealPage.addProductToTheCart("3");
//		cartSummaryPage.clearAll();
		searchResultPage.searchResultsNames();

	}

	@AfterMethod
	public void afterMethod() {
//		driver.quit();
	}

}

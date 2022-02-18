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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class MealItemTest extends BasicTest {

	@Test(priority = 1)
	public void addMealToCartTest() throws InterruptedException {
		locationPopupPage.getCloseButton().click();
		mealPage.getFirstProduct();
		mealPage.addProductToTheCart("3");
		Assert.assertTrue(notificationSystemPage.getNotificationErrorMessage().contains("Errors"),
				"[ERROR] Location error message did not appear.");

		notificationSystemPage.waitForMessageToDisappear();
		locationPopupPage.clickLocationPopUp();
		locationPopupPage.chooseLocation("Beverwyck - Albany");
		driver.navigate().to(baseUrl);
		mealPage.getFirstProduct();
		mealPage.addProductToTheCart("3");
		Assert.assertTrue(notificationSystemPage.getNotificationMessage().contains("Added"),
				"[ERROR] Added to cart message did not appear.");
	}

	@Test(priority = 2)
	public void addMealToFavouriteTest() throws InterruptedException {
		locationPopupPage.getCloseButton().click();
		mealPage.addToFavourite();
		Assert.assertTrue(notificationSystemPage.getNotificationErrorMessage().contains("Please"),
				"[ERROR] Login error message did not appear.");

		loginPage.getLoginButton().click();
		loginPage.login(super.username, super.password);
		driver.navigate().to(baseUrl);
		mealPage.getFirstProduct();
		mealPage.addToFavourite();
		Assert.assertTrue(notificationSystemPage.getNotificationMessage().contains("added"),
				"[ERROR] Added to favourite message did not appear.");
	}

	@Test(priority = 3)
	public void clearCartTest() throws InterruptedException, IOException {
		File file = new File("./data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");
		DataFormatter formatter = new DataFormatter();

		driver.navigate().to(baseUrl + "/meals");
		locationPopupPage.chooseLocation("City Center - Albany");

		for (int i = 1; i < 5; i++) {
			String url = formatter.formatCellValue(sheet.getRow(1).getCell(0));
			driver.navigate().to(url);
			mealPage.addProductToTheCart("i");
			softAssertion.assertTrue(notificationSystemPage.getNotificationMessage().contains("Added"),
					"[ERROR] Added to cart message did not appear.");
		}

		softAssertion.assertAll();

		cartSummaryPage.clearAll();
		Assert.assertTrue(notificationSystemPage.getNotificationMessage().contains("removed"),
				"[ERROR] Meals removed from the cart message did not appear.");

	}

}

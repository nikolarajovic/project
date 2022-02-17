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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class ProfileTest extends BasicTest {

	@Test(priority = 1)
	public void editProfileTest() throws InterruptedException {
		driver.navigate().to(super.baseUrl + "/guest-user/login-form");
		locationPopupPage.chooseLocation("Beverwyck - Albany");
		loginPage.login(super.username, super.password);
		Assert.assertTrue(notificationSystemPage.getNotificationMessage().contains("Successfull"),
				"[ERROR] Login message did not appear.");

		driver.navigate().to(super.baseUrl + "/member/profile");
		profilePage.personalInformationInput("Jackson", "Roland", "StreetInUK10", "066666666", "18000",
				"United Kingdom", "Aberdeen", "Swadlincote");
		Assert.assertTrue(notificationSystemPage.getNotificationMessage().contains("Setup"),
				"[ERROR] Setup message did not appear.");

		notificationSystemPage.waitForMessageToDisappear();
		authPage.logOut();
		Assert.assertTrue(notificationSystemPage.getNotificationMessage().contains("Logout Successfull!"),
				"[ERROR] Logout message did not appear.");
	}

	@Test(priority = 2)
	public void changeProfileImageTest() throws InterruptedException {
		driver.navigate().to(super.baseUrl + "/guest-user/login-form");
		locationPopupPage.chooseLocation("Beverwyck - Albany");
		loginPage.login(super.username, super.password);
		Assert.assertTrue(notificationSystemPage.getNotificationMessage().contains("Successfull"),
				"[ERROR] Login message did not appear.");

		driver.navigate().to(super.baseUrl + "/member/profile");
		profilePage.uploadPhoto("img/profilePhoto.jpeg");
		Assert.assertTrue(
				notificationSystemPage.getNotificationMessage().contains("Profile Image Uploaded Successfully"),
				"[ERROR] Image upload message did not appear.");

		notificationSystemPage.waitForMessageToDisappear();
		profilePage.removePhoto();
		Assert.assertTrue(
				notificationSystemPage.getNotificationMessage().contains("Profile Image Uploaded Successfully"),
				"[ERROR] Image remove message did not appear.");

		authPage.logOut();
		Assert.assertTrue(notificationSystemPage.getNotificationMessage().contains("Logout Successfull!"),
				"[ERROR] Logout message did not appear.");
	}

}

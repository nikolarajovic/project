package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.BasicPage;
import pages.LocationPopupPage;

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
  }

  @Test
  public void function() throws InterruptedException {
	  
	  locationPopupPage.chooseLocation("Beverwyck - Albany");
	  
  }
  
  @AfterMethod
  public void afterMethod() {
//	  driver.quit();
  }

}

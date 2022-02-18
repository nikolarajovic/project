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

public class SearchTest extends BasicTest {

	@Test(priority = 1)
	public void searchResultsTest() throws InterruptedException, IOException {
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");
		DataFormatter formatter = new DataFormatter();

		driver.navigate().to(super.baseUrl + "/meals");
		locationPopupPage.chooseLocation("City Center - Albany");

		for (int i = 1; i < 5; i++) {
			String location = formatter.formatCellValue(sheet.getRow(1).getCell(0));
			String url = formatter.formatCellValue(sheet.getRow(1).getCell(1));
			String searchResultNumber = formatter.formatCellValue(sheet.getRow(1).getCell(2));
			int searchResultNumberInt = Integer.parseInt(searchResultNumber);

			driver.navigate().to(url);
			locationPopupPage.clickLocationPopUp();
			locationPopupPage.chooseLocation(location);

			softAssertion.assertEquals(searchResultPage.searchResultsNumber(), searchResultNumberInt,
					"[ERROR] Search result does not match with compared data.");
		}

		softAssertion.assertAll();

	}

}

package pages;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}

	public WebElement getUploadPhotoButton() {
		return driver.findElement(By.xpath("//*[contains(@class, 'upload uploadFile-Js')]"));
	}

	public WebElement getRemovePhotoButton() {
		return driver.findElement(By.xpath("//*[contains(@class, 'remove')]"));
	}

	public WebElement getAuthButton() {
		return driver.findElement(By.xpath("//*[contains(@class, 'after-arrow user-trigger-js user-trigger-active')]"));
	}

	public WebElement getMyAccountButton() {
		return driver.findElement(By.xpath("//*[contains(@class, 'my-account-dropdown')]//li[1]/a"));
	}

	public WebElement getProfileButton() {
		return driver.findElement(By.xpath("//*[@id = 'fixed__panel']//li[2]/a"));
	}

	public WebElement getFirstNameInput() {
		return driver.findElement(By.name("user_first_name"));
	}

	public void scrollIntoViewFirstName() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView;", getFirstNameInput());
	}

	public WebElement getLastNameInput() {
		return driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAdressInput() {
		return driver.findElement(By.name("user_address"));
	}

	public WebElement getPhoneNumberInput() {
		return driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCodeInput() {
		return driver.findElement(By.name("user_zip"));
	}

	public void selectCountry(String country) {
		Select dropdownCountry = new Select(driver.findElement(By.id("user_country_id")));
		dropdownCountry.selectByVisibleText(country);
	}

	public void selectState(String state) {
		Select dropdownState = new Select(driver.findElement(By.id("user_state_id")));
		dropdownState.selectByVisibleText(state);
	}

	public void selectCity(String city) {
		Select dropdownCity = new Select(driver.findElement(By.id("user_city")));
		dropdownCity.selectByVisibleText(city);
	}

	public WebElement getPersonalInformationSaveButton() {
		return driver.findElement(
				By.xpath("//*[contains(@class, 'col-lg-12 col-md-12 col-sm-12 col-lg-12 align--right')]//input"));
	}

	public void goToProfilePage() {
		js.executeScript("arguments[0].click();", getMyAccountButton());
		getProfileButton().click();
	}

	public void uploadPhoto(String pathToThePicture) throws InterruptedException {
		File profilePhoto = new File(pathToThePicture);
		js.executeScript("arguments[0].click();", getUploadPhotoButton());
		WebElement profilePhotoUpload = driver.findElement(By.name("file"));
		profilePhotoUpload.sendKeys(profilePhoto.getAbsolutePath());
		Thread.sleep(3000);
	}

	public void removePhoto() {
		js.executeScript("arguments[0].click();", getRemovePhotoButton());
	}

	public void personalInformationInput(String firstName, String lastName, String adress, String phoneNumber,
			String zipCode, String country, String state, String city) throws InterruptedException {
		scrollIntoViewFirstName();
		getFirstNameInput().clear();
		getLastNameInput().clear();
		getAdressInput().clear();
		getPhoneNumberInput().clear();
		getZipCodeInput().clear();
		getFirstNameInput().sendKeys(firstName);
		getLastNameInput().sendKeys(lastName);
		getAdressInput().sendKeys(adress);
		getPhoneNumberInput().sendKeys(phoneNumber);
		getZipCodeInput().sendKeys(zipCode);
		selectCountry(country);
		Thread.sleep(1500);
		selectState(state);
		selectCity(city);
		js.executeScript("arguments[0].click();", getPersonalInformationSaveButton());
	}

}

package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}

	public WebElement getMealsButton() {
		return driver.findElement(By.xpath("//*[contains(@class, 'navs--main')]//li[1]/a"));
	}

	public WebElement getProduct() {
		return driver.findElement(By.xpath("//*[@id='listing']/div[1]/div/div[2]/div[2]/a"));
	}

	public WebElement getQuantityInput() {
		return driver.findElement(By.name("product_qty"));
	}

	public WebElement getAddToCartButton() {
		return driver
				.findElement(By.xpath("//*[contains(@class, 'btn btn--primary btn--large js-proceedtoAddInCart ')]"));
	}

	public WebElement getFavouriteButton() {
		return driver.findElement(By.className("favourite"));
	}

	public void getFirstProduct() {
		getMealsButton().click();
		js.executeScript("arguments[0].click();", getProduct());
	}

	public void addProductToTheCart(String quantity) throws InterruptedException {
		getQuantityInput().clear();
		this.getQuantityInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		this.getQuantityInput().sendKeys(quantity);
		getAddToCartButton().click();
	}

	public void addToFavourite() {
		js.executeScript("arguments[0].click();", getFavouriteButton());
	}

}

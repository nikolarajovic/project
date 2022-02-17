package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {

	public NotificationSystemPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}

	public WebElement getLoginButton() {
		return driver.findElement(By.xpath("//*[contains(@class, 'alert--success')]"));
	}

	public String getNotificationLoginMessage() {
		return driver.findElement(By.xpath("//*[contains(@class, 'alert--success')]")).getText();
	}

	public String getNotificationSetupMessage() {
		return driver
				.findElement(By.xpath(
						"//*[contains(@class, 'system_message alert alert--positioned-top-full alert--success')]/div"))
				.getText();
	}

	public String getNotificationLogoutMessage() {
		return driver.findElement(By.xpath("//*[contains(@class, 'system_message alert alert--success')]"))
				.getText();
	}
	
	public String getNotificationImageMessage() {
		return driver.findElement(By.xpath("//*[contains(@class, 'system_message alert alert--positioned-top-full alert--success')]//div"))
				.getText();
	}
	
	public String getNotificationRemoveImageMessage() {
		return driver.findElement(By.xpath("//*[contains(@class, 'system_message alert alert--positioned-top-full alert--success')]//div"))
				.getText();
	}

	public void waitForMessageToDisappear() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.attributeToBe(By.xpath(" //*[contains(@class, 'system_message')]"), "style",
				"display: none;"));
	}

}

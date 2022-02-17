package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}

	public WebElement getMealsButton() {
		return driver.findElement(By.xpath("//*[contains(@class, 'navs--main')]//li[1]/a"));
	}

	public List<WebElement> getSearchResults() {
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='product-name']/a"));
		return list;
	}

	public int searchResultsNumber() {
		return getSearchResults().size();
	}

	public void searchResultsNames() {
		getMealsButton().click();
		int n = getSearchResults().size();
		String[] searchResults = new String[n];
		for (int i = 0; i < searchResults.length; i++) {
			searchResults[i] = getSearchResults().get(i).getText();
		}
		for (int i = 0; i < searchResults.length; i++) {
			System.out.println(searchResults[i]);
		}
	}

}

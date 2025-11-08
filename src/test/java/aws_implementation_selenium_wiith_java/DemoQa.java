package aws_implementation_selenium_wiith_java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DemoQa extends BaseTest {

	SoftAssert sa = new SoftAssert();

	@Test
	public void verifySearchInputText() {

		WebElement searchBox = driver.get()
				.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"));

		String searchBoxText = searchBox.getText();
		logger.info("Search Box : {}", searchBoxText);
		if (!searchBoxText.contains("https://www.flipkart.com/")) {
			logger.warn("search box is not showns.");
			sa.fail("search box text is not matched");
		} else {
			logger.info("✅ Search box text is correct.");
		}
		Utility.takeScreenshot(driver.get(), "verifySearchText");
		sa.assertAll();

	}

}
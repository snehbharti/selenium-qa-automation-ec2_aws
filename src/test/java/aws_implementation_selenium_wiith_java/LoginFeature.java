package aws_implementation_selenium_wiith_java;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginFeature extends BaseTest {

	SoftAssert sa = new SoftAssert();

	@Test
	public void verifyCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		logger.info("Current URL: {}", currentUrl);
		if (!currentUrl.contains("https://www.flipkart.com/")) {
			logger.warn("Current URL mismatch! Expected Flipkart URL.");
			sa.fail("Current Url is mismatched");
		} else {
			logger.info("✅ Current URL is correct.");
		}
		Utility.takeScreenshot(driver, "verifyCurrentUrl");
		sa.assertAll();

	}

	@Test
	public void verifyTitle() {
		String pageTitle = driver.getTitle();
		logger.info("Page Title: {}", pageTitle);
		sa.assertEquals(pageTitle,
				"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
		sa.assertAll();
	}

}

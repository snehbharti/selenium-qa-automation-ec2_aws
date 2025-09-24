package aws_implementation_selenium_wiith_java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {

	protected WebDriver driver;

	protected Logger logger = LogManager.getLogger(getClass());

//	// Before all tests, execute only once per test suite
//	@BeforeTest
//	@Parameters({ "browser" })
//	public void setUpTest(String browser) {
//		// Choose browser dynamically
//		if (browser.equalsIgnoreCase("chrome")) {
//			setUpChrome();
//		}
//		// Add other browsers if necessary
//	}

	private void setUpChrome() {
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--headless=new"); // run in background
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1920,1080");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}

	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(@Optional("chrome") String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			setUpChrome();
			logger.info("Browser launched");
		}
		driver.get("https://flipkart.com");
		logger.info("Navigated to flipkart home page");
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(5);
		driver.quit();
		logger.info("Browser closed");
	}

	// After all tests, execute once per test suite
	// @AfterTest
	// public void cleanUp() {
	// Cleanup code, if any, goes here
	// }
}

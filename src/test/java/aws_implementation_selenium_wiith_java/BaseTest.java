package aws_implementation_selenium_wiith_java;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

	// protected WebDriver driver;   // ❌ remove this line as we are making parallel by selenium grid on aws

	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();   // ✅ add this


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

	private void setUpChrome() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new"); // run in background
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1920,1080");
		
		
//		driver = new ChromeDriver(options);
		
//		\\========================================================\\
		
		/*  WebDriver driver = new RemoteWebDriver(
	                new URL("http:// 15.207.197.153:4444/wd/hub"),
	                options
	        );*/
		  
		  driver.set(new RemoteWebDriver(
	                new URL("http:// 15.207.197.153:4444/wd/hub"),options ));
		  
		  
//		\\===========================================================//
		  driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}

	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(@Optional("chrome") String browser) throws MalformedURLException {
		if (browser.equalsIgnoreCase("chrome")) {
			setUpChrome();
			logger.info(" Chrome Browser launched");
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			
			setUpChrome();
			logger.info(" Firefox Browser launched");
			
			
		}
	//	driver.get("https://flipkart.com");
		driver.get().get("https://flipkart.com");// locker kholo → andar se actual driver nikalo ab us driver ko URL open karne bolo
		
		logger.info("Navigated to flipkart home page");
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(5);
	//	driver.quit();
		driver.get().quit();
		logger.info("Browser closed");
	}

	// After all tests, execute once per test suite
	// @AfterTest
	// public void cleanUp() {
	// Cleanup code, if any, goes here
	// }
}

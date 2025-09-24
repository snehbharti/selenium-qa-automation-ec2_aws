package aws_implementation_selenium_wiith_java;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	
	public static void takeScreenshot(WebDriver driver, String name) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = name + "_" + timestamp + ".png";

        // Create screenshots directory if it doesn't exist
        File screenshotDir = new File("screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdir();
        }

        File destFile = new File(screenshotDir, screenshotName);

        try {
            FileHandler.copy(srcFile, destFile);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

}

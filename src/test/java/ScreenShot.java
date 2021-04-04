import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class ScreenShot {

    public static void TakeaScreenShot(WebDriver driver, ExtentTest test) { //throws InterruptedException {
        WebElement curElement,targetElement;
        String cwd = System.getProperty("user.dir");
        String ImagesPath=cwd + "src/main/resources/Screenshot_Image.png";

        //driver.findElement(By.cssSelector("a[class=feature-bottom-link]")).click();
        curElement=driver.findElement(By.cssSelector("a[class=feature-bottom-link]"));
        curElement.click();
        targetElement=driver.findElement(By.cssSelector("h4[class=title]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement);
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath);
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            test.info("Screenshot wasn't taken because " +e.getMessage());
        }
        //test.info("details", MediaEntityBuilder.createScreenCaptureFromPath(screenShotFile).build());
        test.info("details", MediaEntityBuilder.createScreenCaptureFromPath(ImagesPath).build());
    }
}
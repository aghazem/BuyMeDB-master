import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SvPicAnotherSite {
    private static WebDriver driver;
    private static WebDriverWait wait;
    public static void svPicAnotherSite() throws IOException, InterruptedException {
        //driver = GetDriver.getDriverInstance("chrome");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.trekbikes.com/us/en_US/bikes/mountain-bikes/electric-mountain-bikes/c/B512/");
        WebElement picture = driver.findElement(By.cssSelector("img[id=imageid-32977]"));
        Thread.sleep(500);
        String picSRC = picture.getAttribute("src");
        if(picSRC==null)
             picSRC = driver.findElement(By.cssSelector("img[data-id=imageid-32977]")).getAttribute("SRC");
        for(int i=0;i<1000;i++)
             if(picSRC==null) {

                 picSRC = picture.getAttribute("src");
                 System.out.println( i+ " picSRC: " +picSRC);
             }
             else
                i=1000;
        System.out.println("After for,  picSRC: " +picSRC);
        URL imageURL = new URL(picSRC);

        BufferedImage saveImage = ImageIO.read(imageURL);
        //Thread.sleep(5000);
        ImageIO.write(saveImage, "png", new File("e-bike-32977.png"));
        driver.close();
    }
}

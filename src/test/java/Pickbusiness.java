import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Pickbusiness {


    // Asserting the site's URL
    public static void assretURL(WebDriver driver){
        String url = driver.getCurrentUrl();
        //Assert.assertEquals(url,"AAAAAA");
        Assert.assertEquals(url,"https://buyme.co.il/search?budget=1&category=6&region=11");

    }

    // Go inside the chosen business
    public static void pickbus(WebDriver driver, WebDriverWait wait) throws InterruptedException, Exception{

        // I'm using 'sleep' cause nothing else worked (elementToBeClickable,visibilityOfElementLocated,elementToBeSelected etc')
        Thread.sleep(500);
        driver.findElement(By.cssSelector("div[class=thumbnail]")).click();

        driver.findElement(By.id("ember1543")).sendKeys("70");
        driver.findElement(By.id("ember1543")).sendKeys(Keys.ENTER);
    }
}

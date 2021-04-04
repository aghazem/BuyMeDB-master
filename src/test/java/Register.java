import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class Register {
    public static void registration(WebDriver driver) throws IOException{
        driver.findElement(By.partialLinkText("כניסה")).click();
        // first name
        driver.findElement(By.className("text-btn")).click();
        driver.findElement(By.id("ember1237")).sendKeys("hazem");
        Assert.assertEquals(driver.findElement(By.id("ember1237")).getAttribute("value"),"hazem");

        // E-mail
        // The 'getEmail' will generate new & unused e-mail. I'm saving it for the assert
        String email = GenerateEmail.getEmail();
        driver.findElement(By.id("ember1239")).sendKeys(email);
        Assert.assertEquals(driver.findElement(By.id("ember1239")).getAttribute("value"),email);

        // 1th password
        driver.findElement(By.id("valPass")).sendKeys("hazem123");
        Assert.assertEquals(driver.findElement(By.id("valPass")).getAttribute("value"),"hazem123");

        //2th password
        driver.findElement(By.id("ember1243")).sendKeys("hazem123");
        Assert.assertEquals(driver.findElement(By.id("ember1243")).getAttribute("value"),"hazem123");

        driver.findElement(By.id("ember1242")).sendKeys(Keys.ENTER);
    }
}

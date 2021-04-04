import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class Informationscreen {

    public static void fillInfo(WebDriver driver) throws InterruptedException, IOException {
        Thread.sleep(500);
        driver.findElement(By.xpath("//span[.='למישהו אחר']")).click();
        //Thread.sleep(5000);
        //driver.findElement(By.partialLinkText("יש להשלים את שם המקבל")).sendKeys("John Doe");
        //driver.findElement(By.cssSelector("#ember1664")).sendKeys("John Doe");
        driver.findElement(By.cssSelector("input[data-parsley-group=main]")).sendKeys("John Doe");

        // "Doron" is already written, so it must be erase first
        driver.findElement(By.cssSelector("#ember1666")).clear();
        //driver.findElement(By.xpath("//span[.='?ממי המתנה']")).clear();
        driver.findElement(By.cssSelector("#ember1666")).sendKeys("Jane duh...");

        // What the occasion?
        driver.findElement(By.xpath("//span[.='לאיזה אירוע?']")).click();
        driver.findElements(By.className("active-result")).get(2).click();

        // Warm words....
        driver.findElement(By.cssSelector("textarea[data-parsley-group=main")).clear();
        driver.findElement(By.cssSelector("textarea[data-parsley-group=main")).sendKeys("כפארוש...עד 120");

        // Upload pic
        driver.findElement(By.name("fileUpload")).sendKeys("src/main/resources/e-bike.jpg");
        // Uploading picture from another web-site
        SvPicAnotherSite.svPicAnotherSite();
        driver.findElement(By.name("fileUpload")).sendKeys("src/main/resources/e-bike.jpg");

        // When to send
        driver.findElement(By.className("send-now")).click();

        // e-mail
        driver.findElement(By.xpath("//span[.='במייל']")).click();
        // Creating a new e-mail
        String email = GenerateEmail.getEmail();
        driver.findElement(By.cssSelector("input[type=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("button[type=submit]")).click();

        // Asserting
        Assert.assertEquals(driver.findElement(By.cssSelector("input[data-parsley-group=main]")).getAttribute("value"),"John Doe");
        Assert.assertEquals(driver.findElement(By.cssSelector("#ember1666")).getAttribute("value"),"Jane duh...");



    }
}

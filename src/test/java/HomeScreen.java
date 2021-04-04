import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeScreen {

    public static void homeScreenGoThrough(WebDriver driver) throws InterruptedException {
        // Settint the amount.
        driver.findElement(By.xpath("//span[.='סכום']")).click();
        driver.findElements(By.className("active-result")).get(1).click();
        // Setting the area.
        driver.findElement(By.xpath("//span[.='אזור']")).click();
        driver.findElements(By.className("active-result")).get(2).click();
        // Setting the category
        driver.findElement(By.xpath("//span[.='קטגוריה']")).click();
        driver.findElements(By.className("active-result")).get(14).click();
        // Let's go...
        driver.findElement(By.id("ember840")).click();

    }
}
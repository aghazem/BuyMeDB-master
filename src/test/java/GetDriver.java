import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/*****************************************************************\

\*****************************************************************/
public class GetDriver {
    private static WebDriver driver;

    public static WebDriver getDriverInstance(String browser){
       if(browser.equals("chrome"))
       {      if (driver == null) {
                   System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                   driver = new ChromeDriver();
               }
               return driver;
       }
       return null;
    }
}

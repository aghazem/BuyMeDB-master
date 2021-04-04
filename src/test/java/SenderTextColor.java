//import com.aventstack.extentreports.ExtentReports;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.time.Duration;
//import java.util.StringTokenizer;
//import java.util.concurrent.TimeUnit;
//
////import static javax.swing.UIManager.getColor;
//
////import org.openqa.selenium.support.Color;
////import java.ColorUtils
//
//public class SenderTextColor {
//    private static WebDriver driver;
//    private static WebDriverWait wait;
//    private static ExtentReports extent;
//    private static String a;
//    @BeforeClass
//    public static void openBuyme() {
//        driver = GetDriver.getDriverInstance();
//        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.get("https://buyme.co.il/");
//        System.out.println("AAAAAAAAAAAA");
//    }
//
//    @Test
//    public void test01_navigateTolocation () throws Exception {
//        Register.registration(driver);
//
//        HomeScreen.homeScreenGoThrough(driver);
//
//        Pickbusiness.pickbus(driver, wait);
//
//    }
//    @Test
//    public  void test02_doTheMission() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
//        // The element color. It seems like it's not true....maybe because it is highlighted
//        String color = driver.findElement(By.cssSelector("#ember1579")).getCssValue("color");
//        System.out.println("TTTTTT " +color);
//        String s1 = color.substring(4);
//
//        // converting to RGB
//        color = color.substring(4);
//        color = color.replace(')', ' ');
//        color = color.replace('(', ' ');
//
//        StringTokenizer cl = new StringTokenizer(color);
//        int r = Integer.parseInt(cl.nextToken(",").trim());
//        int g = Integer.parseInt(cl.nextToken(",").trim());
//        int b = Integer.parseInt(cl.nextToken(",").trim());
//
//        // I didn't find the class 'ColorUtils' in Java or in maven, so I just copied it to this directory
//        ColorUtils colorU = new  ColorUtils() ;
//
//        System.out.println("The color is(not...) - " +colorU.getColorNameFromRgb(r,g,b));
//
//
//    }
//}

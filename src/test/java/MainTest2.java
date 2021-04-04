import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/*****************************************************************\

\*****************************************************************/
public class MainTest2 {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static ExtentReports extent ;
    private static ExtentTest test ;
    private static Connection con;
    private static String browser;
    private static String URL;
    private static String updDB;
    private static String textForDB;
    private static String dateTime;
    private static String line;
    private static int resultsDetailedID;
    private static boolean DB_OK;
    private static boolean USEJSON;
    private static BufferedWriter bw;
    private static BufferedWriter bwCSV;
    private static final String USER_NAME = "JbfbjSYbiD";
    private static final String DATABASE_NAME = "JbfbjSYbiD";
    private static final String PASSWORD = "x1Euka4HQK";
    private static final String PORT = "3306";
    private static final String SERVER = "remotemysql.com";

    @BeforeClass
    public static void openBuymeSite() throws Exception {

        String cwd = System.getProperty("user.dir");
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(cwd + "logFile.html");
        USEJSON=false;
        DB_OK=true;
        try {
            con = HandleDB.connectDB();
        }catch (SQLException e)
        { DB_OK=false;
          System.out.println("AAAAA " +e.getSQLState());
        }
        getConfigInfo();
        // Retrieving the results_detailed's current ID
        if(DB_OK)
            resultsDetailedID=HandleDB.getRsltDtldID(con);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // name your test and add description
        test = extent.createTest("MyFirstTest", "Sample description");
        // add custom system info
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("Tester", "hazem");
        // log results
        test.log(Status.INFO, "@Before class");


        boolean driverSW = false;
        try {
              driver = GetDriver.getDriverInstance(browser);
              driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
              driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

              wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driverSW=true;
        }  catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Driver wasn't allocated because " +e.getMessage());
        } finally {
            if (driverSW) {
                test.log(Status.PASS, "Driver was allocated O.K.");
            }
        }

        boolean webSite = false;
        try {
            driver.get(URL);
             webSite=true;
        }  catch (Exception e) {
            e.printStackTrace();

            test.log(Status.FAIL, "WebSite wasn't connected because " +e.getMessage());
        } finally {
            if (webSite) {
                test.log(Status.PASS, "Driver was connected O.K.");
            }
        }

        dateTime = CurDate.curDate();
        // log results
        test.log(Status.INFO, "@Before class");
    }

    @Test
    public void test01_register() throws Exception { // throws IOException {
       boolean register = false;
       try {
             Register.registration(driver);
             register=true;
        }  catch (Exception e) {
           textForDB= "Registration failed because " +e.getMessage();
            filesAndDBH(Status.FAIL);
            e.printStackTrace();
        } finally {
            if (register) {
                textForDB="Registration successed";
                filesAndDBH(Status.PASS);
            }
        }
    }

    @Test
    public void test02_homescreen() throws Exception {//throws InterruptedException {
        boolean homeScreen = false;
        try {
             HomeScreen.homeScreenGoThrough(driver);
             homeScreen=true;
        }  catch (Exception e) {
            e.printStackTrace();
            textForDB="HomeScreen failed because " +e.getMessage();
            filesAndDBH(Status.FAIL);
        } finally {
            if (homeScreen) {
                textForDB="HomeScreen successed";
                filesAndDBH(Status.PASS);
            }
        }
    }

    @Test
    public void test03_pickbusiness() throws Exception { //throws InterruptedException {
        boolean pickbusiness = false;
        try {
             //Pickbusiness.assretURL(driver);
              Pickbusiness.pickbus(driver, wait);
              pickbusiness=true;
        } catch (Exception e) {
            e.printStackTrace();
            textForDB="Business wasn't found because " +e.getMessage();
            filesAndDBH(Status.FAIL);
        } finally{
        if (pickbusiness) {
            textForDB="Business picked up O.K.";
            filesAndDBH(Status.PASS);
        }
     }
    }

    @Test
    public void test04_informationscreen() throws Exception {
        boolean InformationscreenSW = false;
        try {
              Informationscreen.fillInfo(driver);
              InformationscreenSW=true;
        } catch (Exception e) {
            e.printStackTrace();
            textForDB="Informationscreen failed because " +e.getMessage();
            filesAndDBH(Status.FAIL);
        } finally{
            if (InformationscreenSW) {
                textForDB="Informationscreen worked O.K.";
                filesAndDBH(Status.PASS);
            }
        }
    }

    @Test
    public void test05_screenshot() throws Exception {
        boolean screenshotSW = false;
        try {
              ScreenShot.TakeaScreenShot(driver,test);
              screenshotSW=true;
        } catch (Exception e) {
            e.printStackTrace();
            textForDB="ScreenShot wasn't taken because " +e.getMessage();
            filesAndDBH(Status.FAIL);
        } finally{
            if (screenshotSW) {
                textForDB="ScreenShot was taken O.K.";
                filesAndDBH(Status.PASS);
            }
        }
    }

    @AfterClass
    public static void tearDown() throws SQLException, IOException {
        test.log(Status.INFO, "@After test at last....");
        if(DB_OK)
           updDB = HandleDB.insertResults(con,dateTime);
        else
           // The file was open only in case of no-DB
           bw.close();
        bwCSV.close();
        extent.flush();
        // driver.quit();
    }

    /**************************************************************\
    |*  Geting the browser & the web address from DB or xml file.
    \**************************************************************/
    public static void getConfigInfo() throws Exception {
        if(USEJSON)
        {   OkHttpClient client = new OkHttpClient();
            Request request = (new Request.Builder()).url("https://my-json-server.typicode.com/Dgotlieb/JSFakeServer/config").build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            jsonData = jsonData.replace("[","{");
            jsonData = jsonData.replace("]","}");
            jsonData = jsonData.replace("[","{");
            jsonData = jsonData.replace("]","}");
            jsonData = jsonData.replace("l\"","l\":");
            //jsonData = jsonData.replace("e\"","e\",");
            jsonData = jsonData.replaceAll("\\n"," ");
            jsonData = jsonData.replaceAll(" ","");
            JSONObject mainJsonObject = new JSONObject(jsonData);
            JSONObject resultsJson = mainJsonObject.getJSONObject("rates");
//            URL = resultsJson.getString("URL");
//            browser = resultsJson.getString("driver");
            URL = resultsJson.getString("URL");
            browser = resultsJson.getString("driver");
            return;
        }
        if(DB_OK) {
            URL = HandleDB.getConfig(con, "URL");
            // In case the DB is O.K, but there was something wrong with the data
            if (URL == null)
                URL = HandleXML.getData("URL");

            browser = HandleDB.getConfig(con, "browser");
            if (browser == null)
                browser = HandleXML.getData("browserType");
        }
        else{
            URL = HandleXML.getData("URL");
            browser = HandleXML.getData("browserType");
        }
        // Open a file as an alternative for storing the results
        if(!DB_OK) {
            FileWriter fileWritter = new FileWriter("backResultFile.txt", false);
            bw = new BufferedWriter(fileWritter);
            bw.write("New test\n");
            bw.close();
            fileWritter = new FileWriter("backResultFile.txt", true);
            bw = new BufferedWriter(fileWritter);
        }

        // Writing to csv file. In any-case (not just when there's no DB)
        FileWriter fileWritterCSV = new FileWriter("backResultFile.csv", false);
        bwCSV = new BufferedWriter(fileWritterCSV);
        bwCSV.write("test id,test data,test description\n");
        bwCSV.close();
        fileWritterCSV = new FileWriter("backResultFile.csv", true);
        bwCSV = new BufferedWriter(fileWritterCSV);

    }
    /**************************************************************\
    |*  Geting the browser & the web address from DB or xml file.
    \**************************************************************/
    public static void filesAndDBH(Status status) throws Exception {
        test.log(status, textForDB);
        if(DB_OK)
              HandleDB.insertResDetaild(con,resultsDetailedID,textForDB);
        else {
            line = textForDB;
            line = line.concat("\n");
            bw.write(line);
        }
        int strLen =150;
        if(textForDB.length()<strLen)
            strLen=textForDB.length();
        String line2= textForDB.substring(0,strLen);
        line = resultsDetailedID+","+dateTime+","+line2.replaceAll(",","/");
        line = line.concat("\n");
        bwCSV.write(line);
    }
}

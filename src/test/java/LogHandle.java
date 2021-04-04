import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class LogHandle {
    private static String cwd = System.getProperty("user.dir");
    private static ExtentSparkReporter htmlReporter = new ExtentSparkReporter(cwd + "C:\\Users\\hazema\\1test\\BuyMeDB-master\\logFile.html");
    private static ExtentReports  extent = new ExtentReports();
    private static ExtentTest test       = extent.createTest("MyFirstTest", "Sample description"); ;

    public void LogHandle(){
        this.extent.attachReporter(this.htmlReporter);
        this.test = extent.createTest("MyFirstTest", "Sample description");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("Tester", "hazem");
        test.log(Status.INFO, "@Before class");
    }
    public ExtentReports getExtent(){
        return this.extent;
    }

    public void setExtent(ExtentReports extent){
        this.extent=extent;
    }
    public ExtentTest getTest(){
        return this.test;
    }
    public void setTest(Status status,String desc){
        this.test.log(status, desc);
    }

    public void flushLog() {
        extent.flush();
    }

}


package login;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class LoginFacebook {
    @Test
    public void testNGAsserts() throws Exception{
        // Source file (spreadsheet)
        FileInputStream fs = new FileInputStream("./src/test/resources/LoginFacebook.xlsx");
        @SuppressWarnings("resource")
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        DataFormatter df = new DataFormatter();
        String user = df.formatCellValue(sheet.getRow(1).getCell(0));
        String password = df.formatCellValue(sheet.getRow(1).getCell(1));
        
        // Chrome and Firefox to make it work for multiple browsers
        System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();  
       // System.setProperty("webdriver.gecko.driver","./driver/geckodriver.exe");
        //WebDriver driver2 = new FirefoxDriver();
        
        // ExtentReports
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("LoginFacebook.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        ExtentTest logStep = extent.createTest("Login", "Validate login functionality");
        
        logStep.info("Step 1: Open website (Firefox and Chrome)");
        driver.get("https://auth0custm-auth0customfront-main.acklenavenueclient.com/login");  //this link is variable since it is created for every card. Please keep it updated to show the login page
        driver.manage().window().maximize();
        Thread.sleep(1000);
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title, "Payments");
       // driver2.get("https://stripe-boi-frontend-main.acklenavenueclient.com/loginAuth0");
        //driver2.manage().window().maximize();
        //System.out.println(title);
        //Assert.assertEquals(title, "Payments");
        //System.out.println("AssertEquals Test Passed, initial URL is correct");
        Thread.sleep(1000);
    
        //logStep.info("Step 2: Access Login Page");  
    //driver.findElement(By.xpath("//*[@id='root']/div/div/button")).click();
  //  Thread.sleep(3000);
  //  String title2 = driver.getTitle();
  //  System.out.println(title2);
  //  Assert.assertEquals(title2, "Log in | Boilerplate-NoCoding-Template");
   // Thread.sleep(3000);
    
        
        logStep.info("Step 2: Open Facebook Login");       
        driver.findElement(By.xpath("//*[@id='auth0-Facebook-login-button']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys(user);
      //driver2.findElement(By.xpath("//*[@id='username']")).sendKeys(user);
        driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='loginbutton']")).click();
        Thread.sleep(1000);
        
    logStep.info("Step 2: Enter the credentials and click login button");       
        driver.findElement(By.xpath("//*[@id='custom-login-email-input']")).sendKeys(user);
      //driver2.findElement(By.xpath("//*[@id='username']")).sendKeys(user);
        driver.findElement(By.xpath("//*[@id='custom-login-password-input']")).sendKeys(password);
      //driver2.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='custom-login-continue-button']")).click();
      //driver2.findElement(By.xpath("/html/body/div/main/section/div/div[2]/div/form/div[2]/button")).click();
        Thread.sleep(3000);
        
        
     //   driver.findElement(By.xpath("//button[text()='Continue as Boiler']")).click();
      //driver2.findElement(By.xpath("/html/body/div/main/section/div/div[2]/div/form/div[2]/button")).click();
    //    Thread.sleep(1000);
      
        
  
        logStep.info("Step 3: Asserting if user is logged in");     
        String expText = "home";
      //String expText2 = "PRODUCTS";
        String productPage = driver.findElement(By.xpath("//*[@id='root']/div/h1")).getText();
      //String productPage2 = driver2.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        Assert.assertEquals(productPage, expText);
      //Assert.assertEquals(productPage2, expText2);
        System.out.println(productPage);
      //System.out.println(productPage2);
        
        //Create case for failed login, "Wrong username or password"--
        
        
        //Wrong email, password, or code.


     
        logStep.pass("Login success"); 
        driver.close();
      //driver2.close();
   
        logStep.info("End of the test");
    extent.flush();
    }
}
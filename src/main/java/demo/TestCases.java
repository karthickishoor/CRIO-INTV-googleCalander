package demo;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;

import javax.swing.Action;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public WebDriverWait wait;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
         driver.close();
         driver.quit();

    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com/ ");
        if(driver.getCurrentUrl().contains("calendar")){
            System.out.println("The URL of the Calendar homepage contains \"calendar\"");
        }else{
            System.out.println("The URL of the Calendar homepage does not contains \\\"calendar\\\"");
        }
        
        System.out.println("end Test case: testCase01");
    }

    public void Testcase02() throws InterruptedException{
        WebElement viewsdropDownEl = driver.findElement(By.xpath("//button[@aria-describedby='VjyWCf']"));
        viewsdropDownEl.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-accelerator='M']")));
        WebElement monthEl = driver.findElement(By.xpath("//li[@data-accelerator='M']"));
        monthEl.click();
        if(monthEl.getText().contains("Month")){
            System.out.println("month view");
        }else{
            System.out.println("Not in month view");
        }
        WebElement createEl = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[1]/div/div[1]/button/span[5]"));
        Actions action = new Actions(driver); 
        action.click(createEl).perform();
        WebElement taskEl = driver.findElement(By.xpath("//*[@id=\"ucc-1\"]/div/ul/li[2]"));
        taskEl.click();
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement tittleEl= driver.findElement(By.xpath("//input[@aria-label='Add title']"));
        js.executeScript("arguments[0].value='';", tittleEl);
        Thread.sleep(1000);
        tittleEl.click();
        tittleEl.sendKeys("Crio INTV Task Automation");
        WebElement descriptionEl = driver.findElement(By.xpath("//textarea[@placeholder='Add description']"));
        js.executeScript("arguments[0].value='';", descriptionEl);
        descriptionEl.click();
        descriptionEl.sendKeys("Crio INTV Calendar Task Automation"); 
        WebElement saveBtnEl = driver.findElement(By.xpath("(//div[@class='VfPpkd-dgl2Hf-ppHlrf-sM5MNb'])[10]/button"));
        saveBtnEl.click();       
        WebElement eventBtnEl = driver.findElement(By.xpath("//*[@id=\"YPCqFe\"]/div/div/div/div[2]/div[2]/div[3]/div/div[2]/div/div/div"));
        eventBtnEl.click();
        Thread.sleep(1000);
        WebElement checkdescEl=driver.findElement(By.xpath("//div[@id='xDetDlgDesc']"));
        //System.out.println(checkdescEl.getText());
        if(checkdescEl.getText().contains("Crio INTV Calendar Task Automation")){
            System.out.println("Description is updated");
        }else{
            System.out.println("Description is not updated");
        }
        System.out.println("The Calendar switched to month view and a task was created");
        
    //    WebElement dissmissEl = driver.findElement(By.xpath("(//button[@data-id='IYtByb'])[2]"));
    //    dissmissEl.click();

    }
    public void Testcase03() throws InterruptedException{
        List<WebElement> eventsListEl = driver.findElements(By.xpath("(//div[@role='presentation'])[9]/div"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(WebElement event : eventsListEl){
            event.click();                        
            WebElement updateBtnEl = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div/div/div[2]/span/div/div[1]/div/div/div[2]/div[1]/span/button"));
            wait.until(ExpectedConditions.visibilityOf(updateBtnEl));
            updateBtnEl.click();   
            Thread.sleep(1000);    
            WebElement descriptionEl = driver.findElement(By.xpath("//textarea[@placeholder='Add description']"));
            js.executeScript("arguments[0].value='';", descriptionEl);
            descriptionEl.click();
            js.executeScript("arguments[0].value='';", descriptionEl);
            descriptionEl.sendKeys("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");
            // if(descriptionEl.getText().contains("Crio INTV Task Automation is a test suite designed for automating various tasks on the google Calendar web application")){
            //     System.out.println("The task description is successfully updated and displayed");
            // }else{
            //     System.out.println("The task description is not displayed");
            // }
            WebElement saveBtnEl = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[1]/div/div[2]/span/div/div[8]/div/button"));
            saveBtnEl.click();
            Thread.sleep(1000);
            WebElement taskEl = driver.findElement(By.xpath("//*[@id=\"YPCqFe\"]/div/div/div/div[2]/div[2]/div[3]/div/div[2]/div/div/div"));
            taskEl.click();
            Thread.sleep(1000);
            WebElement updateddescEl = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/span/div/div[2]/div[3]/div[2]")) ;   
            //WebElement updateddescpEl = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/span/div/div[6]/div[2]/div/div/div/textarea"));            
            System.out.println(updateddescEl.getText());                       
            if(updateddescEl.getText().contains("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application")){ //previous atech person told to write it with isdisplayed()
                System.out.println("The task description is displayed");
            }else{
                System.out.println("The task description is not displayed");
            }
        }
    }



    public void Testcase04() throws InterruptedException{
        driver.navigate().refresh();
        Thread.sleep(1000);
        List<WebElement> eventsListEl = driver.findElements(By.xpath("//*[@id=\"YPCqFe\"]/div/div/div/div[2]/div[2]/div[3]/div/div[2]/div/div"));
        for(WebElement event : eventsListEl){
            event.click();
            Thread.sleep(1000);
            WebElement deleteBtnEl = driver.findElement(By.xpath("//button[@aria-label='Delete task']"));
            
            //wait.until(ExpectedConditions.visibilityOf(deleteBtnEl));
            deleteBtnEl.click();
            System.out.println("deleted");
        }
        // Alert alertobj = driver.switchTo().alert();
        // System.out.println(alertobj.getText());
        Thread.sleep(1000);
        WebElement deleteMsgIsDisplayed = driver.findElement(By.xpath("//div[@class='VYTiVb']"));
        wait.until(ExpectedConditions.visibilityOf(deleteMsgIsDisplayed));
        System.out.println(deleteMsgIsDisplayed.getText());
        if(deleteMsgIsDisplayed.getText().contains("Task deleted")){
            System.out.println("The task is successfully deleted, and the confirmation message indicates \"Task deleted\"");
        }else{
            System.out.println("The task is not deleted");
        }
        Thread.sleep(1000);
        
        // WebElement deletEventEl = driver.findElement(By.xpath("//button[@aria-label='Delete event']"));
        // wait.until(ExpectedConditions.visibilityOf(deletEventEl));
        // deletEventEl.click();
        
    }




}

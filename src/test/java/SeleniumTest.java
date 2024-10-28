import model.DataUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.PaymentProcess;
import pages.SelectQuantity;

import java.time.Duration;

public class SeleniumTest {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @BeforeTest
    public void Start(){

        //Modify your ChromeDriver path accordingly
        System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\Downloads\\Selenium\\drivers//chromedriver.exe");
        driver.get("https://demo.guru99.com/payment-gateway/index.php");
        driver.manage().window().maximize();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inner']//h2")));

        WebElement title = driver.findElement(By.xpath("//div[@class='inner']//h2"));
        Assert.assertEquals(title.getText(), "Mother Elephant With Babies Soft Toy");

    }

    @Test(description = "Submit a form", dataProviderClass = DataUtil.class, dataProvider = "JsonDataProvider")
    public void Test(String data) throws InterruptedException {

        String[] formInfo = data.split(",");
        SelectQuantity selectQuantity = new SelectQuantity();
        selectQuantity.inputQuantity(driver);

        PaymentProcess paymentProcess = new PaymentProcess();
        paymentProcess.inputPaymentInfo(driver, formInfo[0], formInfo[1], formInfo[2], formInfo[3]);
        paymentProcess.verifyPaymentSuccessful(driver);
    }

    @AfterTest
    public void EndTest() throws InterruptedException {
        Thread.sleep(3000);
        //driver.close();
        driver.quit();
    }
}


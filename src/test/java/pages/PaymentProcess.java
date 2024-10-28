package pages;

import base.PageBase;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class PaymentProcess extends PageBase {

/*    By cardNum = By.xpath("//input[@id='card_nmuber']");
    By expMonth = By.xpath("//input[@id='month']");
    By expYear = By.xpath("//input[@id='year']");
    By cvv = By.xpath("//input[@id='cvv_code']");*/

    //Input the payment details using dat from json file
    public PaymentProcess inputPaymentInfo(WebDriver driver, String cardNo, String month, String year, String cvvNo) throws InterruptedException {

        WebElement cardNum = driver.findElement(By.xpath("//input[@id='card_nmuber']"));
        Select expMonth = new Select(driver.findElement(By.xpath("//select[@id='month']")));
        Select expYear = new Select(driver.findElement(By.xpath("//select[@id='year']")));
        WebElement cvv = driver.findElement(By.xpath("//input[@id='cvv_code']"));
        WebElement buyBtn = driver.findElement(By.xpath("//input[@class='button special']"));


        cardNum.sendKeys(cardNo);
        expMonth.selectByValue(String.valueOf(month));
        expYear.selectByValue(String.valueOf(year));
        cvv.sendKeys(cvvNo);

        Thread.sleep(3000);

        buyBtn.click();

        return this;
    }

    //Verify payment is completed and save the order ID
    public PaymentProcess verifyPaymentSuccessful(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement successMsg = driver.findElement(By.xpath("//div[@class='inner']//h2"));
        WebElement orderId = driver.findElement(By.xpath("(//div[@class='inner']//h3)[2]"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inner']//h2")));
        Assert.assertEquals(successMsg.getText(), "Payment successfull!");

        String savedID = orderId.getText();
        System.out.println("Order ID = " + savedID);

        return this;
    }

}

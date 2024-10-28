package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectQuantity extends PageBase {

    public SelectQuantity inputQuantity(WebDriver driver) throws InterruptedException {

        WebElement buyBtn = driver.findElement(By.xpath("//input[@class='button special']"));
        Select quantity = new Select(driver.findElement(By.xpath("//select[@name='quantity']")));
        int qty = randomNum(1, 9);
        quantity.selectByValue(String.valueOf(qty));
        System.out.println("The quantity selected is " + qty);
        Thread.sleep(3000);
        buyBtn.click();

        WebElement paymentAmt = driver.findElement(By.xpath("//div[@class='row']//font[2]"));
        System.out.println(paymentAmt.getText());

        return this;
    }

}

package base;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class PageBase {

    public int randomNum(int min, int max){

        Random random = new Random();

        int r = random.ints(min, max + 1).findFirst().getAsInt();

        //int quantity = 1+random.nextInt(9);

        return r;
    }

}

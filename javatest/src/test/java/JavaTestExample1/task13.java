package JavaTestExample1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class task13 {
    private static WebDriver chromeDriver;
    private WebDriverWait waitInChrome;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        waitInChrome = new WebDriverWait(chromeDriver, 10);
    }

    @Test
    public void FirstTestInChrome() {
        chromeDriver.get("http://litecart.stqa.ru/en/");
        waitInChrome.until(titleIs("Online Store | My Store"));
        // Put ducks into the basket
        for (int i = 0; i < 3; i++) {
            chromeDriver.findElements(By.cssSelector(".product")).get(0).click();
            waitInChrome.until(visibilityOf(chromeDriver.findElement(By.cssSelector("#tab-information"))));
            try {
                new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("[name='options[Size]']"))).click().sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).perform();
            } catch (Exception e) {
            }
            chromeDriver.findElements(By.cssSelector(".buy_now button[type='Submit']")).get(0).click();
            waitInChrome.until(stalenessOf(chromeDriver.findElement(By.cssSelector("div[style^='background']"))));
        }
        // Remove ducks from the basket
        int quantity = Integer.parseInt(chromeDriver.findElement(By.cssSelector("span.quantity")).getText());
        chromeDriver.findElement(By.xpath("//*[text()='Checkout »']")).click();
        waitInChrome.until(visibilityOf(chromeDriver.findElement(By.cssSelector(".phone"))));
        waitInChrome.withTimeout(Duration.ofSeconds(1));
        for (int j = 0; j < quantity+1; j++) {
            if(chromeDriver.findElements(By.cssSelector(".item")).size()==0) {
                return;
            } else {
                WebElement itemToDelete = chromeDriver.findElements(By.cssSelector("ul.items")).get(0);
                if (itemToDelete.getCssValue("margin-left").equals("0px")) {
                    try {
                        for(int a=0; a<quantity+1; a++) {
                            chromeDriver.findElements(By.cssSelector("button[value='Remove']")).get(a).click();
                        }
                    }
                    catch (Exception e) {}
                }
                waitInChrome.until(visibilityOf(chromeDriver.findElement(By.cssSelector("body[style]"))));
            }
            quantity = chromeDriver.findElements(By.cssSelector("td[style$='center;']")).size();
        }
        // Remove last duck from the basket
        waitInChrome.until(visibilityOf(chromeDriver.findElement(By.cssSelector("body[style]"))));
        chromeDriver.findElements(By.cssSelector("button[value='Remove']")).get(0).click();
    }

    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}
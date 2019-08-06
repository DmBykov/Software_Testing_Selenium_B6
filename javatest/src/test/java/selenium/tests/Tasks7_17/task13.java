package selenium.tests.Tasks7_17;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        chromeDriver.findElement(By.xpath("//*[text()='Checkout »']")).click();
        waitInChrome.until(visibilityOf(chromeDriver.findElement(By.cssSelector(".phone"))));
        int quantity = chromeDriver.findElements(By.cssSelector("td[style$='center;']")).size();
        for (int j = 0; j < quantity; j++) {
            if (chromeDriver.findElements(By.cssSelector(".item")).size() == 0) {
                return;
            } else {
                for (int a = 0; a < quantity; a++) {
                    try {
                        chromeDriver.findElements(By.cssSelector("button[value='Remove']")).get(a).click();
                    } catch (Exception e) {
                    }
                }
                waitInChrome.until(ExpectedConditions.attributeToBe(By.cssSelector("div#checkout-cart-wrapper"), "style", "opacity: 1;"));
            }
        }
    }

    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}
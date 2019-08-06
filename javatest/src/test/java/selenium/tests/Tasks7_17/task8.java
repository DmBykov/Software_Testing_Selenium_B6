package selenium.tests.Tasks7_17;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class task8 {
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
        chromeDriver.get("http://localhost/litecart/en/");
        waitInChrome.until(titleIs("Online Store | My Store"));

        List<WebElement> items = chromeDriver.findElements(By.cssSelector("li[class^=product]")); //".link[title$='Duck']"));
        int size = items.size();
        for (int i = 0; i < size; i++) {
            List<WebElement> stickers = items.get(i).findElements(By.cssSelector("div[class^='sticker']"));
            Assert.assertEquals(stickers.size(), 1);
            Assert.assertTrue(stickers.get(0).isDisplayed());
        }
    }

    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}
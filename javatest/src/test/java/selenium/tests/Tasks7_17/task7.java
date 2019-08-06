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

public class task7 {
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
        chromeDriver.get("http://localhost/litecart/admin/.");
        chromeDriver.findElement(By.name("username")).sendKeys("admin");
        chromeDriver.findElement(By.name("password")).sendKeys("admin");
        chromeDriver.findElement(By.name("login")).click();
        waitInChrome.until(titleIs("My Store"));
        List<WebElement> elements = chromeDriver.findElements(By.cssSelector("#app-"));
        int size = elements.size();
        try {
            for (int i = 0; i < size; i++) {
                elements.get(i).click();
                Assert.assertTrue(chromeDriver.findElement(By.cssSelector("h1")).isDisplayed());
                WebElement selectedElement = chromeDriver.findElements(By.className("selected")).get(0);
                List<WebElement> elementsInside = selectedElement.findElements(By.className("name"));
                int sizeInside = elementsInside.size();
                for (int j = 2; j < sizeInside; j++) {
                    elementsInside.get(j).click();
                    Assert.assertTrue(chromeDriver.findElement(By.cssSelector("h1")).isDisplayed());
                    List<WebElement> elementsInsideAfterClick = chromeDriver.findElements(By.cssSelector("#app- span.name"));
                    elementsInside = elementsInsideAfterClick;
                }
                List<WebElement> elementsAfterClick = chromeDriver.findElements(By.cssSelector("[id='app-']:not(.selected)"));
                elements = elementsAfterClick;
            }
        } catch (IndexOutOfBoundsException e) {
        }
    }


    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}
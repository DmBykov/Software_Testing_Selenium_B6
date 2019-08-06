package selenium.tests.Tasks7_17;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class task14 {
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
        chromeDriver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        waitInChrome.until(titleIs("Countries | My Store"));
        List<WebElement> countriesEditButtons = chromeDriver.findElements(By.cssSelector("a[title='Edit']"));
        countriesEditButtons.get(0).click();
        waitInChrome.until(titleIs("Edit Country | My Store"));
        List<WebElement> redirectLinks = chromeDriver.findElements(By.cssSelector(".fa-external-link"));
        for (int i = 0; i < redirectLinks.size(); i++) {

// запоминаем идентификатор текущего окна
            String originalWindow = chromeDriver.getWindowHandle();
// запоминаем идентификаторы уже открытых окон
            Set<String> existingWindows = chromeDriver.getWindowHandles();
// кликаем кнопку, которая открывает новое окно
            redirectLinks.get(i).click();
// ждем появления нового окна, с новым идентификатором
            String newWindow = (String) waitInChrome.until(anyWindowOtherThan(existingWindows));
// переключаемся в новое окно
            chromeDriver.switchTo().window(newWindow);
// ждем загрузки страницы
            isExternalPageOpen(chromeDriver);
// закрываем его
            chromeDriver.close();
// и возвращаемся в исходное окно
            chromeDriver.switchTo().window(originalWindow);
        }
    }

    private ExpectedCondition anyWindowOtherThan(Set<String> existingWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(existingWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    private Boolean isExternalPageOpen(WebDriver driver) {
        try {
            return driver.findElements(By.xpath("//*[text()='Log in']")).size() > 0;
        } catch (Exception e) {
            return null;
        }
    }

    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}
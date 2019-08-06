package selenium.tests.Tasks7_17;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class task17 {
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
        chromeDriver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        waitInChrome.until(titleIs("Catalog | My Store"));
        int numberLinks = chromeDriver.findElements(By.cssSelector(".dataTable a[href]:not([title='Edit'])")).size();
        for (int i = 0; i < numberLinks+2; i++) {
            if (chromeDriver.findElements(By.xpath("//h1[text()=' Catalog']")).size() == 0) {
                isProductPageOpen(chromeDriver);
                chromeDriver.findElement(By.cssSelector("button[name='cancel']")).click();
                waitInChrome.until(titleIs("Catalog | My Store"));
                i--;
            } else {
                chromeDriver.findElements(By.cssSelector(".dataTable a[href]:not([title='Edit'])")).get(i).click();
            }
        }
        Assert.assertEquals("Message: Browser log(s) is exist:\n", chromeDriver.manage().logs().get("browser").getAll().size(), 0);
        for (LogEntry l : chromeDriver.manage().logs().get("browser").getAll()) {
            System.out.println(l);
        }
    }

    private Boolean isProductPageOpen(WebDriver driver) {
        try {
            return driver.findElements(By.xpath("//h1[contains(text(),'Edit Product')]")).size() > 0;
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
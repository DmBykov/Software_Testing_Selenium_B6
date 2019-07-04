package JavaTestExample1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class javaTest {
    private static WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");
//        System.setProperty("webdriver.gecko.driver", "C:\\tools\\geckodriver-v0.24.0-win64\\geckodriver.exe");
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void FirstTest() {
        driver.get("http://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("selenium");
        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("selenium - Поиск в Google"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

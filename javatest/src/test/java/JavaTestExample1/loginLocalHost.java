package JavaTestExample1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class loginLocalHost {
    private static WebDriver chromeDriver, geckoDriver, ieDriver;
    private WebDriverWait waitInChrome, waitInGecko, waitInIE;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        waitInChrome = new WebDriverWait(chromeDriver, 10);
        System.setProperty("webdriver.gecko.driver", "C:\\tools\\geckodriver.exe");
        geckoDriver = new FirefoxDriver();
        waitInGecko = new WebDriverWait(geckoDriver, 10);
        System.setProperty("webdriver.ie.driver", "C:\\tools\\IEDriverServer.exe");
        ieDriver = new InternetExplorerDriver();
        waitInIE = new WebDriverWait(ieDriver, 10);
    }

    @Test
    public void FirstTestInChrome() {
        chromeDriver.get("http://localhost/litecart/admin/.");
        chromeDriver.findElement(By.name("username")).sendKeys("admin");
        chromeDriver.findElement(By.name("password")).sendKeys("admin");
        chromeDriver.findElement(By.name("login")).click();
        waitInChrome.until(titleIs("My Store"));
    }

    @Test
    public void SecondTestInFirefox() {
        geckoDriver.get("http://localhost/litecart/admin/.");
        geckoDriver.findElement(By.name("username")).sendKeys("admin");
        geckoDriver.findElement(By.name("password")).sendKeys("admin");
        geckoDriver.findElement(By.name("login")).click();
        waitInGecko.until(titleIs("My Store"));
    }

    @Test
    public void ThirdTestInIE() {
        ieDriver.get("http://localhost/litecart/admin/.");
        ieDriver.findElement(By.name("username")).sendKeys("admin");
        ieDriver.findElement(By.name("password")).sendKeys("admin");
        ieDriver.findElement(By.name("login")).click();
        waitInIE.until(titleIs("My Store"));
    }

    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
        geckoDriver.quit();
        geckoDriver = null;
        ieDriver.quit();
        ieDriver = null;
    }
}
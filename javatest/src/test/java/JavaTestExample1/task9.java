package JavaTestExample1;

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
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class task9 {
    private static WebDriver chromeDriver;
    private WebDriverWait waitInChrome;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        waitInChrome = new WebDriverWait(chromeDriver, 10);
    }

    @Test
    public void NinthTestInChrome() {
        chromeDriver.get("http://localhost/litecart/admin/.");
        chromeDriver.findElement(By.name("username")).sendKeys("admin");
        chromeDriver.findElement(By.name("password")).sendKeys("admin");
        chromeDriver.findElement(By.name("login")).click();
        waitInChrome.until(titleIs("My Store"));
        chromeDriver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countries = chromeDriver.findElements(By.cssSelector("[name='countries_form'] a:not([title='Edit'])"));
        List<String> sortedCountries = countries.stream().map(p -> p.getText()).sorted().collect(Collectors.toList());
        List<String> actualCountries = countries.stream().map(p -> p.getText()).collect(Collectors.toList());
        Assert.assertEquals(actualCountries, sortedCountries);
        int sizeCountriesList = countries.size();
        for(int i=0; i<sizeCountriesList; i++) {
            Integer numberOfStates = Integer.valueOf(chromeDriver.findElements(By.xpath("//td[6]")).get(i).getText());
            if (!(numberOfStates == 0)) {
                countries.get(i).click();
                waitInChrome.until(titleIs("Edit Country | My Store"));
                List<WebElement> states = chromeDriver.findElements(By.cssSelector("table#table-zones input[name^='zones'][name$='[name]']"));
                List<String> sortedStates = states.stream().map(p -> p.getText()).sorted().collect(Collectors.toList());
                List<String> actualStates = states.stream().map(p -> p.getText()).collect(Collectors.toList());
                Assert.assertEquals(actualStates, sortedStates);
                chromeDriver.findElement(By.cssSelector("[name='cancel']")).click();
                waitInChrome.until(titleIs("Countries | My Store"));
                countries = chromeDriver.findElements(By.cssSelector("[name='countries_form'] a:not([title='Edit'])"));
            }
        }
    }

    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}

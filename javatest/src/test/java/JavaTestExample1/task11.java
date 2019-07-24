package JavaTestExample1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class task11 {
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
        String email = getRandomEmail();
        String password = getRandomString();
        chromeDriver.get("http://localhost/litecart/en/");
        waitInChrome.until(titleIs("Online Store | My Store"));
        chromeDriver.findElement(By.xpath("//*[text()='New customers click here']")).click();
        //Set name, address
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='firstname']"))).click().sendKeys(getRandomString()).perform();
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='lastname']"))).click().sendKeys(getRandomString()).perform();
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='address1']"))).click().sendKeys(getRandomString()).perform();
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='postcode']"))).click().sendKeys(getRandomInt()).perform();
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='city']"))).click().sendKeys("Washington").perform();//getRandomString()).perform();
        //Select Country
        chromeDriver.findElement(By.cssSelector(".selection>span")).click();
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector(".select2-search"))).click().sendKeys("states" + Keys.DOWN + Keys.ENTER).perform();
        //Set email
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='email']"))).click().sendKeys(email).perform();
        //Set phone
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='phone']"))).click().sendKeys(getRandomPhoneNumber()).perform();
        //Set password
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='password']"))).click().sendKeys(password).perform();
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='confirmed_password']"))).click().sendKeys(password).perform();
        chromeDriver.findElement(By.cssSelector("[name='create_account']")).click();
        //Set zone
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("[name='zone_code']"))).click().sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).perform();
        //Create account
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='password']"))).click().sendKeys(password).perform();
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='confirmed_password']"))).click().sendKeys(password).perform();
        chromeDriver.findElement(By.cssSelector("[name='create_account']")).click();
        waitInChrome.until(titleIs("Online Store | My Store"));
        //Logout
        chromeDriver.findElements(By.xpath("//*[text()='Logout']")).get(0).click();
        waitInChrome.until(titleIs("Online Store | My Store"));
        //Login
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='email']"))).click().sendKeys(email).perform();
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='password']"))).click().sendKeys(password).perform();
        chromeDriver.findElement(By.cssSelector("[name='login']")).click();
        waitInChrome.until(titleIs("Online Store | My Store"));
        //Logout
        chromeDriver.findElements(By.xpath("//*[text()='Logout']")).get(0).click();

    }

    private String getRandomString() {
        String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder string = new StringBuilder();
        Random rnd = new Random();
        while (string.length() < 10) {
            int index = (int) (rnd.nextFloat() * CHARS.length());
            string.append(CHARS.charAt(index));
        }
        return string.toString();
    }

    private String getRandomInt() {
        String CHARS = "1234567890";
        StringBuilder string = new StringBuilder();
        Random rnd = new Random();
        while (string.length() < 5) {
            int index = (int) (rnd.nextFloat() * CHARS.length());
            string.append(CHARS.charAt(index));
        }
        return string.toString();
    }

    private String getRandomPhoneNumber() {
        return "+191612" + getRandomInt();
    }

    private String getRandomEmail() {
        return getRandomString() + "@email.com";
    }

    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}

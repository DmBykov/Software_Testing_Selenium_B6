package selenium.tests.Tasks7_17;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class task12 {
    private static final String UPLOAD_FILE_PATH = "D:\\IdeaProjects\\Software_Testing_Selenium_B6\\javatest\\";
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
        chromeDriver.findElement(By.xpath("//*[text()='Catalog']")).click();
        chromeDriver.findElement(By.cssSelector(".button[href$='product']")).click();
        //Set Status
        chromeDriver.findElement(By.cssSelector("input[type='radio'][value='1']")).click();
        //Set Name
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='name[en]']"))).click().sendKeys("Sherlock duck").perform();
        //Set Code
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='code']"))).click().sendKeys(getRandomString()).perform();
        //Set Categories
        chromeDriver.findElement(By.cssSelector("input[data-priority='1']")).click();
        //Set Default Category
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("select[name='default_category_id']"))).click().sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).perform();
        //Set Gender
        chromeDriver.findElement(By.xpath("//*[text()='Unisex']/..//input")).click();
        //Set Quantity
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='quantity']"))).click().sendKeys(getRandomInt()).perform();
        //Upload image
        chromeDriver.findElement(By.cssSelector("input[type='file']")).sendKeys(getAbsolutePath("src\\test\\resources\\1021066251.jpg"));
        //Set Date Valid From
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='date_valid_from']"))).click().sendKeys(Keys.UP, Keys.LEFT, Keys.UP, Keys.LEFT, Keys.UP).perform();
        //Set Date Valid To
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='date_valid_to']"))).click().sendKeys(Keys.UP, Keys.LEFT, Keys.DOWN, Keys.LEFT, Keys.DOWN).perform();
        //Click Information
        chromeDriver.findElement(By.xpath("//*[text()='Information']")).click();
        //Set Manufacturer
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("[name='manufacturer_id']"))).click().sendKeys(Keys.DOWN, Keys.ENTER).perform();
        //Set Keywords
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='keywords']"))).click().sendKeys("duck", "sherlock").perform();
        //Set Short Description
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("input[name='short_description[en]']"))).click().sendKeys("yellow duck").perform();
        //Set Description
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector(".trumbowyg-editor"))).click().sendKeys("yellow duck in sherlock's suit").perform();
        //Set Head Title
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("[name='head_title[en]']"))).click().sendKeys("Duck").perform();
        //Set Meta Description
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("[name='meta_description[en]']"))).click().sendKeys("toy").perform();
        //Click Prices
        chromeDriver.findElement(By.xpath("//*[text()='Prices']")).click();
        //Set Purchase Price
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("[name='purchase_price']"))).click().sendKeys(Keys.DELETE).sendKeys("10").perform();
        //Set USD
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("[name='purchase_price_currency_code']"))).click().sendKeys(Keys.DOWN, Keys.ENTER).perform();
        //Set Price
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("[name='prices[USD]']"))).click().sendKeys("12").perform();
        //Click Save button
        chromeDriver.findElement(By.xpath("//*[text()='General']")).click();
        chromeDriver.findElement(By.cssSelector("[name='save']")).click();
    }

    private static String getAbsolutePath(String filePath) {
        return Paths.get(filePath).isAbsolute() ? filePath : Paths.get(UPLOAD_FILE_PATH + filePath).toAbsolutePath().toString();
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
        while (string.length() < 2) {
            int index = (int) (rnd.nextFloat() * CHARS.length());
            string.append(CHARS.charAt(index));
        }
        return string.toString();
    }

    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}

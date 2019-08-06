package selenium.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    protected WebDriver chromeDriver;
    protected WebDriverWait waitInChrome;

    public Page(WebDriver driver) {
        this.chromeDriver = driver;
        waitInChrome = new WebDriverWait(driver, 10);
    }
}
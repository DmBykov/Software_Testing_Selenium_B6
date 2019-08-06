package selenium.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        chromeDriver.get("http://litecart.stqa.ru/en/");
    }

    public Class<ProductPage> selectAvailableProduct() {
        chromeDriver.findElements(By.cssSelector(".product")).get(0).click();
        return ProductPage.class;
    }

    public MainPage waitForPageLoaded() {
        waitInChrome.until(visibilityOf(chromeDriver.findElement(By.cssSelector("li img[alt='ACME Corp.']"))));
        return this;
    }

    public Class<BasketPage> goToBasket() {
        chromeDriver.findElement(By.xpath("//*[text()='Checkout »']")).click();
        return BasketPage.class;
    }
}

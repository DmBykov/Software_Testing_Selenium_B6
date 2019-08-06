package selenium.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage waitForPageLoaded() {
        waitInChrome.until(visibilityOf(chromeDriver.findElement(By.cssSelector("#tab-information"))));
        return this;
    }

    public ProductPage addSelectedProductInBasket() {
        try {
            new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.cssSelector("[name='options[Size]']"))).click().sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).perform();
        } catch (Exception e) {
        }
        chromeDriver.findElements(By.cssSelector(".buy_now button[type='Submit']")).get(0).click();
        waitInChrome.until(stalenessOf(chromeDriver.findElement(By.cssSelector("div[style^='background']"))));
        return this;
    }

    public Class<MainPage> returnToMainPage() {
        chromeDriver.findElement(By.cssSelector("img[title='My Store']")).click();
        return MainPage.class;
    }
}

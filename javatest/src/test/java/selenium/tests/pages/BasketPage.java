package selenium.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BasketPage extends Page {
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public BasketPage waitForPageLoaded() {
        waitInChrome.until(visibilityOf(chromeDriver.findElement(By.cssSelector(".phone"))));
        return this;
    }

    public BasketPage removeAllProducts() {
        int quantity = chromeDriver.findElements(By.cssSelector("td[style$='center;']")).size();
        for (int j = 0; j < quantity; j++) {
            if (chromeDriver.findElements(By.cssSelector(".item")).size() == 0) {
                return this;
            } else {
                for (int a = 0; a < quantity; a++) {
                    try {
                        chromeDriver.findElements(By.cssSelector("button[value='Remove']")).get(a).click();
                    } catch (Exception e) {
                    }
                }
                waitInChrome.until(ExpectedConditions.attributeToBe(By.cssSelector("div#checkout-cart-wrapper"), "style", "opacity: 1;"));
            }
        }
        return this;
    }
}


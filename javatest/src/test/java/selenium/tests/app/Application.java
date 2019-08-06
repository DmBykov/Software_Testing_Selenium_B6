package selenium.tests.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.tests.pages.BasketPage;
import selenium.tests.pages.MainPage;
import selenium.tests.pages.ProductPage;

public class Application {

    private WebDriver chromeDriver;

    private MainPage mainPage;
    private ProductPage productPage;
    private BasketPage basketPage;

    public Application() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        mainPage = new MainPage(chromeDriver);
        productPage = new ProductPage(chromeDriver);
        basketPage = new BasketPage(chromeDriver);
    }

    public void quit() {
        chromeDriver.quit();
    }

    public void buyNewProducts(int numberOfProducts) {
        mainPage.open();
        for (int i = 0; i < numberOfProducts; i++) {
            mainPage.selectAvailableProduct();
            productPage
                    .waitForPageLoaded()
                    .addSelectedProductInBasket()
                    .returnToMainPage();
            mainPage.waitForPageLoaded();
        }
    }

    public void goToBasket() {
        mainPage.goToBasket();
        basketPage.waitForPageLoaded();
    }

    public void removeAllProductsFromBasket() {
        basketPage
                .waitForPageLoaded()
                .removeAllProducts();

    }
}
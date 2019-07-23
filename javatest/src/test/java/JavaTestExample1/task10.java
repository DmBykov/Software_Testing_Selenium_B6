package JavaTestExample1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class task10 {
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
        chromeDriver.get("http://localhost/litecart/en/");
        waitInChrome.until(titleIs("Online Store | My Store"));

        List<WebElement> items = chromeDriver.findElements(By.cssSelector("#box-campaigns [class^='product']"));
        String mainName = items.get(0).findElement(By.cssSelector(".name")).getText();
        WebElement mainRegularPrice = items.get(0).findElement(By.cssSelector(".regular-price"));
        WebElement mainCampaignPrice = items.get(0).findElement(By.cssSelector(".campaign-price"));
        String mainRegularPriceText = mainRegularPrice.getText();
        String mainCampaignPriceText = mainCampaignPrice.getText();
        Assert.assertEquals(mainRegularPrice.getCssValue("text-decoration-line"), "line-through");
        Assert.assertTrue(Integer.parseInt(mainRegularPrice.getCssValue("font-weight")) < 410);
        Assert.assertTrue(Integer.parseInt(mainCampaignPrice.getCssValue("font-weight")) > 590);
        checkGreyColor(mainRegularPrice);
        checkRedColor(mainCampaignPrice);
        String mainRegularPriceFontSize = mainRegularPrice.getCssValue("font-size");
        String mainCampaignPriceFontSize = mainCampaignPrice.getCssValue("font-size");
        Assert.assertTrue(convertStringIntoInteger(mainCampaignPriceFontSize) > convertStringIntoInteger(mainRegularPriceFontSize));

        items.get(0).click();
        List<WebElement> pageItem = chromeDriver.findElements(By.cssSelector("#box-product"));
        Assert.assertEquals(pageItem.size(), 1);
        String pageName = pageItem.get(0).findElement(By.cssSelector("h1")).getText();
        WebElement pageRegularPrice = pageItem.get(0).findElement(By.cssSelector(".regular-price"));
        WebElement pageCampaignPrice = pageItem.get(0).findElement(By.cssSelector(".campaign-price"));
        String pageRegularPriceText = pageRegularPrice.getText();
        String pageCampaignPriceText = pageCampaignPrice.getText();

        Assert.assertTrue(Integer.parseInt(pageRegularPrice.getCssValue("font-weight")) < 410);
        Assert.assertTrue(Integer.parseInt(pageCampaignPrice.getCssValue("font-weight")) > 590);
        checkGreyColor(pageRegularPrice);
        checkRedColor(pageCampaignPrice);
        String pageRegularPriceFontSize = pageRegularPrice.getCssValue("font-size");
        String pageCampaignPriceFontSize = pageCampaignPrice.getCssValue("font-size");
        Assert.assertTrue(convertStringIntoInteger(pageRegularPriceFontSize) < convertStringIntoInteger(pageCampaignPriceFontSize));
        
        Assert.assertEquals(mainName, pageName);
        Assert.assertEquals(mainRegularPriceText, pageRegularPriceText);
        Assert.assertEquals(mainCampaignPriceText, pageCampaignPriceText);
    }

    private void checkRedColor(WebElement el){
        Color color = Color.fromString(el.getCssValue("color"));
        Assert.assertEquals("Campaign price is not red.", color.getColor().getBlue(), 0);
        Assert.assertEquals("Campaign price is not red.", color.getColor().getGreen(), 0);
    }

    private void checkGreyColor(WebElement el){
        Color color = Color.fromString(el.getCssValue("color"));
        Assert.assertEquals("Regular price is not grey.", color.getColor().getRed(), color.getColor().getGreen());
        Assert.assertEquals("Regular price is not grey.", color.getColor().getGreen(), color.getColor().getBlue());
    }

    private double convertStringIntoInteger(String el) {
        String el1 = el.split("px")[0];
        return Double.parseDouble((el1));
    }


    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}

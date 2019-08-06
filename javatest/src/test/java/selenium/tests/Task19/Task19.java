package selenium.tests.Task19;

import org.junit.Test;

public class Task19 extends TestBase{
    private static final int PRODUCTS_SIZE = 3;

    @Test
    public void test19() {
        app.buyNewProducts(PRODUCTS_SIZE);
        app.goToBasket();
        app.removeAllProductsFromBasket();
    }
}

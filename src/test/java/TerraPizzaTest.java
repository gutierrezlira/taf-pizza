import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TerraPizzaTest {
    WebDriver driver;

    @BeforeEach
    public void warmUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TerraPizzaPage.URL);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void checkOrder_Pizza() {
        //add pizza
        WebElement scroll = driver.findElement(By.xpath(TerraPizzaPage.CATALOG_PIZZA_BTN));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);

        driver.findElement(By.xpath(TerraPizzaPage.CATALOG_PIZZA_BTN)).click();

        WebElement scroll2 = driver.findElement(By.xpath(TerraPizzaPage.SCROLL_DOWN_MARGARITA));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll2);

        waitWebElement(driver, By.xpath(TerraPizzaPage.BUTTON_ADD_MARGARITA)).click();

        //checking  our order in the cart
        waitWebElement( driver, By.xpath(TerraPizzaPage.CHECK_CART)).click();
        WebElement pizzaInf = waitWebElement(driver, By.xpath(TerraPizzaPage.CHECK_IS_PIZZA_IN));
        waitWebElement(driver,By.xpath(TerraPizzaPage.PIZZA_SIZE));

        assertEquals("Пицца Маргарита Классическая 32 см", pizzaInf.getText());
    }

    private static WebElement waitWebElement(WebDriver driver, By by) {
        return new WebDriverWait((WebDriver) driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Test
    public void checkOrder_PizzaDrink() {
        //add pizza
        WebElement scroll = driver.findElement(By.xpath(TerraPizzaPage.CATALOG_PIZZA_BTN));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);

        driver.findElement(By.xpath(TerraPizzaPage.CATALOG_PIZZA_BTN)).click();

        WebElement scroll2 = driver.findElement(By.xpath(TerraPizzaPage.SCROLL_DOWN_MARGARITA));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll2);

        waitWebElement(driver, By.xpath(TerraPizzaPage.BUTTON_ADD_MARGARITA)).click();
        //add Drink
        WebElement barNav = waitWebElement(driver, By.xpath(TerraPizzaPage.MENU_CATALOG));

        JavascriptExecutor scroll_up = (JavascriptExecutor) driver;
        scroll_up.executeScript("window.scrollTo(0, 0);");

        waitWebElement(driver, By.xpath(TerraPizzaPage.CATALOG_BAR_BTN)).click();

        WebElement searchDrink = driver.findElement(By.xpath(TerraPizzaPage.ADD_DRINK_BTN));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchDrink);
        searchDrink.click();

        //checking order in the cart
        waitWebElement(driver, By.xpath(TerraPizzaPage.CHECK_CART)).click();

        WebElement pizzaInf = waitWebElement(driver, By.xpath(TerraPizzaPage.CHECK_IS_PIZZA_IN));

        WebElement pizzaSize =  waitWebElement(driver, By.xpath(TerraPizzaPage.PIZZA_SIZE));
        assertEquals("Пицца Маргарита Классическая 32 см", pizzaInf.getText());

        WebElement cart3 =  waitWebElement(driver, By.xpath(TerraPizzaPage.CHECK_DRINK));
        String drinkName = cart3.getText();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertEquals("Вода питьевая \"Аква Минерале\" негаз. (Россия)", drinkName);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

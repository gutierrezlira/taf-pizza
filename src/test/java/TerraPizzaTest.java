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
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        WebElement addMargarita = driver.findElement(By.xpath(TerraPizzaPage.BUTTON_ADD_MARGARITA));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(addMargarita));
        addMargarita.click();

        //checking  our order in the cart
        WebElement cart = driver.findElement(By.xpath(TerraPizzaPage.CHECK_CART));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(cart));
        cart.click();

/*        WebElement fullCartWindow = driver.findElement(By.xpath(TerraPizzaPage.CART_CONTENT));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(fullCartWindow));
        boolean isDisplayed = fullCartWindow.isDisplayed();
        assertTrue(isDisplayed,"yes" );*/ //

        WebElement pizzaInf = driver.findElement(By.xpath(TerraPizzaPage.CHECK_IS_PIZZA_IN));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(pizzaInf));
        WebElement pizzaSize = driver.findElement(By.xpath(TerraPizzaPage.PIZZA_SIZE));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(pizzaSize));

        assertEquals("Пицца Маргарита Классическая 32 см", pizzaInf.getText());
    }

    @Test
    public void checkOrder_PizzaDrink() {
        //add pizza
        WebElement scroll = driver.findElement(By.xpath(TerraPizzaPage.CATALOG_PIZZA_BTN));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);

        driver.findElement(By.xpath(TerraPizzaPage.CATALOG_PIZZA_BTN)).click();

        WebElement scroll2 = driver.findElement(By.xpath(TerraPizzaPage.SCROLL_DOWN_MARGARITA));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll2);

        WebElement addMargarita = driver.findElement(By.xpath(TerraPizzaPage.BUTTON_ADD_MARGARITA));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(addMargarita));
        addMargarita.click();

        //add Drink
        WebElement barNav = driver.findElement(By.xpath(TerraPizzaPage.MENU_CATALOG));
        //new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(barNav));
        JavascriptExecutor scroll_up = (JavascriptExecutor) driver;
        scroll_up.executeScript("window.scrollTo(0, 0);");

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(barNav));
        WebElement barButton = driver.findElement(By.xpath(TerraPizzaPage.CATALOG_BAR_BTN));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        barButton.click();

        WebElement searchDrink = driver.findElement(By.xpath(TerraPizzaPage.ADD_DRINK_BTN));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchDrink);
        searchDrink.click();

        //checking order in the cart
        WebElement cart = driver.findElement(By.xpath(TerraPizzaPage.CHECK_CART));
        new WebDriverWait(driver, Duration.ofSeconds(7)).until(ExpectedConditions.visibilityOf(cart));
        cart.click();

/*        WebElement fullCartWindow = driver.findElement(By.xpath(TerraPizzaPage.CART_CONTENT));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(fullCartWindow));
        boolean isDisplayed = fullCartWindow.isDisplayed();
        assertTrue(isDisplayed,"yes" );*/

        WebElement pizzaInf = driver.findElement(By.xpath(TerraPizzaPage.CHECK_IS_PIZZA_IN));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(pizzaInf));
        WebElement pizzaSize = driver.findElement(By.xpath(TerraPizzaPage.PIZZA_SIZE));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(pizzaSize));
        assertEquals("Пицца Маргарита Классическая 32 см", pizzaInf.getText());

        WebElement cart3 = driver.findElement(By.xpath(TerraPizzaPage.CHECK_DRINK));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(cart3));
        String drinkName = cart3.getText();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertEquals("Вода питьевая \"Аква Минерале\" негаз. (Россия)", drinkName);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

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
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TerraPizzaPage.URL);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void checkOrder() {
        WebElement scroll = driver.findElement(By.xpath(TerraPizzaPage.SCROLL_CATALOG));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",scroll);

        driver.findElement(By.xpath(TerraPizzaPage.CATALOG_PIZZA)).click();

        WebElement scroll2 = driver.findElement(By.xpath(TerraPizzaPage.SCROLL_DOWN_MARGARITA));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",scroll2);

        WebElement addMargarita = driver.findElement(By.xpath(TerraPizzaPage.BUTTON_ADD_MARGARITA));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(addMargarita));
        addMargarita.click();

        WebElement cart = driver.findElement(By.xpath(TerraPizzaPage.CHECK_CART));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(cart));
        cart.click();

        WebElement fullCartWindow = driver.findElement(By.xpath(TerraPizzaPage.CART_CONTENT));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(fullCartWindow));
        boolean isDisplayed = fullCartWindow.isDisplayed();
        assertTrue(isDisplayed,"yes" );

        WebElement cart2 = driver.findElement(By.xpath(TerraPizzaPage.CHECK_IS_PIZZA_IN));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(cart2));
        String pizzaName = cart2.getText();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertEquals("Пицца Маргарита Классическая", pizzaName);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}

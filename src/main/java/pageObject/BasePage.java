package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static config.Constants.DEFAULT_TIMEOUT;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание видимости элемента: {locator}")
    public void waitForElement(By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new AssertionError("Элемент не найден: " + locator, e);
        }
    }

    @Step("Проверка видимости элемента: {locator}")
    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    @Step("Ожидание URL: {expectedUrl}")
    public void waitForUrl(String expectedUrl) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (TimeoutException e) {
            throw new AssertionError("URL не соответствует ожидаемому: " + expectedUrl, e);
        }
    }

    @Step("Клик по элементу: {locator}")
    public void clickElement(By locator) {
        waitForElement(locator);
        driver.findElement(locator).click();
    }
}

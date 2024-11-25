package pageObject;

import config.Constants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage extends BasePage {

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    private final By loginLink = By.xpath(".//a[text()='Войти']");
    private final By recoverPasswordButton = By.xpath(".//button[text()= 'Восстановить']");

    public void waitForElement(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Ожидание загрузки страницы восстановления пароля")
    public void waitForgotPasswordPage() {
        waitForElement(recoverPasswordButton);
    }

    @Step("Клик по ссылке Войти")
    public LoginPage clickLoginLink() {
        waitForgotPasswordPage();
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }
}
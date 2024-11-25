package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.User;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By userEmailField = By.xpath(".//div/label[text()='Email']/parent::div/input");
    private final By userPasswordField = By.xpath(".//div/label[text()='Пароль']/parent::div/input");
    private final By enterButton = By.xpath(".//button[text()='Войти']");

    @Step("Ожидаем загрузку страницы авторизации")
    public void waitLoginPage() {
        waitForElement(enterButton);
    }

    @Step("Проверка, что страница логина отображается")
    public boolean isLoginPage() {
        waitLoginPage(); // Убедиться, что страница логина загружена
        return driver.findElement(userEmailField).isDisplayed() &&
                driver.findElement(userPasswordField).isDisplayed() &&
                driver.findElement(enterButton).isDisplayed();
    }

    @Step("Заполняем поле Email")
    public void setUserEmail(String email) {
        WebElement emailField = driver.findElement(userEmailField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    @Step("Заполняем поле Пароль")
    public void setUserPassword(String password) {
        WebElement passwordField = driver.findElement(userPasswordField);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @Step("Кликаем на кнопку Войти")
    public void clickEnterButton() {
        clickElement(enterButton); // Выполняем клик
    }

    @Step("Вход через форму логина")
    public void loginFromLoginPage(User user) {
        setUserEmail(user.getEmail());
        setUserPassword(user.getPassword());
        clickEnterButton();

        if (!isElementDisplayed(By.xpath("//button[text()='Оформить заказ']"))) {
            throw new AssertionError("Пользователь не был успешно авторизован.");
        }
    }

    @Step("Вход через форму логина и переход на страницу профиля")
    public ProfilePage loginAndGoToProfile(User user) {
        setUserEmail(user.getEmail());
        setUserPassword(user.getPassword());
        clickEnterButton();

        return new ProfilePage(driver);
    }

}

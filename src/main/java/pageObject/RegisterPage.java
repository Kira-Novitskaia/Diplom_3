package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.User;

public class RegisterPage extends BasePage {

    // Локаторы
    private final By userNameField = By.xpath(".//div/label[text()='Имя']/parent::div/input");
    private final By userEmailField = By.xpath(".//div/label[text()='Email']/parent::div/input");
    private final By userPasswordField = By.xpath(".//div/label[text()='Пароль']/parent::div/input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By incorrectPasswordMessage = By.xpath("//p[text()='Некорректный пароль']");
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы регистрации")
    public void open() {
        driver.get(config.Constants.REGISTER_PAGE_URL);
    }

    @Step("Ожидание загрузки страницы регистрации")
    public void waitRegisterPage() {
        waitForElement(registerButton);
    }

    @Step("Заполнение формы регистрации")
    public void fillRegistrationForm(User user) {
        waitRegisterPage();
        setUserName(user.getName());
        setUserEmail(user.getEmail());
        setUserPassword(user.getPassword());
    }

    @Step("Заполняем поле Имя")
    public void setUserName(String userName) {
        WebElement nameField = driver.findElement(userNameField);
        nameField.clear();
        nameField.sendKeys(userName);
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

    @Step("Нажимаем кнопку Зарегистрироваться")
    public void clickRegisterButton() {
        WebElement button = driver.findElement(registerButton);
        button.click();
    }

    @Step("Получение сообщения об ошибке пароля")
    public String getIncorrectPasswordMessageText() {
        return driver.findElement(incorrectPasswordMessage).getText();
    }

    @Step("Выполнение регистрации пользователя")
    public void performRegistration(User user) {
        fillRegistrationForm(user);
        clickRegisterButton();
    }

    @Step("Клик по ссылке Войти")
    public LoginPage clickLoginLink() {
        WebElement link = driver.findElement(loginLink);
        link.click();
        return new LoginPage(driver);
    }
}

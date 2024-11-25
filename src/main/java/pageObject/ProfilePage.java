package pageObject;

import config.Constants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver); // Вызов конструктора BasePage
    }

    // Локаторы
    private final By emailField = By.xpath("//input[contains(@name, 'name') and contains(@type, 'text')]");
    private final By saveButton = By.xpath("//button[text()='Сохранить']");
    private final By exitButton = By.xpath("//button[text()='Выход']");
    private final By logoLink = By.className("AppHeader_header__logo__2D0X2");

    @Step("Ожидание загрузки страницы профиля")
    public void waitProfilePage() {
        waitForElement(saveButton);
    }

    @Step("Получение содержимого из поля Email")
    public String getEmailFromField() {
        waitProfilePage();
        return driver.findElement(emailField).getAttribute("value");
    }

    @Step("Клик по логотипу сайта")
    public MainPage clickLogoLink() {
        clickElement(logoLink);
        return new MainPage(driver);
    }

    @Step("Переход в конструктор через профиль")
    public MainPage goToConstructorFromProfile() {
        By constructorButton = By.xpath("//p[contains(text(),'Конструктор')]");
        clickElement(constructorButton);
        waitForUrl(Constants.MAIN_PAGE_URL);
        return new MainPage(driver);
    }

    @Step("Клик по кнопке Выход")
    public LoginPage clickExitButton() {
        clickElement(exitButton);
        return new LoginPage(driver);
    }
}

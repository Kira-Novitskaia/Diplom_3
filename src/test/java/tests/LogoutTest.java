package tests;

import config.Constants;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.ProfilePage;
import static org.junit.Assert.assertTrue;

public class LogoutTest extends BaseTest {

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете.")
    public void logOutTestSuccess() {
        // Открываем страницу логина
        driver.get(Constants.LOGIN_PAGE_URL);
        LoginPage loginPage = new LoginPage(driver);

        // Логинимся через LoginPage
        loginPage.loginFromLoginPage(user);

        // Проверяем, что попали на главную страницу
        MainPage mainPage = new MainPage(driver);
        assertTrue("Не удалось попасть на главную страницу после логина", mainPage.isMainPageDisplayed());

        // Переходим в Личный кабинет
        ProfilePage profilePage = mainPage.goToProfilePage(); // Нажимаем на «Личный кабинет»

        // Проверяем загрузку страницы профиля
        profilePage.waitProfilePage();

        // Нажимаем "Выход"
        loginPage = profilePage.clickExitButton(); // Клик по кнопке выхода

        // Проверяем, что мы на странице логина
        loginPage.waitLoginPage();
        assertTrue("Выход из личного кабинета не выполнен", loginPage.isLoginPage());
    }

}
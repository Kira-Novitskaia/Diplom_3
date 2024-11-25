package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageObject.*;
import static config.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginThroughAccountButtonSuccess() {
        // Открываем главную страницу
        driver.get(MAIN_PAGE_URL);

        // Переход на страницу логина через кнопку «Личный кабинет»
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();

        // Проверяем, что текущий URL - страница логина
        String currentUrl = driver.getCurrentUrl();
        assertEquals("Ожидался переход на страницу логина", LOGIN_PAGE_URL, currentUrl);

        // Выполнение входа
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFromLoginPage(user);

        // Ожидаем, что после входа URL будет MAIN_PAGE_URL
        mainPage.waitForUrl(MAIN_PAGE_URL);

        // Проверяем, что текущий URL соответствует главной странице
        currentUrl = driver.getCurrentUrl();
        assertEquals("После входа пользователь не был перенаправлен на главную страницу",
                MAIN_PAGE_URL, currentUrl);

        // Проверяем, что кнопка «Оформить заказ» отображается
        assertTrue("Кнопка 'Оформить заказ' не отображается после входа",
                mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void registerAccountButtonSuccess() {
        // Открываем главную страницу
        driver.get(MAIN_PAGE_URL);
        MainPage mainPage = new MainPage(driver);

        // Переход на страницу Личного кабинета
        LoginPage loginPage = mainPage.clickAccountButton();

        // Выполнение входа
        loginPage.loginFromLoginPage(user);

        // Проверяем, что текущий URL соответствует MAIN_PAGE_URL
        String currentUrl = driver.getCurrentUrl();
        assertEquals("После входа пользователь не был перенаправлен на главную страницу",
                MAIN_PAGE_URL, currentUrl);

        // Проверяем, что кнопка 'Оформить заказ' отображается
        assertTrue("Кнопка 'Оформить заказ' не отображается после входа",
                mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void registerRegisterPageSuccess() {
        // Открываем страницу регистрации
        driver.get(REGISTER_PAGE_URL);
        RegisterPage registerPage = new RegisterPage(driver);

        // Переход на страницу логина
        LoginPage loginPage = registerPage.clickLoginLink();

        // Выполнение входа
        loginPage.loginFromLoginPage(user);

        // Ожидаем, что после входа URL будет MAIN_PAGE_URL
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForUrl(MAIN_PAGE_URL);

        // Проверяем, что текущий URL соответствует MAIN_PAGE_URL
        String currentUrl = driver.getCurrentUrl();
        assertEquals("После входа пользователь не был перенаправлен на главную страницу",
                MAIN_PAGE_URL, currentUrl);

        // Проверяем, что кнопка 'Оформить заказ' отображается
        assertTrue("Кнопка 'Оформить заказ' не отображается после входа", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void registerRecoverPasswordPageSuccess() {
        // Открываем страницу восстановления пароля
        driver.get(FORGOT_PASSWORD_PAGE_URL);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        // Переход на страницу логина
        LoginPage loginPage = forgotPasswordPage.clickLoginLink();

        // Выполнение входа
        loginPage.loginFromLoginPage(user);

        // Убедитесь, что после авторизации мы перенаправлены на главную страницу
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForUrl(MAIN_PAGE_URL);

        // Проверяем, что кнопка 'Оформить заказ' отображается
        assertTrue("Кнопка 'Оформить заказ' не отображается после входа",
                mainPage.isOrderButtonVisible());
    }

}

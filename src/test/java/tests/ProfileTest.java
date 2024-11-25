package tests;

import config.Constants;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.ProfilePage;
import static config.Constants.MAIN_PAGE_URL;
import static config.Constants.PROFILE_PAGE_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfileTest extends BaseTest {

    @Test
    @DisplayName("Переход в Профиль")
    public void clickAccountLinkSuccess() {
        // Открываем главную страницу
        driver.get(MAIN_PAGE_URL);

        // Переход на страницу логина через кнопку «Личный кабинет»
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickAccountButton();

        // Выполнение входа
        loginPage.loginFromLoginPage(user);

        // Еще раз нажимаем на «Личный кабинет» для перехода в профиль
        ProfilePage profilePage = mainPage.goToProfilePage();

        // Проверка перехода на страницу профиля
        assertEquals("Не произошел переход на страницу профиля",
                PROFILE_PAGE_URL,
                driver.getCurrentUrl());

        // Проверка отображения email пользователя
        assertEquals("Переход в личный кабинет не выполнен",
                user.getEmail(),
                profilePage.getEmailFromField());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void registerAccountButtonSuccess() {
        // Открываем главную страницу
        driver.get(MAIN_PAGE_URL);

        // Переход на страницу логина через кнопку «Личный кабинет»
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickAccountButton();

        // Выполнение входа
        loginPage.loginFromLoginPage(user);

        // Еще раз нажимаем на «Личный кабинет» для перехода в профиль
        ProfilePage profilePage = mainPage.goToProfilePage();

        // Переход из профиля в конструктор
        MainPage constructorMainPage = profilePage.goToConstructorFromProfile();
        assertEquals("Не произошел переход на страницу Конструктора", Constants.MAIN_PAGE_URL, driver.getCurrentUrl());

        // Проверка наличия заголовка конструктора
        assertTrue("Заголовок конструктора не отображается", constructorMainPage.waitConstructorHeader());
    }


    @Test
    @DisplayName("Переход из личного кабинета на главную страницу по клику на логотип Stellar Burgers")
    public void clickLogoGetMainPage() {
        driver.get(MAIN_PAGE_URL);
        MainPage mainPage = new MainPage(driver);
        ProfilePage profilePage = mainPage.clickAccountButton()
                .loginAndGoToProfile(user);
        MainPage logoMainPage = profilePage.clickLogoLink();
        assertEquals("Не произошел переход на страницу Конструктора",
                MAIN_PAGE_URL,
                driver.getCurrentUrl());
        assertTrue(logoMainPage.waitConstructorHeader());
    }

}

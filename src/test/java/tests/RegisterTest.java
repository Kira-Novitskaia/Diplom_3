package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pageObject.RegisterPage;
import utils.User;
import utils.UserGenerator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RegisterTest extends BaseTest {
    private RegisterPage registerPage;

    @Before
    @Step("Подготовка тестовых данных и открытие страницы регистрации")
    public void init() {
        // Генерация уникального пользователя для каждого теста
        user = UserGenerator.getRandomUser();
        registerPage = new RegisterPage(driver);
        registerPage.open();

        // Добавление данных пользователя в Allure отчет
        Allure.addAttachment("Имя", user.getName());
        Allure.addAttachment("Email", user.getEmail());
        Allure.addAttachment("Пароль", user.getPassword());
    }

    @Test
    @DisplayName("Тест успешной регистрации пользователя")
    public void registerNewUserSuccess() {
        registerPage.performRegistration(user); // Регистрация

        // Проверяем возможность логина через API
        userToken = userClient.login(user).jsonPath().getString("accessToken").split(" ")[1];
        assertNotNull("Токен пользователя не получен, логин не удался", userToken);
    }

    @Test
    @DisplayName("Негативная регистрация с коротким паролем")
    public void registerNewUserWithShortPasswordFail() {
        String email = "shortPasswordUser" + System.currentTimeMillis() + "@example.com";
        String password = "123";
        String name = "ShortUser";

        User userWithShortPassword = User.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();

        registerPage.fillRegistrationForm(userWithShortPassword);
        registerPage.clickRegisterButton();

        // Ожидание с использованием метода getIncorrectPasswordMessageText
        assertEquals("Некорректный пароль", registerPage.getIncorrectPasswordMessageText());
    }
}

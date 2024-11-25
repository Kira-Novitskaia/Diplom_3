package tests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import utils.User;
import utils.UserClient;
import utils.UserGenerator;

import static driver.WebDriverCreator.createWebDriver;

public abstract class BaseTest {
    protected WebDriver driver;
    protected User user;
    protected UserClient userClient = new UserClient();
    protected String userToken;

    @Before
    public void setup() {
        driver = createWebDriver();
        setupUser();
    }

    @Step("Подготовка тестового пользователя через API")
    public void setupUser() {
        // Используем метод createRandomUserThroughAPI из UserGenerator
        user = UserGenerator.createRandomUserThroughAPI(userClient);
        // Логиним пользователя и сохраняем токен
        Response loginResponse = userClient.login(user);
        if (loginResponse.statusCode() != HttpStatus.SC_OK) {
            throw new AssertionError("Не удалось войти через API. Статус: " + loginResponse.statusCode());
        }
        userToken = loginResponse.jsonPath().getString("accessToken").split(" ")[1];
    }

    @After
    public void tearDown() {
        if (userToken != null) {
            userClient.deleteUser(userToken);
            userToken = null;
        }
        if (driver != null) {
            driver.quit();
        }
    }
}

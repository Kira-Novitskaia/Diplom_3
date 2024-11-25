package utils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private static final String CREATE_URL = "/api/auth/register";
    private static final String LOGIN_URL = "/api/auth/login";
    private static final String USER_URL = "/api/auth/user";

    public UserClient() {
        RestAssured.baseURI = BASE_URI;
    }

    private RequestSpecification getRequestSpec(String token) {
        RequestSpecification spec = given()
                .header("Content-type", "application/json");
        if (token != null) {
            spec.auth().oauth2(token);
        }
        return spec;
    }

    @Step("Создание нового пользователя")
    public Response create(User user) {
        Response response = getRequestSpec(null).body(user).post(CREATE_URL);
        System.out.println("Создание пользователя: " + response.body().asString());
        return response;
    }

    @Step("Вход под пользователем")
    public Response login(User user) {
        Response response = getRequestSpec(null).body(user).post(LOGIN_URL);
        System.out.println("Логин пользователя: " + response.body().asString());
        return response;
    }

    @Step("Удаление пользователя через API")
    public Response deleteUser(String token) {
        Response response = getRequestSpec(token).delete(USER_URL);
        System.out.println("Удаление пользователя: " + response.body().asString());
        return response;
    }
}

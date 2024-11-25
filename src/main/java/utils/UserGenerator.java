package utils;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.Random;

public class UserGenerator {
    private static final String EMAIL_DOMAIN = "@example.com";
    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 16;
    private static final Random RANDOM = new Random();

    // Генерация пользователя с рандомными валидными данными и регистрация через API
    public static User createRandomUserThroughAPI(UserClient userClient) {
        User user = generateRandomUser();
        Response response = userClient.create(user);

        if (response.statusCode() != HttpStatus.SC_OK) {
            System.out.println("Ошибка создания пользователя: " + response.body().asString());
            throw new AssertionError("Не удалось создать пользователя через API. Статус: " + response.statusCode());
        }

        return user;
    }

    public static User getRandomUser() {
        return generateRandomUser();
    }


    // Генерация пользователя с уникальными email, паролем и именем
    private static User generateRandomUser() {
        String email = "user" + System.currentTimeMillis() + RANDOM.nextInt(1000) + EMAIL_DOMAIN;
        String password = generateRandomPassword();
        String name = "User" + RANDOM.nextInt(10000);

        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }

    // Генерация случайного пароля заданной длины
    private static String generateRandomPassword() {
        int length = RANDOM.nextInt(PASSWORD_MAX_LENGTH - PASSWORD_MIN_LENGTH + 1) + PASSWORD_MIN_LENGTH;
        String password = RANDOM.ints(length, 33, 122)
                .filter(i -> Character.isLetterOrDigit(i) || "!@#$%^&*()".indexOf((char) i) >= 0)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        // Убедимся, что пароль содержит буквы, цифры и спецсимволы
        if (!password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*()].*")) {
            return generateRandomPassword(); // Рекурсивно сгенерируем новый
        }
        return password;
    }
}

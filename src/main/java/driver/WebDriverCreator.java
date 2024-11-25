package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverCreator {

    public static WebDriver createWebDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        switch (browser) {
            case "chrome":
                return createChromeDriver();
            case "yandex":
                return createYandexDriver();
            default:
                throw new IllegalArgumentException("Браузер -> " + browser + " не поддерживается");
        }
    }

    private static WebDriver createChromeDriver() {
        // Настраиваем ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }


    private static WebDriver createYandexDriver() {
        // Укажите путь к драйверу и самому браузеру
        String yandexDriverPath = "D:\\Tester\\yandexdriver-24.10.1.598-win64\\yandexdriver.exe";
        System.setProperty("webdriver.chrome.driver", yandexDriverPath);

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\kiran\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }
}

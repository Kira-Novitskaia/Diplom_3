package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageObject.MainPage;


import static org.junit.Assert.assertTrue;

public class MainPageTest extends BaseTest {

    @Test
    @DisplayName("Переход в раздел 'Булки'")
    public void testBunsSectionIsDisplayedByDefault() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        // Проверка, что заголовок "Булки" отображается по умолчанию
        assertTrue("Заголовок 'Булки' не отображается по умолчанию", mainPage.isBunsHeaderDisplayed());
    }

    @Test
    @DisplayName("Переход в раздел 'Соусы'")
    public void testNavigateToSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        // Проверяем переход в раздел "Соусы"
        mainPage.clickSaucesSection();
        assertTrue("Заголовок 'Соусы' не отображается", mainPage.isSaucesHeaderDisplayed());
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки'")
    public void testNavigateToFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        // Проверяем переход в раздел "Начинки"
        mainPage.clickFillingsSection();
        assertTrue("Заголовок 'Начинки' не отображается", mainPage.isFillingsHeaderDisplayed());
    }
}

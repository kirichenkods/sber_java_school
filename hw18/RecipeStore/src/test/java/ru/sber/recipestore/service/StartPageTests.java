package ru.sber.recipestore.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations = "classpath:test-application.properties")
class StartPageTests {
    private WebDriver driver;
    @Value("${webdriver.chromedriver.name}")
    String driverName;
    @Value("${webdriver.chromedriver.path}")
    String driverPath;
    String url;
    @Value("${server.host}")
    String host;
    @Value("${server.port}")
    int port;
    @BeforeEach
    void setup() {
        System.setProperty(driverName, driverPath);
        driver = new ChromeDriver();
        url = String.format("http://%s:%d", host, port);
        driver.get(url);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void givenHomePage_whenOpened_thenTitleShouldBeCorrect() {
        String title = "Сборник рецептов";
        assertEquals(title, driver.getTitle());
    }

    @Test
    public void givenHomePage_whenOpened_thenElementRecipeNameExists() {
        assertTrue(driver.findElement(By.name("recipeName")).isDisplayed());
    }

    @Test
    public void givenHomePage_whenOpened_thenPageContainsTwoFormTags() {
        int tagFormCount = 2;
        assertEquals(driver.findElements(By.ByTagName.tagName("form")).size(), tagFormCount);
    }
}

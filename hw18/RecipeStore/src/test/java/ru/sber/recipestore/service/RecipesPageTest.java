package ru.sber.recipestore.service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@TestPropertySource(locations = "classpath:test-application.properties")
public class RecipesPageTest {
    @Value("${server.host}")
    String host;
    @Value("${server.port}")
    int port;
    String existRecipeName;
    String notExistRecipeName;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://" + host;
        existRecipeName = "салат винегрет";
        notExistRecipeName = "рецепт которого нет";
    }

    @Test
    public void whenGetExistRecipe_thenOK() {
        given()
                .queryParam("recipeName", existRecipeName)
                .when()
                .get("/find")
                .then()
                .statusCode(200);
    }

    @Test
    public void whenGetExistRecipe_BodyNotEmpty() {
        given()
                .queryParam("recipeName", existRecipeName)
                .when()
                .get("/find")
                .then()
                .body(not(empty()));
    }

    @Test
    public void whenGetNotExistRecipe_thenCorrectResult() {
        given()
                .queryParam("recipeName", notExistRecipeName)
                .when()
                .get("/find")
                .then()
                .statusCode(200)
                .and().body(containsString("<h3>Не найдено рецептов с таким названием</h3>"));
    }

    @Test
    public void whenGetExistRecipe_thenCorrectHeader() {
        given()
                .queryParam("recipeName", existRecipeName)
                .when()
                .get("/find")
                .then()
                .statusCode(200).and().body(containsString("<h1>Рецепты</h1>"));
    }

    @Test
    public void whenGetRecipe_thenContentTypeHTML() {
        given().queryParam("recipeName", existRecipeName)
                .when()
                .get("/find")
                .then()
                .contentType(ContentType.HTML);
    }

    @Test
    public void whenGetExistRecipe_thenBodyContainsAllButtons() {
        given()
                .queryParam("recipeName", existRecipeName)
                .when()
                .get("/find")
                .then()
                .statusCode(200)
                .and()
                .body(containsString("<form action=\"/start"))
                .and()
                .body(containsString("<input type=\"submit\" value=\"На главную\"/>"))
                .and()
                .body(containsString("<form action=\"/edit/"))
                .and()
                .body(containsString("<button type=\"submit\">Редактировать рецепт</button>"))
                .and()
                .body(containsString("<form action=\"/delete/салат винегрет\" method=\"get\">"))
                .and()
                .body(containsString("<button type=\"submit\">Удалить рецепт</button>"));
    }
}

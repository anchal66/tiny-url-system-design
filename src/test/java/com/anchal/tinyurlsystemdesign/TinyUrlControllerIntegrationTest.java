package com.anchal.tinyurlsystemdesign;

import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TinyUrlControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private static String originalUrl = "https://takeuforward.org/interviews/strivers-sde-sheet-top-coding-interview-problems/";
    private static String shortenedUrl;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    @Order(1)
    public void testHashUrl() {
        UrlReqest urlReqest = new UrlReqest(originalUrl);

        MockMvcResponse response = given()
                .contentType("application/json")
                .body(urlReqest)
                .when()
                .post("/api")
                .then()
                .statusCode(200)
                .extract()
                .response();

        shortenedUrl = response.asString();
    }

    @Test
    @Order(2)
    public void testGetUrl() {
        given()
                .when()
                .get("/api/{url}", shortenedUrl)
                .then()
                .statusCode(200)
                .body(equalTo(originalUrl));
    }
}

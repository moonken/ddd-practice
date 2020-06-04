package com.thoughtworks.dddpractice.representation.restful.controllers;

import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.JpaGoodsRepository;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GoodsControllerE2ETest {

  @LocalServerPort
  private int port;

  @Autowired
  JpaGoodsRepository jpaGoodsRepository;

  @AfterEach
  public void teardown() {
    jpaGoodsRepository.deleteAll();
  }

  @Test
  void should_create_goods_successful() throws Exception {
    //given
    String requestBody = "{\n" +
      "\"code\": \"001\",\n" +
      "\"name\": \"apple\",\n" +
      "\"price\": 3.5\n" +
      "}";

    //when
    ValidatableResponse validatableResponse = RestAssured.given()
      .contentType(JSON)
      .body(requestBody)
      .when()
      .post(String.format("http://localhost:%s/goods", port))
      .then();

    //then
    validatableResponse
      .statusCode(is(201))
      .body("code", is("001"))
      .body("price", is(3.5f))
      .body("name", is("apple"));
  }
}

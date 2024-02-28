package com.example.spring_boot_test.chap09;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.spring_boot_test.chap07.AuthDebit.CardNumberValidator;
import com.example.spring_boot_test.chap07.AuthDebit.CardValidity;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardNumberValidatorTest {
  private WireMockServer wireMockServer;

  @BeforeEach
  void setUp() {
    wireMockServer = new WireMockServer(8081);
    wireMockServer.start();
  }

  @AfterEach
  void tearDown() {
    wireMockServer.stop();
  }

  @Test
  void validCardNumber() {
    wireMockServer.stubFor(
        post(urlEqualTo("/card"))
            .withRequestBody(equalToJson("1234567890"))
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("ok")));

    CardNumberValidator validator = new CardNumberValidator("http://localhost:8081");
    CardValidity validity = validator.validate("1234567890");

    assertEquals(CardValidity.VALID, validity);
  }

  @Test
  void timeout() {
    wireMockServer.stubFor(post(urlEqualTo("/card")).willReturn(aResponse().withFixedDelay(5000)));

    CardNumberValidator validator = new CardNumberValidator("http://localhost:8081");
    CardValidity validity = validator.validate("1234567890");

    assertEquals(CardValidity.TIMEOUT, validity);
  }
}

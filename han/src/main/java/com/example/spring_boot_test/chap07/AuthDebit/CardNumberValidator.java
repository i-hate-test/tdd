package com.example.spring_boot_test.chap07.AuthDebit;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

public class CardNumberValidator {
  private String serverUrl;

  public CardNumberValidator(String serverUrl) {
    this.serverUrl = serverUrl;
  }

  public CardValidity validate(String cardNumber) {
    HttpClient httpClient = HttpClient.newHttpClient();
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(serverUrl + "/card"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(cardNumber))
            .timeout(Duration.ofSeconds(3))
            .build();

    try {
      HttpResponse<String> response =
          httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      switch (response.body()) {
        case "ok":
          return CardValidity.VALID;
        case "bad":
          return CardValidity.INVALID;
        case "expired":
          return CardValidity.EXPIRED;
        case "theft":
          return CardValidity.THEFT;
        default:
          return CardValidity.UNKNOWN;
      }
    } catch (HttpTimeoutException e) {
      return CardValidity.TIMEOUT;
    } catch (IOException | InterruptedException e) {
      return CardValidity.ERROR;
    }
  }
}

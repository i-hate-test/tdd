package com.example.spring_boot_test.chap09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiE2ETest {
  @Autowired private TestRestTemplate restTemplate;

  @Test
  void weakPwResponse() {
    String reqBody = "{\"id\":\"id\",\"password\":\"123\",\"email\":\"email@email.com\"}";
    RequestEntity<String> req =
        RequestEntity.post(URI.create("/users"))
            .contentType(MediaType.APPLICATION_JSON)
            .body(reqBody);

    ResponseEntity<String> res = restTemplate.exchange(req, String.class);

    assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    assertEquals("WeakPasswordException", res.getBody());
  }
}

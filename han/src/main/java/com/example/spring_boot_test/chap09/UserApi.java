package com.example.spring_boot_test.chap09;

import com.example.spring_boot_test.chap07.UserRegister.UserRegister;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {
  @Autowired private UserRegister userRegister;

  @PostMapping("/users")
  public ResponseEntity<Object> register(@RequestBody CreateUserRequest req) {
    try {
      userRegister.register(req.getId(), req.getPassword(), req.getEmail());
      return ResponseEntity.created(URI.create("/users/" + req.getId())).build();
    } catch (Exception ex) {
      return ResponseEntity.badRequest().body(ex.getClass().getSimpleName());
    }
  }
}

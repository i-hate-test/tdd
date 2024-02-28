package com.example.spring_boot_test.chap07.UserRegister;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "users")
public class User {
  @Id private String id;
  private String password;
  private String email;

  protected User() {}

  public User(String id, String password, String email) {
    this.id = id;
    this.password = password;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }
}

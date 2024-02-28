package com.example.spring_boot_test.chap08.LoginService;

public class LoginService {
  private AuthService authService;

  public LoginResult login(String id, String password) {
    boolean auth = authService.authenticate(id, password);
    System.out.println("auth = " + auth);
    if (!authService.authenticate(id, password)) {
      return LoginResult.INVALID_ID;
    }
    return null;
  }

  public void setAuthService(AuthService authService) {
    this.authService = authService;
  }
}

package com.example.spring_boot_test.chap09;

import com.example.spring_boot_test.chap07.UserRegister.EmailNotifier;
import com.example.spring_boot_test.chap07.UserRegister.WeakPasswordChecker;
import org.springframework.stereotype.Component;

@Component
public class SimpleEmailNotifier implements EmailNotifier {
  private String email;

  public String getEmail() {
    return email;
  }

  @Override
  public void sendRegisterEmail(String email) {
    this.email = email;
  }

  @Component
  public static class SimpleWeakPasswordChecker implements WeakPasswordChecker {
    @Override
    public boolean checkPasswordWeak(String pw) {
      return pw == null || pw.length() < 5;
    }
  }
}

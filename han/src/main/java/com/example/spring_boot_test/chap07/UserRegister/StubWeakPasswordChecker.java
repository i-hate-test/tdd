package com.example.spring_boot_test.chap07.UserRegister;

public class StubWeakPasswordChecker implements WeakPasswordChecker {
  private boolean weak;

  public void setWeak(boolean weak) {
    this.weak = weak;
  }

  @Override
  public boolean checkPasswordWeak(String pw) {
    return weak;
  }
}

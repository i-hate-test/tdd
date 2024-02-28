package com.example.spring_boot_test.chap07.UserRegister;

public class WeakPasswordException extends RuntimeException {
  private boolean weak;

  public void setWeak(boolean weak) {
    this.weak = weak;
  }
}

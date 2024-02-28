package com.example.spring_boot_test.chap07.AuthDebit;

public class RegisterResult {
  private boolean isSuccess;
  private CardValidity validity;

  public RegisterResult(CardValidity validity) {
    this.validity = validity;
    if (validity == CardValidity.VALID) {
      this.isSuccess = true;
    } else {
      this.isSuccess = false;
    }
  }

  public static RegisterResult error(CardValidity validity) {
    return new RegisterResult(validity);
  }

  public static RegisterResult success() {
    return new RegisterResult(CardValidity.VALID);
  }

  public boolean isSuccess() {
    return isSuccess;
  }

  public CardValidity getValidity() {
    return validity;
  }
}

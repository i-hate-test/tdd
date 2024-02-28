package com.example.spring_boot_test.chap07.AuthDebit;

public class StubCardNumberValidator extends CardNumberValidator {
  private String invalidNo;

  public StubCardNumberValidator() {
    super("https://some-card-info-api");
  }

  public void setInvalidNo(String invalidNo) {
    this.invalidNo = invalidNo;
  }

  @Override
  public CardValidity validate(String cardNumber) {
    if (invalidNo != null && invalidNo.equals(cardNumber)) {
      return CardValidity.INVALID;
    }
    return CardValidity.VALID;
  }
}

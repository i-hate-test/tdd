package com.example.spring_boot_test.chap10.way2;

import java.time.LocalDateTime;

public class Member2 {
  private LocalDateTime expiryDate;

  public void setExpiryDate(LocalDateTime expiryDate) {
    this.expiryDate = expiryDate;
  }

  public boolean isExpired() {
    return expiryDate.isBefore(Today2.getDateTime());
  }
}

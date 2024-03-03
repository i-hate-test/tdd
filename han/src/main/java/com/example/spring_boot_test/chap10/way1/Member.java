package com.example.spring_boot_test.chap10.way1;

import java.time.LocalDateTime;

public class Member {
  private LocalDateTime expiryDate;

  public void setExpiryDate(LocalDateTime expiryDate) {
    this.expiryDate = expiryDate;
  }

  public boolean isExpired() {
    return expiryDate.isBefore(Today.getDateTime());
  }
}

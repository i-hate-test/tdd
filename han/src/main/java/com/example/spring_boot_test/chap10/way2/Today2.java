package com.example.spring_boot_test.chap10.way2;

import java.time.LocalDateTime;

public class Today2 {
  private static final Today2 _default = new Today2();
  private static Today2 _instance = _default;

  public static void reset() {
    _instance = _default;
  }

  protected void setInstance(Today2 instance) {
    _instance = instance;
  }

  public static LocalDateTime getDateTime() {
    return _instance._getDateTime();
  }

  public LocalDateTime _getDateTime() {
    return LocalDateTime.now();
  }
}

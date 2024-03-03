package com.example.spring_boot_test.chap10;

import com.example.spring_boot_test.chap10.way2.Today2;
import java.time.LocalDateTime;

public class TestToday extends Today2 {
  private LocalDateTime _dateTime;

  public TestToday() {
    setInstance(this);
  }

  public void setToday(LocalDateTime dateTime) {
    this._dateTime = dateTime;
  }

  @Override
  public LocalDateTime _getDateTime() {
    return this._dateTime != null ? this._dateTime : super._getDateTime();
  }
}

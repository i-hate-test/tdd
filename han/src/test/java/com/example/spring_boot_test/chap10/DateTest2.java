package com.example.spring_boot_test.chap10;

import static org.junit.jupiter.api.Assertions.assertFalse;

import com.example.spring_boot_test.chap10.way2.Member2;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class DateTest2 {
  TestToday testClock = new TestToday();

  @AfterEach
  void resetClock() {
    testClock.reset();
  }

  @Test
  void memberIsNotExpired() {
    testClock.setToday(LocalDateTime.of(2023, 12, 31, 0, 0, 0));

    Member2 member = new Member2();
    member.setExpiryDate(LocalDateTime.of(2024, 1, 1, 0, 0, 0));

    assertFalse(member.isExpired());
  }
}

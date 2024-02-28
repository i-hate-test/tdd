package com.example.spring_boot_test.chap02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {
  @Test
  void plus() {
    int result = PasswordStrengthMeter.Calculator.plus(1, 2);
    assertEquals(3, result);
    assertEquals(5, PasswordStrengthMeter.Calculator.plus(4, 1));
  }
}

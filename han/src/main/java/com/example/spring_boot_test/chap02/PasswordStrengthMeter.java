package com.example.spring_boot_test.chap02;

public class PasswordStrengthMeter {
  public PasswordStrength meter(String s) {
    if (s == null || s.isEmpty()) return PasswordStrength.INVALID;

    int metCounts = countHowManyCriteriaMet(s);
    if (metCounts <= 1) return PasswordStrength.WEAK;
    if (metCounts == 2) return PasswordStrength.NORMAL;
    return PasswordStrength.STRONG;
  }

  private int countHowManyCriteriaMet(String s) {
    int metCounts = 0;
    if (s.length() >= 8) metCounts++;
    if (meetsContainingNumberCriteria(s)) metCounts++;
    if (meetsContainingUppercaseCriteria(s)) metCounts++;
    return metCounts;
  }

  private Boolean meetsContainingNumberCriteria(String s) {
    for (char ch : s.toCharArray()) {
      if (ch >= '0' && ch <= '9') {
        return true;
      }
    }
    return false;
  }

  private Boolean meetsContainingUppercaseCriteria(String s) {
    for (char ch : s.toCharArray()) {
      if (Character.isUpperCase(ch)) {
        return true;
      }
    }
    return false;
  }

  public static class Calculator {
    public static int plus(int a, int b) {
      return a + b;
    }
  }
}

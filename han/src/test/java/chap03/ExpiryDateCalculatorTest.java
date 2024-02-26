package chap03;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {
  @Test
  void pay_10000_Then_ExpireInOneMonthLater() {
    assertExpiryDate(
        PayData.builder().billingDate(LocalDate.of(2019, 1, 1)).payAmount(10000).build(),
        LocalDate.of(2019, 2, 1));
    assertExpiryDate(
        PayData.builder().billingDate(LocalDate.of(2019, 5, 5)).payAmount(10000).build(),
        LocalDate.of(2019, 6, 5));
  }

  @Test
  void pay_10000_Then_ExpireInOneMonthLater_But_NotSameDay() {
    assertExpiryDate(
        PayData.builder().billingDate(LocalDate.of(2019, 1, 31)).payAmount(10000).build(),
        LocalDate.of(2019, 2, 28));

    PayData payDataOfLastDayOfLessThan31DaysMonth =
        PayData.builder().billingDate(LocalDate.of(2019, 4, 30)).payAmount(10000).build();

    assertExpiryDate(payDataOfLastDayOfLessThan31DaysMonth, LocalDate.of(2019, 5, 30));

    PayData payDataForLeapYear =
        PayData.builder().billingDate(LocalDate.of(2020, 1, 31)).payAmount(10000).build();

    assertExpiryDate(payDataForLeapYear, LocalDate.of(2020, 2, 29));
  }

  @Test()
  void
      pay_10000_When_DayOfFirstBillingDate_And_DayOfBillingDate_NotSame_Then_ExpireInOneMonthLaterWithDayOfFirstBillingDate() {
    PayData payData =
        PayData.builder()
            .firstBillingDate(LocalDate.of(2019, 1, 31))
            .billingDate(LocalDate.of(2019, 2, 28))
            .payAmount(10000)
            .build();

    assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

    PayData payDataForLastDayOfMonth =
        PayData.builder()
            .firstBillingDate(LocalDate.of(2019, 1, 30))
            .billingDate(LocalDate.of(2019, 2, 28))
            .payAmount(10000)
            .build();

    assertExpiryDate(payDataForLastDayOfMonth, LocalDate.of(2019, 3, 30));

    PayData payDataForLastDayOfFirstBillingMonth =
        PayData.builder()
            .firstBillingDate(LocalDate.of(2019, 5, 31))
            .billingDate(LocalDate.of(2019, 6, 28))
            .payAmount(10000)
            .build();

    assertExpiryDate(payDataForLastDayOfFirstBillingMonth, LocalDate.of(2019, 7, 31));
  }

  @Test()
  void pay_MoreThan_20000_Then_ExpireInProportionalMonthLater() {
    PayData payData =
        PayData.builder().billingDate(LocalDate.of(2019, 7, 1)).payAmount(20000).build();

    assertExpiryDate(payData, LocalDate.of(2019, 9, 1));

    PayData payDataForLastDayOfMonth =
        PayData.builder().billingDate(LocalDate.of(2019, 5, 31)).payAmount(40000).build();

    assertExpiryDate(payDataForLastDayOfMonth, LocalDate.of(2019, 9, 30));
  }

  @Test()
  void
      pay_MoreThan_20000_When_DayOfFirstBillingDate_And_DayOfBillingDate_NotSame_Then_ExpireInProportionalMonthLaterWithDayOfFirstBillingDate() {
    PayData payData =
        PayData.builder()
            .firstBillingDate(LocalDate.of(2019, 1, 31))
            .billingDate(LocalDate.of(2019, 2, 28))
            .payAmount(20000)
            .build();

    assertExpiryDate(payData, LocalDate.of(2019, 4, 30));

    PayData payDataForLeapYear =
        PayData.builder()
            .firstBillingDate(LocalDate.of(2020, 2, 29))
            .billingDate(LocalDate.of(2020, 3, 31))
            .payAmount(40000)
            .build();

    assertExpiryDate(payDataForLeapYear, LocalDate.of(2020, 7, 29));
  }

  @Test()
  void pay_100000_Then_ExpireInOneYearLater() {
    PayData payData =
        PayData.builder().billingDate(LocalDate.of(2019, 1, 1)).payAmount(100000).build();

    assertExpiryDate(payData, LocalDate.of(2020, 1, 1));

    PayData leapYearPayData =
        PayData.builder().billingDate(LocalDate.of(2020, 2, 29)).payAmount(100000).build();

    assertExpiryDate(leapYearPayData, LocalDate.of(2021, 2, 28));
  }

  @Test()
  void pay_130000_Then_ExpireIn13MonthLater() {
    PayData payData =
        PayData.builder().billingDate(LocalDate.of(2019, 1, 1)).payAmount(130000).build();

    assertExpiryDate(payData, LocalDate.of(2020, 4, 1));

    PayData leapYearPayData =
        PayData.builder().billingDate(LocalDate.of(2020, 2, 29)).payAmount(130000).build();

    assertExpiryDate(leapYearPayData, LocalDate.of(2021, 5, 29));
  }

  private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
    ExpiryDateCalculator calculator = new ExpiryDateCalculator();
    LocalDate expiryDate = calculator.calculateExpiryDate(payData);
    assertEquals(expectedExpiryDate, expiryDate);
  }
}

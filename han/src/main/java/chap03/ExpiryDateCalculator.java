package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
  public LocalDate calculateExpiryDate(PayData payData) {
    int amountPerMonth = 10000;
    int amountPerYear = 100000;
    int addedMonths = calculateAddedMonths(payData, amountPerMonth, amountPerYear);

    if (payData.getFirstBillingDate() != null) {
      return calculateExpiryDateUsingFirstBillingDate(payData, addedMonths);
    } else {
      return payData.getBillingDate().plusMonths(addedMonths);
    }
  }

  private LocalDate calculateExpiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
    LocalDate candidateExpiryDate = payData.getBillingDate().plusMonths(addedMonths);

    final int dayOfFirstBillingDate = payData.getFirstBillingDate().getDayOfMonth();
    final int dayOfCandidateExpiryDate = candidateExpiryDate.getDayOfMonth();

    if (dayOfFirstBillingDate != dayOfCandidateExpiryDate) {
      final int lastDayOfCandidateMonth = YearMonth.from(candidateExpiryDate).lengthOfMonth();

      if (lastDayOfCandidateMonth < dayOfFirstBillingDate) {
        return candidateExpiryDate.withDayOfMonth(lastDayOfCandidateMonth);
      }
      return candidateExpiryDate.withDayOfMonth(dayOfFirstBillingDate);
    } else {
      return candidateExpiryDate;
    }
  }

  private int calculateAddedMonths(PayData payData, int amountPerMonth, int amountPerYear) {
    int year = Math.floorDiv(payData.getPayAmount(), amountPerYear);
    int month = Math.floorMod(payData.getPayAmount(), amountPerYear) / amountPerMonth;

    return year * 12 + month;
  }
}

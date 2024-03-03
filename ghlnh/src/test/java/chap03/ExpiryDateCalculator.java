package chap03;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData){
        //지불 금액이 10만원이면 1년으로 지정
        int addedMonths = payData.getPayAmount()== 100_000 ? 12:payData.getPayAmount() / 10_000;
        if (payData.getFirstBillingDate() !=null){
            return expiryDateUsingFirstDate(payData, addedMonths);
        }else{
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstDate(PayData payData, int addedMonths) {
        //후보만료일 (candidateExp) 지정 : billigdate+구독개월 수
        LocalDate candidiateExp = payData.getBillingDate().plusMonths(addedMonths);
        //첫 납부일의 일자와 후보 만료일의 일자가 다르면
        if (isSameDayOfMonth(payData.getBillingDate(), candidiateExp)){
            //후보 만료일이 포함된 달의 마지막 날 < 첫 납부일의 일자 이면 (ex: 후보 만료일자: 28일, 첫 납부일자 31일)
            final int dayLenOfCandidateMon = lastDayOfMonth(candidiateExp);
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            if (dayLenOfCandidateMon < dayOfFirstBilling){
                //후보 만료일을 그 달의 마지막 날로 조정
                return candidiateExp.withDayOfMonth(dayLenOfCandidateMon);
            }
            //첫 납부일 일자를 후보 만료일의 일자로 사용
            return candidiateExp.withDayOfMonth(dayOfFirstBilling);
        }else {
            return candidiateExp;
        }
    }

    private int lastDayOfMonth(LocalDate date) {
        return YearMonth.from(date).lengthOfMonth();
    }

    private static boolean isSameDayOfMonth(LocalDate date1, LocalDate date2) {
        return date1 != date2;
    }
}

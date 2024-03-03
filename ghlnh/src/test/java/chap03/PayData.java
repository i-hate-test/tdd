package chap03;

import java.time.LocalDate;

public class PayData {
    public LocalDate getFirstBillingDate() {
        return firstBillingDate;
    }

    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int payAmount;

    private PayData(){}

    public PayData(LocalDate firstBillingDate, LocalDate billingDate, int payAmount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.payAmount = payAmount;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder{
        private PayData data = new PayData();

        public Builder billingDate(LocalDate billingDate){
            data.billingDate = billingDate;
            return this;
        }
        public Builder payAmount(int payAmount){
            data.payAmount = payAmount;
            return this;
        }
        public PayData build(){
            return data;
        }

        public Builder firstBilingDate(LocalDate firstBilingDate) {
            data.firstBillingDate = firstBilingDate;
            return this;
        }
    }
}

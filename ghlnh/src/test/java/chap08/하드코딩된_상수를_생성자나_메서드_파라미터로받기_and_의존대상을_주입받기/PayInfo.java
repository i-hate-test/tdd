package chap08.하드코딩된_상수를_생성자나_메서드_파라미터로받기_and_의존대상을_주입받기;

public class PayInfo {
    private final String datetime;
    private final String trNum;
    private final int amounts;

    public PayInfo(String datetime, String trNum, int amounts) {
        this.datetime = datetime;
        this.trNum = trNum;
        this.amounts = amounts;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getTrNum() {
        return trNum;
    }

    public int getAmounts() {
        return amounts;
    }
}
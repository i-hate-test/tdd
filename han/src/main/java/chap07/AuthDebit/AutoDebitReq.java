package chap07.AuthDebit;

public class AutoDebitReq extends AutoDebitInfo {
  public AutoDebitReq(String userId, String cardNumber) {
    super(userId, cardNumber);
  }

  public String getUserId() {
    return super.getUserId();
  }

  public String getCardNumber() {
    return super.getCardNumber();
  }
}

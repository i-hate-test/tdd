package chap07.AuthDebit;

public interface AutoDebitInfoRepository {
  void save(AutoDebitInfo info);

  AutoDebitInfo findOne(String userId);
}

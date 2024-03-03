package chap07;

public interface AutoDebitInfoRepositiory {
    void save(AutoDebitInfo info);
    AutoDebitInfo findOne(String userId);
}

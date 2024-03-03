package chap07;

public class StubAutoDebitInfoRepository implements AutoDebitInfoRepositiory {

    @Override
    public void save(AutoDebitInfo info) {

    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return null;
    }
}

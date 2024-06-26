package chap07;

import java.util.HashMap;
import java.util.Map;

//AutoDebitInfoRepositiory 대역 구현
public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepositiory{
    private Map<String, AutoDebitInfo> infos = new HashMap<>();
    @Override
    public void save(AutoDebitInfo info) {
        infos.put(info.getUserId(), info);
    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return infos.get(userId);
    }
}

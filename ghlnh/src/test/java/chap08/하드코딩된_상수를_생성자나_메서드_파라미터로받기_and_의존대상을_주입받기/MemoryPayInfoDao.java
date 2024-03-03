package chap08.하드코딩된_상수를_생성자나_메서드_파라미터로받기_and_의존대상을_주입받기;

import chap08.PayInfoDao;
import chap08.하드코딩된_상수를_생성자나_메서드_파라미터로받기_and_의존대상을_주입받기.PayInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MemoryPayInfoDao extends PayInfoDao {
    private LinkedHashMap<String, PayInfo> infos = new LinkedHashMap<>();
    public List<PayInfo> getAll() {
        return new ArrayList<>(infos.values());
    }
}

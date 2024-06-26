package com.example.spring_boot_test.chap07.AuthDebit;

import java.util.HashMap;
import java.util.Map;

public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository {
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

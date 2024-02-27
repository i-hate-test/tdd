package chap08.PaySync;

import java.io.File;
import java.io.IOException;

public class PaySync {
  private String filePath = "path";
  private PayInfoDao payInfoDao;

  public PaySync() {}

  public void sync() throws IOException {
    File file = new File(filePath);
    if (!file.exists()) {
      throw new IOException("File not found!");
    }
    payInfoDao.insert(file);
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public void setPayInfoDao(PayInfoDao payInfoDao) {
    this.payInfoDao = payInfoDao;
  }
}

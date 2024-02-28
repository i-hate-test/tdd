package com.example.spring_boot_test.chap08.PaySync;

import java.io.File;
import java.util.HashMap;

public class MemoryPayInfoDao extends PayInfoDao {
  HashMap<String, File> files = new HashMap<>();

  @Override
  public void insert(File file) {
    files.put(file.getName(), file);
  }

  public File getFile(String name) {
    return files.get(name);
  }

  public HashMap<String, File> getAllFiles() {
    return files;
  }
}

package com.example.spring_boot_test.chap06;

import java.io.File;

public class FileExecutor {
  public void execute(File file) {
    if (!file.exists()) {
      throw new IllegalArgumentException("File not found: " + file.getPath());
    }
    return;
  }
}

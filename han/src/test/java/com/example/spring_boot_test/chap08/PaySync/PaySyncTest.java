package com.example.spring_boot_test.chap08.PaySync;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaySyncTest {
  private PaySync paySync;
  private MemoryPayInfoDao fakePayInfoDao;

  @BeforeEach
  void setUp() {
    paySync = new PaySync();
    fakePayInfoDao = new MemoryPayInfoDao();
  }

  @Test
  void when_Sync_With_InvalidPath_Throws_IO_Exception() {
    String invalidPath = "src/test/resources/invalidPath.txt";
    paySync.setFilePath(invalidPath);

    assertThrows(IOException.class, () -> paySync.sync());
  }

  @Test
  void allDataSaved() throws IOException {
    String validPath = "src/test/resources/validPath.txt";
    String content = "valid content";
    givenFile(validPath, content);

    paySync.setFilePath(validPath);
    paySync.setPayInfoDao(fakePayInfoDao);
    paySync.sync();

    HashMap<String, File> files = fakePayInfoDao.getAllFiles();
    assertEquals(1, files.size());

    deleteFile(validPath);
  }

  private void givenFile(String path, String content) {
    try {
      Path dataPath = Paths.get(path);
      if (Files.exists(dataPath)) {
        Files.delete(dataPath);
      }
      Files.write(dataPath, content.getBytes());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void deleteFile(String path) {
    try {
      Path dataPath = Paths.get(path);
      if (Files.exists(dataPath)) {
        Files.delete(dataPath);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

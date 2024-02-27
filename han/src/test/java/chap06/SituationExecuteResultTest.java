package chap06;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public class SituationExecuteResultTest {
  @Test()
  void noFile_Then_Exception() {
    String badPath = "noDataFile.txt";
    givenNoFile(badPath);

    File file = new File(badPath);
    assertThrows(IllegalArgumentException.class, () -> new FileExecutor().execute(file));
  }

  private void givenNoFile(String path) {
    File file = new File(path);
    if (file.exists()) {
      boolean deleted = file.delete();
      if (!deleted) {
        throw new IllegalArgumentException("Can't delete file: " + path);
      }
    }
  }

  @Test()
  void fileExist_Then_NoException() {
    String goodPath = "goodDataFile.txt";
    givenFile(goodPath, "test from han");

    File file = new File(goodPath);
    assertDoesNotThrow(() -> new FileExecutor().execute(file));

    givenNoFile(goodPath);
  }

  private void givenFile(String path, String content) {
    try {
      Path dataPath = Paths.get(path);
      if (Files.exists(dataPath)) {
        Files.delete(dataPath);
      }
      Files.write(dataPath, content.getBytes());
    } catch (IOException e) {
      throw new RuntimeException("Can't write file: " + path, e);
    }
  }
}

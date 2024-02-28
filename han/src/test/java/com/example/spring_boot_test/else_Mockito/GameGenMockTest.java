package com.example.spring_boot_test.else_Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

public class GameGenMockTest {
  @Test
  void mockTest() {
    GameNumGen genMock = mock(GameNumGen.class);
    given(genMock.generate(GameLevel.EASY)).willReturn("123");

    String num = genMock.generate(GameLevel.EASY);
    assertEquals("123", num);
  }

  @Test
  void mockThrowTest() {
    GameNumGen genMock = mock(GameNumGen.class);
    given(genMock.generate(GameLevel.EASY)).willThrow(new IllegalArgumentException());

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          genMock.generate(GameLevel.EASY);
        });
  }
}

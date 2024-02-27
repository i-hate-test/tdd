package else_Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.junit.jupiter.api.Test;

public class AnyMatcherTest {
  @Test
  void anyMatchTest() {
    GameNumGen genMock = mock(GameNumGen.class);
    given(genMock.generate(any())).willReturn("456");

    String num = genMock.generate(GameLevel.EASY);
    assertEquals("456", num);

    String num2 = genMock.generate(GameLevel.NORMAL);
    assertEquals("456", num2);
  }

  @Test
  void mixAnyAndEq() {
    List<Integer> mockList = mock(List.class);

    given(mockList.set(anyInt(), eq(123))).willReturn(456);

    int old = mockList.set(5, 123);
    assertEquals(456, old);
  }
}

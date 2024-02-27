package chap08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import chap08.DailyBatchLoader.DailyBatchLoader;
import chap08.DailyBatchLoader.Times;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class DailyBatchLoaderTest {
  private final DailyBatchLoader dailyLoader = new DailyBatchLoader();

  @Test
  void loadTodayCount() {
    Times mockTimes = mock(Times.class);
    given(mockTimes.today()).willReturn(LocalDate.of(2020, 1, 1));

    dailyLoader.setTime(mockTimes);
    int result = dailyLoader.loadTodayCount();

    assertEquals(100, result);
  }
}

package chap08.DailyBatchLoader;

import java.time.LocalDate;

public class BatchLoader {
  public int load(LocalDate date) {
    if (date.equals(LocalDate.of(2020, 1, 1))) return 100;
    else return 0;
  }
}

package com.example.spring_boot_test.chap08.DailyBatchLoader;

public class DailyBatchLoader {
  private BatchLoader batchLoader = new BatchLoader();
  private Times times = new Times();

  public void setLoader(BatchLoader batchLoader) {
    this.batchLoader = batchLoader;
  }

  public void setTime(Times times) {
    this.times = times;
  }

  public int loadTodayCount() {
    return this.batchLoader.load(this.times.today());
  }
}

package com.example.spring_boot_test.chap10.way1;

import java.time.Clock;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Today {
  public static Clock clock;

  @Autowired
  public Today(Clock clock) {
    Today.clock = clock;
  }

  public static LocalDateTime getDateTime() {
    return LocalDateTime.now(clock);
  }
}

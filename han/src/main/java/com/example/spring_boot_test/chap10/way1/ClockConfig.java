package com.example.spring_boot_test.chap10.way1;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfig {
  @Bean
  public Clock clock() {
    return Clock.systemDefaultZone();
  }
}

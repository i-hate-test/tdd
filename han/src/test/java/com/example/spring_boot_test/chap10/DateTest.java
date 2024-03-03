package com.example.spring_boot_test.chap10;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import com.example.spring_boot_test.chap10.way1.Member;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class DateTest {
  @MockBean private Clock clock;

  @Test
  void memberIsNotExpired() {
    prepareClock("2023-12-31T00:00:00Z");
    LocalDateTime expiryDate = LocalDateTime.of(2024, 1, 1, 0, 0, 0);

    Member member = new Member();
    member.setExpiryDate(expiryDate);

    assertFalse(member.isExpired());
  }

  private void prepareClock(CharSequence text) {
    when(clock.instant()).thenReturn(Instant.parse(text));
    when(clock.getZone()).thenReturn(Clock.systemDefaultZone().getZone());
  }
}

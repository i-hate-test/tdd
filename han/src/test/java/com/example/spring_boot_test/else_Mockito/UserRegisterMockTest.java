package com.example.spring_boot_test.else_Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import com.example.spring_boot_test.chap07.UserRegister.EmailNotifier;
import com.example.spring_boot_test.chap07.UserRegister.UserRegister;
import com.example.spring_boot_test.chap07.UserRegister.UserRepository;
import com.example.spring_boot_test.chap07.UserRegister.WeakPasswordChecker;
import com.example.spring_boot_test.chap08.DailyBatchLoader.DailyBatchLoader;
import com.example.spring_boot_test.chap08.DailyBatchLoader.Times;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

public class UserRegisterMockTest {
  private UserRegister userRegister;

  private WeakPasswordChecker mockWeakPasswordChecker = mock(WeakPasswordChecker.class);
  private UserRepository mockUserRepository = mock(UserRepository.class);
  @Mock private EmailNotifier mockEmailNotifier;

  @BeforeEach
  void setUp() {
    userRegister = new UserRegister(mockWeakPasswordChecker, mockUserRepository, mockEmailNotifier);
  }

  @DisplayName("When registered EmailNotifier should send email")
  @Test
  void whenRegister_thenSendEmail() {
    userRegister.register("id", "pw", "email@email.com");

    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    then(mockEmailNotifier).should().sendRegisterEmail(captor.capture());

    String capturedEmail = captor.getValue();
    assertEquals("email@email.com", capturedEmail);
  }

  public static class DailyBatchLoaderTest {
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
}

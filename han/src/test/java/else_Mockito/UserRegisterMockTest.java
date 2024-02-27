package else_Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import chap07.UserRegister.EmailNotifier.EmailNotifier;
import chap07.UserRegister.PasswordChecker.WeakPasswordChecker;
import chap07.UserRegister.UserRegister;
import chap07.UserRegister.UserRepository.UserRepository;
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
}

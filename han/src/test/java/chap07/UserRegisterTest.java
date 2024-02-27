package chap07;

import static org.junit.jupiter.api.Assertions.*;

import chapter07.UserRegister.*;
import chapter07.UserRegister.EmailNotifier.SpyEmailNotifier;
import chapter07.UserRegister.PasswordChecker.StubWeakPasswordChecker;
import chapter07.UserRegister.PasswordChecker.WeakPasswordException;
import chapter07.UserRegister.User.DupIdException;
import chapter07.UserRegister.User.User;
import chapter07.UserRegister.UserRepository.MemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRegisterTest {
  private UserRegister userRegister;
  private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
  private MemoryUserRepository fakeUserRepository = new MemoryUserRepository();
  private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

  @BeforeEach
  void setUp() {
    userRegister = new UserRegister(stubWeakPasswordChecker, fakeUserRepository, spyEmailNotifier);
  }

  @DisplayName("약한 암호면 가입 실패")
  @Test
  void weakPassword() {
    stubWeakPasswordChecker.setWeak(true);

    assertThrows(
        WeakPasswordException.class,
        () -> {
          userRegister.register("id", "pw", "email");
        });
  }

  @DisplayName("같은 ID가 이미 존재하면 가입 실패")
  @Test
  void dupIdExists() {
    fakeUserRepository.save(new User("id", "pw", "email@email.com"));

    assertThrows(
        DupIdException.class,
        () -> {
          userRegister.register("id", "pw", "email@email.com");
        });
  }

  @DisplayName("같은 ID가 존재하지 않으면 가입 성공")
  @Test
  void noDupId_RegisterSuccess() {
    userRegister.register("id", "pw", "email@email.com");

    User savedUser = fakeUserRepository.findById("id");
    assertEquals("id", savedUser.getId());
    assertEquals("pw", savedUser.getPassword());
    assertEquals("email@email.com", savedUser.getEmail());
  }

  @DisplayName("가입 시 메일 전송")
  @Test
  void whenRegisterThenSendMail() {
    userRegister.register("id", "pw", "email@email.com");

    assertTrue(spyEmailNotifier.isCalled());
    assertEquals("email@email.com", spyEmailNotifier.getEmail());
  }
}

package com.example.spring_boot_test.chap07.UserRegister;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRegisterTest {
  private UserRegister userRegister;
  private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
  private UserRegister.MemoryUserRepository fakeUserRepository =
      new UserRegister.MemoryUserRepository();
  private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

  @BeforeEach
  void setUp() {
    userRegister = new UserRegister(stubWeakPasswordChecker, fakeUserRepository, spyEmailNotifier);
  }

  @DisplayName("Fail when weak password")
  @Test
  void weakPassword() {
    stubWeakPasswordChecker.setWeak(true);

    assertThrows(
        WeakPasswordException.class,
        () -> {
          userRegister.register("id", "pw", "email");
        });
  }

  @DisplayName("Fail when dup ID")
  @Test
  void dupIdExists() {
    fakeUserRepository.save(new User("id", "pw", "email@email.com"));

    assertThrows(
        DupIdException.class,
        () -> {
          userRegister.register("id", "pw", "email@email.com");
        });
  }

  @DisplayName("Success when no dup ID and valid password")
  @Test
  void noDupId_RegisterSuccess() {
    userRegister.register("id", "pw", "email@email.com");

    User savedUser = fakeUserRepository.findById("id");
    assertEquals("id", savedUser.getId());
    assertEquals("pw", savedUser.getPassword());
    assertEquals("email@email.com", savedUser.getEmail());
  }

  @DisplayName("Send mail when register success")
  @Test
  void whenRegisterThenSendMail() {
    userRegister.register("id", "pw", "email@email.com");

    assertTrue(spyEmailNotifier.isCalled());
    assertEquals("email@email.com", spyEmailNotifier.getEmail());
  }
}

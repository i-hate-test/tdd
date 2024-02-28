package com.example.spring_boot_test.chap07.UserRegister;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class UserRegister {
  private WeakPasswordChecker passwordChecker;
  private UserRepository userRepository;
  private EmailNotifier emailNotifier;

  public UserRegister(
      WeakPasswordChecker passwordChecker,
      UserRepository userRepository,
      EmailNotifier emailNotifier) {
    this.passwordChecker = passwordChecker;
    this.userRepository = userRepository;
    this.emailNotifier = emailNotifier;
  }

  public void register(String id, String pw, String email) {
    if (passwordChecker.checkPasswordWeak(pw)) {
      throw new WeakPasswordException();
    }
    User existUser = userRepository.findById(id);
    if (existUser != null) {
      throw new DupIdException();
    }
    userRepository.save(new User(id, pw, email));

    emailNotifier.sendRegisterEmail(email);
  }

  public static class MemoryUserRepository implements UserRepository {
    private Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
      users.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
      return users.get(id);
    }
  }
}

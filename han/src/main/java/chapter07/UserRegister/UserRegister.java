package chapter07.UserRegister;

import chapter07.UserRegister.EmailNotifier.EmailNotifier;
import chapter07.UserRegister.PasswordChecker.WeakPasswordChecker;
import chapter07.UserRegister.PasswordChecker.WeakPasswordException;
import chapter07.UserRegister.User.DupIdException;
import chapter07.UserRegister.User.User;
import chapter07.UserRegister.UserRepository.UserRepository;

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
}

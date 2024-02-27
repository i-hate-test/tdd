package chap07.UserRegister;

import chap07.UserRegister.EmailNotifier.EmailNotifier;
import chap07.UserRegister.PasswordChecker.WeakPasswordChecker;
import chap07.UserRegister.PasswordChecker.WeakPasswordException;
import chap07.UserRegister.User.DupIdException;
import chap07.UserRegister.User.User;
import chap07.UserRegister.UserRepository.UserRepository;

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

package chap07.UserRegister.PasswordChecker;

public class StubWeakPasswordChecker implements WeakPasswordChecker {
  private boolean weak;

  @Override
  public void setWeak(boolean weak) {
    this.weak = weak;
  }

  @Override
  public boolean checkPasswordWeak(String pw) {
    return weak;
  }
}

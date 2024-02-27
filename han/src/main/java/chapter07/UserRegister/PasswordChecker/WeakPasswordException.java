package chapter07.UserRegister.PasswordChecker;

public class WeakPasswordException extends RuntimeException {
  private boolean weak;

  public void setWeak(boolean weak) {
    this.weak = weak;
  }
}

package chap07.UserRegister.PasswordChecker;

public interface WeakPasswordChecker {
  public void setWeak(boolean weak);

  public boolean checkPasswordWeak(String pw);
}

package chap07.UserRegister.UserRepository;

import chap07.UserRegister.User.User;

public interface UserRepository {
  public void save(User user);

  public User findById(String id);
}

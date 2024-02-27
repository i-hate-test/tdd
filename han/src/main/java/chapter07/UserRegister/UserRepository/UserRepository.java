package chapter07.UserRegister.UserRepository;

import chapter07.UserRegister.User.User;

public interface UserRepository {
  public void save(User user);

  public User findById(String id);
}

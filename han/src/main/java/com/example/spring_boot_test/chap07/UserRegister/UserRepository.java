package com.example.spring_boot_test.chap07.UserRegister;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, String> {
  public void save(User user);

  public User findById(String id);
}

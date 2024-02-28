package com.example.spring_boot_test.chap09;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.spring_boot_test.chap07.UserRegister.DupIdException;
import com.example.spring_boot_test.chap07.UserRegister.UserRegister;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@SpringBootTest
public class UserRegisterIntTest {
  @Autowired private UserRegister register;
  @Autowired private JdbcTemplate jdbcTemplate;

  @Test
  void throwDupIdException() {
    jdbcTemplate.update(
        "INSERT INTO users (id, password, email) VALUES (?, ?, ?) on duplicate key update password = ?, email = ?",
        "cbk",
        "pw",
        "cbk@cbk.com",
        "pw",
        "cbk@cbk.com");

    assertThrows(
        DupIdException.class, () -> register.register("cbk", "strongpw", "email@email.com"));
  }

  @Test
  void insert_When_Not_Exists() {
    jdbcTemplate.update("DELETE FROM users WHERE id = ?", "cbk");

    register.register("cbk", "strongpw", "email@email.com");

    SqlRowSet rs = jdbcTemplate.queryForRowSet("SELECT * FROM users WHERE id = ?", "cbk");
    rs.next();
    assertEquals("cbk", rs.getString("id"));
    assertEquals("strongpw", rs.getString("password"));
    assertEquals("email@email.com", rs.getString("email"));
  }
}

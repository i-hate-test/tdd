package com.example.spring_boot_test.chap10;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class UserRegisterIntTestHelper {
  private JdbcTemplate jdbcTemplate;

  public UserRegisterIntTestHelper(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void givenUser(String id, String password, String email) {
    jdbcTemplate.update(
        "INSERT INTO USERS (id, password, email) VALUES (?, ?, ?) on duplicate key update password = ?, email = ?",
        id,
        password,
        email,
        password,
        email);
  }

  public void noGivenUser(String id) {
    jdbcTemplate.update("DELETE FROM USERS WHERE id = ?", id);
  }

  public SqlRowSet findUser(String id) {
    return jdbcTemplate.queryForRowSet("SELECT * FROM USERS WHERE id = ?", id);
  }
}

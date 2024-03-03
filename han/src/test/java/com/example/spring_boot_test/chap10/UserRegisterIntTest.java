package com.example.spring_boot_test.chap10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.spring_boot_test.chap07.UserRegister.DupIdException;
import com.example.spring_boot_test.chap07.UserRegister.UserRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@SpringBootTest
public class UserRegisterIntTest {
  @Autowired private UserRegister register;
  @Autowired private JdbcTemplate jdbcTemplate;
  private UserRegisterIntTestHelper helper;

  @BeforeEach
  void setUp() {
    helper = new UserRegisterIntTestHelper(jdbcTemplate);
  }

  @Test
  void throwDupIdException() {
    helper.givenUser("cbk", "strongpw", "email@email.com");

    assertThrows(
        DupIdException.class, () -> register.register("cbk", "strongpw", "email@email.com"));

    helper.noGivenUser("cbk");
  }

  @Test
  void insert_When_Not_Exists() {
    helper.noGivenUser("cbk");
    helper.givenUser("cbk", "strongpw", "email@email.com");

    SqlRowSet rs = helper.findUser("cbk");
    rs.next();
    assertEquals("cbk", rs.getString("id"));
    assertEquals("strongpw", rs.getString("password"));
    assertEquals("email@email.com", rs.getString("email"));
  }
}

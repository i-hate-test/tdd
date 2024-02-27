package chap08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import chap08.LoginService.AuthService;
import chap08.LoginService.LoginResult;
import chap08.LoginService.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginServiceTest {
  private LoginService loginService;

  @BeforeEach
  void setUp() {
    loginService = new LoginService();
  }

  @Test
  void whenAuthenticatedFalse_Throw_ArgumentException() {
    AuthService authService = mock(AuthService.class);
    String invalidId = "id";
    String invalidPw = "1234";

    given(authService.authenticate(invalidId, invalidPw)).willReturn(false);
    loginService.setAuthService(authService);

    assertEquals(LoginResult.INVALID_ID, loginService.login(invalidId, invalidPw));
  }
}

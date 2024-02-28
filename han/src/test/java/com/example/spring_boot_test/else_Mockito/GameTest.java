package com.example.spring_boot_test.else_Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

import com.example.spring_boot_test.chap08.LoginService.AuthService;
import com.example.spring_boot_test.chap08.LoginService.LoginResult;
import com.example.spring_boot_test.chap08.LoginService.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
  @Test
  void gameNumGen_ShouldGenerate_InitiatedGameLevel() {
    GameNumGen genMock = mock(GameNumGen.class);
    Game game = new Game(genMock);
    game.init(GameLevel.EASY);

    then(genMock).should().generate(GameLevel.EASY);
  }

  @Test
  void gameNumGen_ShouldGenerate_Twice() {
    GameNumGen genMock = mock(GameNumGen.class);
    Game game = new Game(genMock);
    game.init(GameLevel.EASY);
    game.init(GameLevel.EASY);

    then(genMock).should(atLeast(2)).generate(GameLevel.EASY);
  }

  public static class LoginServiceTest {
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
}

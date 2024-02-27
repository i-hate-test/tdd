package else_Mockito;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

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
}

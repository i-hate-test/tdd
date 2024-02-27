package chap07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chapter07.AuthDebit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoDebitRegister_Fake_Test {
  private AutoDebitRegister register;
  private StubCardNumberValidator stubValidator;
  private MemoryAutoDebitInfoRepository repository;

  @BeforeEach
  void setUp() {
    stubValidator = new StubCardNumberValidator();
    repository = new MemoryAutoDebitInfoRepository();
    register = new AutoDebitRegister(stubValidator, repository);
  }

  @Test
  void invalidCard() {
    stubValidator.setInvalidNo("1111222233334444");

    AutoDebitReq req = new AutoDebitReq("user1", "1111222233334444");
    RegisterResult result = register.register(req);

    assertEquals(CardValidity.INVALID, result.getValidity());
  }

  @Test
  void alreadyRegistered_InfoUpdated() {
    repository.save(new AutoDebitInfo("user1", "old"));

    AutoDebitReq req = new AutoDebitReq("user1", "new");
    RegisterResult result = register.register(req);

    AutoDebitInfo saved = repository.findOne("user1");
    assertEquals("new", saved.getCardNumber());
  }

  @Test
  void notYetRegistered_newInfoRegistered() {
    AutoDebitReq req = new AutoDebitReq("user2", "new");
    RegisterResult result = register.register(req);

    AutoDebitInfo saved = repository.findOne("user2");
    assertEquals("new", saved.getCardNumber());
  }
}

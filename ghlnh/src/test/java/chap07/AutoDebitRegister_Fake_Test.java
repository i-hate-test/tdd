package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegister_Fake_Test {
    private AutoDebitRegister register;
    private StubCardNumberValidator cardNumberValidator;
    private MemoryAutoDebitInfoRepository repository;  //DB대역객체

    @BeforeEach
    void setUp(){
        cardNumberValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(cardNumberValidator, repository);
    }

    @Test
    @DisplayName("이미 자동이체 정보가 등록되어 있을 때 기존 정보가 올바르게 바뀌는지 검사")
    void alreadyRegistered_InfoUpdated(){
        repository.save(
                new AutoDebitInfo("user1", "1111222333444", LocalDateTime.now()));//이미 자동이체 정보가 존재하는 상황 흉내

        AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
        RegisterResult result = this.register.register(req);
    }

    @Test
    @DisplayName("아직 자동이체 정보가 등록되어 있지 않을 때 새로운 정보과 올바르게 등록되는지 검사")
    void notYetRegistered_newInfoRegistered(){
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");//대역을 통한 결과 검증
        assertEquals("1234123412341234", saved.getCardNumber());
    }

    //특정 사용자에 대한 자동이체 정보가 이미 등록되어 있거나 등록되어 있지 않은 상황을 흉내냄
}

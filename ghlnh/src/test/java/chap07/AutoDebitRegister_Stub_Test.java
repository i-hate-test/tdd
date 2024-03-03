package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chap07.CardValidity.INVALID;
import static chap07.CardValidity.THEFT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegister_Stub_Test {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private StubAutoDebitInfoRepository stubRepository;

    @BeforeEach
    void setUp(){
        stubValidator = new StubCardNumberValidator();
        stubRepository = new StubAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, stubRepository);
    }

    @Test
    @DisplayName("대역을 사용해서 유효하지 않은 카드번호 테스트")
    void invalidCard(){
        stubValidator.setInvalidNo("111122223333");//대역 사용->유효하지 않은 카드 번호 상황 흉내

        AutoDebitReq req = new AutoDebitReq("user1", "111122223333");
        RegisterResult result = register.register(req);

        assertEquals(INVALID, result.getValidity());
    }

    @Test
    @DisplayName("대역을 사용해서 도난 카드번호 테스트")
    void theftCard(){
        stubValidator.SetTheftNo("1234567890123456");//대역 사용

        AutoDebitReq req = new AutoDebitReq("user2", "1234567890123456");
        RegisterResult result = register.register(req);

        assertEquals(THEFT, result.getValidity());
    }

}

//카드 정보 API 대신해서 유효한 카드 번호, 도난 카드 번호와 같은 상황을 흉내냄

package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chap07.CardValidity.THEFT;
import static chap07.CardValidity.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegisterTest {
    private AutoDebitRegister register;

    @BeforeEach
    void setUp(){
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepositiory repositiory = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repositiory);
    }

    @Test
    @DisplayName("업체에서 받은 테스트용 유효한 카드번호 사용")
    void validCard(){
        AutoDebitReq req = new AutoDebitReq("user1","1234123412341234");
        RegisterResult result = this.register.register(req);
        assertEquals(VALID, result.getValidity());
    }

    @Test
    @DisplayName("업체에서 받은 도난 테스트용 카드번호 사용")
    void theftCard(){
        AutoDebitReq req = new AutoDebitReq("user2", "1234567891234567");
        RegisterResult result = this.register.register(req);
        assertEquals(THEFT,result.getValidity());
    }

}

//외부 업체로부터 테스트 목적의 정보를 얻어야만 진행->이러한 의존 요인 때문에 테스트가 어려움
//대역을 써서 테스트 진행 가능 (AutoDebitRegister_Stub_Test)

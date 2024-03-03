package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


public class UserRegisterTest {
    private UserRegister userRegister;
    //모의객체로 스텁과 스파이 대체
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    //스텁 사용해서 대역 구현
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();

    private  MemoryUserRepository fakeRepository = new MemoryUserRepository();

    //스파이 사용해서 대역 구현
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    //모의객체로 스파이 대체
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);
/*
    //스텁과 스파이로 대역 테스트
    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubPasswordChecker, fakeRepository, spyEmailNotifier);
    }*/

    //모의객체로 스텁&스파이 대체
    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubPasswordChecker, fakeRepository, mockEmailNotifier);
    }

/*    //스텁 사용해서 대역 테스트
    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword(){
        stubPasswordChecker.setWeak(true);  //암호가 약하다고 응답하도록 설정
        assertThrows(WeakPasswordException.class, () ->{
            userRegister.register("id","pw", "email");
        });
    }*/

    //모의객체로 스파이 대체
    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword(){
        //"pw"인자를 사용해서 모의 객체의 checkPasswordWeak 메서드롤 호출하면
        BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw"))
                //결과로 ture를 리턴하라
                .willReturn(true);
        };

    @DisplayName("회원 가입시 암호 검사 수행함")
    @Test
    void checkPassword(){
        userRegister.register("id", "pw", "email");
        //인자로 전달된 mockPasswordChecker 모의 객체의
        BDDMockito.then(mockPasswordChecker)
                //특정 메서드가 호출됐는지 검증하는데
                .should()
                //임의의 String 타입 인자를 이용해서 checkPasswordWeak() 호출여부를 확인
                .checkPasswordWeak(BDDMockito.anyString());
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    void dupIdExists(){
        //이미 같은 ID가 존재하는 상황 만들기
        fakeRepository.save(new User("id", "pw1", "email@email.com"));
        assertThrows(DupIdException.class, ()->{
            userRegister.register("id", "pw2", "email");
        });
    }

    @DisplayName("같은 ID가 없으면 가입 성공")
    @Test
    void noDupId_RegisterSuccess(){
        userRegister.register("id", "pw", "email");

        User savedUser = fakeRepository.findById("id"); //가입 결과 확인
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail(){
        userRegister.register("id", "pw", "email@email.com");

/*        //결과확인을 위해 대역을 이용->스파이 사용해서 대역 테스트
        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("email@email.com", spyEmailNotifier.getEmail());*/


        //ArgumentCaptor: 모의 객체를 메서드를 호출할 때 전달한 객체를 담는 기능 제공
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier)
                .should()
                //ArgumentCaptor.capture(): 메서드를 호출할 때 전달한 인자가 ArgumentCaptor 에 담김
                .sendReisterEmail(captor.capture());

        String realEmail = captor.getValue();//보관한 인자를 구함
        assertEquals("email@email.com", realEmail);
    }
}


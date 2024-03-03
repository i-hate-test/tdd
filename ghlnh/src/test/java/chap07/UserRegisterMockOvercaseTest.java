package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRegisterMockOvercaseTest {
    private UserRegister userRegister;
    private UserRepository mockRepository = Mockito.mock(UserRepository.class);

    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp(){
        userRegister = new UserRegister(mockPasswordChecker, mockRepository, mockEmailNotifier);
    }

    @Test
    void noDupId_RegisterSuccess(){
        userRegister.register("id", "pw", "email@email.com");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        //save()가 호출되었는지 확인해야함 + ArgumentCaptor 를 이용해서 호출할 때 전달한 인자를 전달해야함
        // ->fakeRepository(MemoryUserRepository 내 메서드)(메모리를 이용한 가짜구현)보다 코드가 복잡해짐
        BDDMockito.then(mockRepository).should().save(captor.capture());

        User savedUser = captor.getValue();
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }
}

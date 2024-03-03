package chap08.외부라이브러리는_직접_사용하지말고_감싸서_사용하기;

import chap08.Customer;
import chap08.CustomerRepository;
import chap08.LoginResult;

//대역 사용이 어려운 외부 라이브러리를 직접 사용하지 않게 변경
public class LoginService {
    //분리한 타입(AuthService)을 사용하도록 변경
    private AuthService authService = new AuthService();

    private CustomerRepository customerRepo;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public LoginResult login(String id, String pw) {
        int resp = authService.authenticate(id, pw);
        if (resp == -1) return LoginResult.badAuthKey();

        if (resp == 1) {
            Customer c = customerRepo.findOne(id);
            return LoginResult.authenticated(c);
        } else {
            return LoginResult.fail(resp);
        }
    }

}

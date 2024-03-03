package chap08.외부라이브러리는_직접_사용하지말고_감싸서_사용하기;

import chap08.AuthUtil;

//대역으로 대체하기 어려운 외부라이러리를 대신해 외부라이브러리와 연동하기 위한 타입 따로 생성
public class AuthService {
    private String authKey = "somekey";

    public int authenticate(String id, String pw) {
        boolean authorized = AuthUtil.authorize(authKey);
        if(authorized) {
            return AuthUtil.authenticate(id, pw);
        } else {
            return -1;
        }
    }
}
